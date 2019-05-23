package com.cpm.Marico.dailyEntry;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MappingTesterStock;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.utilities.AlertandMessages;
import com.cpm.Marico.utilities.CommonString;

import java.util.ArrayList;

public class TesterStockActivity extends AppCompatActivity {

    private MaricoDatabase database;
    Dialog dialog;
    Context context;
    SharedPreferences preferences;
    FloatingActionButton fab;
    boolean flag = true;
    Toolbar toolbar;
    String _pathforcheck, _path, str,Error_Message = "";
    private String username, store_id, visit_date,menu_id="";
    RecyclerView recyclerView;
    Bitmap bmp, dest;
    JourneyPlan journeyPlan;
    MenuMaster menuMaster;
    ArrayList<MappingTesterStock> testerStockList = new ArrayList<>();
    TesterStockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester_stock);
        declaration();
    }


    private void declaration() {
        context = this;
        database = new MaricoDatabase(context);
        database.open();
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster  = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
            store_id = String.valueOf(journeyPlan.getStoreId());
            menu_id  = String.valueOf(menuMaster.getMenuId());
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        store_id   = String.valueOf(journeyPlan.getStoreId());
        visit_date = String.valueOf(journeyPlan.getVisitDate());
        username   =  preferences.getString(CommonString.KEY_USERNAME, null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tester Available - " + visit_date);
        getSupportActionBar().setSubtitle( journeyPlan.getStoreName() + " - " + journeyPlan.getStoreId());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateData(testerStockList)) {
                    saveData(testerStockList);
                    Snackbar.make(recyclerView, "Data has been saved", Snackbar.LENGTH_LONG).show();
                    finish();
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                } else {
                    Snackbar.make(recyclerView, Error_Message, Snackbar.LENGTH_LONG).show();
                }
            }
        });


        database.open();
        testerStockList = database.getInsertedTestStockData(store_id,visit_date);

        if (!(testerStockList.size() > 0)) {
            testerStockList = database.getTesterStockData(journeyPlan.getStoreTypeId(), journeyPlan.getStateId(), journeyPlan.getStoreCategoryId());
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
        }


       /* if(testerStockList.size() == 0) {
            database.open();
            testerStockList = database.getTesterStockData(journeyPlan.getStoreTypeId(), journeyPlan.getStateId(), journeyPlan.getStoreCategoryId());
        }*/

        adapter = new TesterStockAdapter(testerStockList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private boolean validateData(ArrayList<MappingTesterStock> testerStockList) {
      boolean flag = true;

      for(int i=0;i<testerStockList.size();i++){
          if(testerStockList.get(i).getIsChecked() == -1){
              flag = false;
              break;
          }
      }

      if(!flag){
        Error_Message = "Please set stock exist or not";
      }
      return flag;
    }


    public void saveData(ArrayList<MappingTesterStock> testerStockList) {

        long i1 = database.insertTesterStockData(this.testerStockList,store_id,menu_id, visit_date,username);
        if (i1 > 0) {
            AlertandMessages.showToastMsg(TesterStockActivity.this,"Data saved successfully");
            finish();
        } else {
            AlertandMessages.showToastMsg(TesterStockActivity.this,"Data not saved");
        }
    }


    @Override
    public void onBackPressed() {
        new AlertandMessages(TesterStockActivity.this, null, null, null).backpressedAlert();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            new AlertandMessages(TesterStockActivity.this, null, null, null).backpressedAlert();

        }
        return super.onOptionsItemSelected(item);
    }

    private class TesterStockAdapter extends RecyclerView.Adapter<TesterStockAdapter.ViewHolder> {
        ArrayList<MappingTesterStock> testerStockList;

        public TesterStockAdapter(ArrayList<MappingTesterStock> testerStockList) {
            this.testerStockList = testerStockList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tester_stock_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {
            viewHolder.sku_name.setText(testerStockList.get(pos).getSku());
            viewHolder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (viewHolder.radio_yes.isChecked()) {
                        viewHolder.radio_yes.setBackground(getResources().getDrawable(R.mipmap.yes_green));
                        viewHolder.radio_no.setBackground(getResources().getDrawable(R.mipmap.no_white));
                        testerStockList.get(pos).setIsChecked(1);
                    }
                    if (viewHolder.radio_no.isChecked()) {
                        viewHolder.radio_no.setBackground(getResources().getDrawable(R.mipmap.no_red));
                        viewHolder.radio_yes.setBackground(getResources().getDrawable(R.mipmap.yes_white));
                        testerStockList.get(pos).setIsChecked(0);
                    }
                }
            });


            if(testerStockList.get(pos).getIsChecked()==1){
                viewHolder.radio_yes.setBackground(getResources().getDrawable(R.mipmap.yes_green));
                viewHolder.radio_no.setBackground(getResources().getDrawable(R.mipmap.no_white));
                viewHolder.radio_yes.setChecked(true);
                //notifyDataSetChanged();
            }
            else if(testerStockList.get(pos).getIsChecked()==0){
                viewHolder.radio_yes.setBackground(getResources().getDrawable(R.mipmap.yes_white));
                viewHolder.radio_no.setBackground(getResources().getDrawable(R.mipmap.no_red));
                viewHolder.radio_no.setChecked(true);
                //notifyDataSetChanged();
            }else {
                viewHolder.radio_yes.setBackground(getResources().getDrawable(R.mipmap.yes_white));
                viewHolder.radio_no.setBackground(getResources().getDrawable(R.mipmap.no_white));
                viewHolder.rg.clearCheck();
                //notifyDataSetChanged();
            }
        }

        @Override
        public int getItemCount() {
            return testerStockList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView sku_name;
            RadioButton radio_yes,radio_no;
            RadioGroup rg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                sku_name = (TextView)itemView.findViewById(R.id.sku_name);
                radio_yes = (RadioButton) itemView.findViewById(R.id.radio_yes);
                radio_no = (RadioButton) itemView.findViewById(R.id.radio_no);
                rg = (RadioGroup) itemView.findViewById(R.id.radio_group);
            }
        }
    }
}
