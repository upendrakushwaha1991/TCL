package com.cpm.Tcl.dailyEntry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.adapter.SkuListAdapter;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.ClosingStockData;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.getterSetter.StockNewGetterSetter;
import com.cpm.Tcl.utilities.AlertandMessages;
import com.cpm.Tcl.utilities.CommonString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CheckOutConfirmationActivity extends AppCompatActivity {
    MaricoDatabase db;
    TextView store_name,visited_date,promoter_name;
    RecyclerView sku_view;
    private SharedPreferences preferences;
    String visit_date="",username="",rightname="", tag_from = "";
    JourneyPlan jcpGetset;
    ArrayList<StockNewGetterSetter> stockList;
    SkuListAdapter skuAdapter;
    Button btnYes,btnNo;
    MenuMaster menuMaster;
    ClosingStockData closingStockData;
    LinearLayout sale_layout;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_confirmation);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null  && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null && getIntent().getSerializableExtra(CommonString.KEY_LIST) != null) {
            jcpGetset  = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            closingStockData = (ClosingStockData) getIntent().getSerializableExtra(CommonString.KEY_LIST);
            tag_from   = getIntent().getStringExtra(CommonString.TAG_FROM);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        store_name = (TextView)findViewById(R.id.store_name_txt);
        visited_date = (TextView)findViewById(R.id.date_txt);
        promoter_name = (TextView)findViewById(R.id.promoter_name);
        sku_view = (RecyclerView)findViewById(R.id.sku_view);
        btnNo = (Button)findViewById(R.id.btn_no);
        btnYes = (Button)findViewById(R.id.btn_yes);
        sale_layout = (LinearLayout)findViewById(R.id.sale_layout);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutConfirmationActivity.this,ClosingStock.class);
                intent.putExtra(CommonString.TAG_OBJECT, jcpGetset);
                intent.putExtra(CommonString.KEY_MENU_ID, menuMaster);
                intent.putExtra(CommonString.TAG_FROM, tag_from);
                intent.putExtra(CommonString.KEY_LIST,closingStockData);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                finish();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = saveBitMapImage(CheckOutConfirmationActivity.this,sale_layout);
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new MaricoDatabase(getApplicationContext());
        db.open();
        stockList =  db.getstockInsertedStoreDetails(jcpGetset,closingStockData.getHashMapData(),closingStockData.getStockList());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date  = preferences.getString(CommonString.KEY_DATE, null);
        username    = preferences.getString(CommonString.KEY_USERNAME, null);
        rightname   = preferences.getString(CommonString.KEY_RIGHTNAME, "");

        setTitle("Sales Confirmation");

        store_name.setText(jcpGetset.getStoreName()+ " ( " + jcpGetset.getStateId() + " )");
        visited_date.setText(visit_date);
        promoter_name.setText(username);


        skuAdapter = new SkuListAdapter(stockList);
        sku_view.setHasFixedSize(true);
        sku_view.setLayoutManager(new LinearLayoutManager(this));
        sku_view.setAdapter(skuAdapter);
    }

    private File saveBitMapImage(CheckOutConfirmationActivity context, LinearLayout drawView) {
        File pictureFileDir = new File(CommonString.FILE_PATH1);
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("TAG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() +File.separator+ System.currentTimeMillis()+".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();

            if (getIntent().getSerializableExtra(CommonString.KEY_LIST) != null) {
                closingStockData = (ClosingStockData) getIntent().getSerializableExtra(CommonString.KEY_LIST);
                db.open();
                long val =  db.UpdateClosingStocklistData(jcpGetset, closingStockData.getHashMapData(), closingStockData.getStockList());
                if(val > 0){
                    Intent intent = new Intent(CheckOutConfirmationActivity.this,EntryMenuActivity.class);
                    intent.putExtra(CommonString.TAG_OBJECT, jcpGetset);
                    intent.putExtra(CommonString.TAG_FROM, tag_from);
                    intent.putExtra(CommonString.KEY_STATUS, "1");
                    intent.putExtra(CommonString.KEY_IMAGE, pictureFile.getName());
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    finish();
                }else{
                    AlertandMessages.showToastMsg(context,"Data not saved");
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    finish();
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }

        return pictureFile;
    }


    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }
}
