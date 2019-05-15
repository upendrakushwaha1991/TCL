package com.cpm.Marico.dailyEntry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;*/
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.BackofStoreGetterSetter;
import com.cpm.Marico.getterSetter.BrandMaster;
import com.cpm.Marico.getterSetter.CategoryMaster;
import com.cpm.Marico.getterSetter.CommonChillerDataGetterSetter;
import com.cpm.Marico.getterSetter.FocusProductGetterSetter;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MappingTesterStock;
import com.cpm.Marico.getterSetter.MenuGetterSetter;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.utilities.CommonString;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntryMenuActivity extends AppCompatActivity {

    MaricoDatabase database;
    Context context;
    JourneyPlan journeyPlan;
    String visit_date = "";
    List<MenuGetterSetter> data = new ArrayList<>();
    RecyclerView recyclerView;
    ValueAdapter adapter;
    TextView txt_label;
    SharedPreferences preferences;
    List<MenuMaster> menu_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_menu);
        declaration();
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null
                && getIntent().getSerializableExtra(CommonString.TAG_FROM) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            getSupportActionBar().setTitle("");
            txt_label.setText("Entry Menu" + " - " + visit_date);
        }

        if(getIntent().getSerializableExtra(CommonString.KEY_STATUS) != null && getIntent().getStringExtra(CommonString.KEY_IMAGE) != null){
            final Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpg");
            final File photoFile = new File(CommonString.FILE_PATH1, getIntent().getStringExtra(CommonString.KEY_IMAGE));
            shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(EntryMenuActivity.this,"com.cpm.Marico.fileprovider",photoFile));
            startActivity(Intent.createChooser(shareIntent, "Share image using"));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        menu_list = database.getMenuData(journeyPlan.getStoreTypeId(), journeyPlan.getStoreCategoryId());

        adapter = new ValueAdapter(context, menu_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        if (chekDataforCheckout(journeyPlan)) {

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    finish();
                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 1000);

        }
    }


    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder> {
        private LayoutInflater inflator;
        List<MenuMaster> data;

        public ValueAdapter(Context context, List<MenuMaster> data) {
            inflator = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = inflator.inflate(R.layout.custom_menu_row, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
            final MenuMaster current = data.get(position);
            viewHolder.txt.setText(current.getMenuName());
            //viewHolder.icon.setImageResource(current.getIconImage());

            String icon_path = current.getNormalIcon();
            switch (current.getMenuId()) {

                case 1:
                    if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                        if (database.isOpeningDataFilled(journeyPlan.getStoreId().toString())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    } else {
                        icon_path = current.getGreyIcon();
                    }
                    break;

                case 2:

                    if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                        if (database.isMiddayDataFilled1(journeyPlan.getStoreId())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    } else {
                        icon_path = current.getGreyIcon();
                    }
                    break;

                case 3:
                    if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                        if (database.isClosingDataFilled1(journeyPlan.getStoreId())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    } else {
                        icon_path = current.getGreyIcon();
                    }
                    break;

                case 4:

                    if (database.getmappingShareOfShelfData().size() > 0) {
                        if (database.isSosFieldData(journeyPlan.getStoreId())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    } else {
                        icon_path = current.getGreyIcon();
                    }
                    break;

                case 5:
                    List<BrandMaster> paidVisibilityData = database.getBrandMasterData(journeyPlan);
                    if(paidVisibilityData.size() > 0) {
                        if (database.isPaidVisibilityFilledData(journeyPlan.getStoreId())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    }else{
                        icon_path = current.getGreyIcon();
                    }
                    break;
                case 6:

                    if (database.isAdditionalVisibilityFilled(journeyPlan.getStoreId())) {
                        icon_path = current.getTickIcon();
                    } else {
                        icon_path = current.getNormalIcon();
                    }
                    break;

                case 7:

                    List<CategoryMaster> listDataHeader = database.getCategoryMasterData(journeyPlan);
                    if(listDataHeader.size() > 0) {
                        if (database.isPromotionFilledData(journeyPlan.getStoreId())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    }else{
                        icon_path = current.getGreyIcon();
                    }

                    break;

                case 8:

                    if (database.isgROOMINGFilled(journeyPlan)) {
                        icon_path = current.getTickIcon();
                    } else {
                        icon_path = current.getNormalIcon();
                    }
                    break;

                case 9:

                    if (database.issampledDataFilled(journeyPlan.getStoreId().toString())) {
                        icon_path = current.getTickIcon();
                    } else {
                        icon_path = current.getNormalIcon();
                    }
                    break;


                case 10:

                    if(database.getTesterStockData(journeyPlan.getStoreTypeId(), journeyPlan.getStateId(), journeyPlan.getStoreCategoryId()).size() >0){
                        if (database.isTesterStockFilled(journeyPlan.getStoreId().toString())) {
                            icon_path = current.getTickIcon();
                        } else {
                            icon_path = current.getNormalIcon();
                        }
                    }else{
                        icon_path = current.getGreyIcon();
                    }
                    break;


            }

            Glide.with(EntryMenuActivity.this)
                    .load(Uri.fromFile(new File(CommonString.FILE_PATH_Downloaded + icon_path)))
                    .apply(new RequestOptions())
                    .into(viewHolder.icon);


            viewHolder.lay_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int menu_id = current.getMenuId();
                    switch (menu_id) {

                        case 1:
                            if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                                if (!database.isClosingDataFilled(journeyPlan.getStoreId())) {
                                    startActivity(new Intent(context, OpeningStock.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                                } else {
                                    Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
                                }
                            }

                            break;

                        case 2:
                            if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                                if (database.isOpeningDataFilled(journeyPlan.getStoreId().toString())) {

                                   // if (!database.isMiddayDataFilled1(journeyPlan.getStoreId())) {
                                    if (!database.isClosingDataFilled(journeyPlan.getStoreId())) {
                                        startActivity(new Intent(context, MidDayStock.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                                    } else {
                                        Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Snackbar.make(recyclerView, "First fill Opening Stock Category in Data", Snackbar.LENGTH_SHORT).show();
                                }

                            }

                            break;

                        case 3:
                            if (database.getmappingStockDataNew(journeyPlan).size() > 0) {
                                if (database.isOpeningDataFilled(journeyPlan.getStoreId().toString())) {
                                    if (database.isMiddayDataFilled1(journeyPlan.getStoreId())) {
                                        startActivity(new Intent(context, ClosingStock.class).putExtra(CommonString.TAG_OBJECT, journeyPlan)
                                                .putExtra(CommonString.KEY_MENU_ID, current)
                                                .putExtra(CommonString.TAG_FROM,getIntent().getSerializableExtra(CommonString.TAG_FROM)));
                                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                                    } else {
                                        Snackbar.make(recyclerView, "First fill Stock Added to Shelf Data", Snackbar.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Snackbar.make(recyclerView, "First  fill Opening Stock Category,Stock Added to Shelf in Data", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                            break;

                        case 4:
                            if (database.getmappingShareOfShelfData().size() > 0) {
                                startActivity(new Intent(context, ShareOfShelfActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            }
                            break;

                        case 5:
                            if (database.getBrandMasterData(journeyPlan).size()>0) {
                                startActivity(new Intent(context, PaidVisibilityActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            }

                            break;

                        case 6:
                                startActivity(new Intent(context, Additonalvisibility.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                            break;

                        case 7:
                            if (database.getCategoryMasterData(journeyPlan).size()>0){
                                startActivity(new Intent(context, PromotionActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            }

                            break;

                        case 8:

                            if (!database.isgROOMINGFilled(journeyPlan)) {
                                startActivity(new Intent(context, SelfieActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            } else {
                                Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
                            }
                            break;

                        case 9:

                            startActivity(new Intent(context, SamplingActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            break;


                        case 10:

                            if(database.getTesterStockData(journeyPlan.getStoreTypeId(), journeyPlan.getStateId(), journeyPlan.getStoreCategoryId()).size() >0){
                                startActivity(new Intent(context, TesterStockActivity.class).putExtra(CommonString.TAG_OBJECT, journeyPlan).putExtra(CommonString.KEY_MENU_ID, current));
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            }

                            break;



                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt;
            ImageView icon;
            LinearLayout lay_menu;

            public MyViewHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.list_txt);
                icon = (ImageView) itemView.findViewById(R.id.list_icon);
                lay_menu = (LinearLayout) itemView.findViewById(R.id.lay_menu);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // NavUtils.navigateUpFromSameTask(this);
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }

        return super.onOptionsItemSelected(item);
    }


    void declaration() {
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        txt_label = (TextView) findViewById(R.id.txt_label);
        recyclerView = (RecyclerView) findViewById(R.id.rec_menu);
        database = new MaricoDatabase(context);
        database.open();
    }

    private boolean chekDataforCheckout(JourneyPlan journeyPlan) {
        boolean status = true;
        database.open();
        ArrayList<MenuMaster> menu_list = database.getMenuData(journeyPlan.getStoreTypeId(), journeyPlan.getStoreCategoryId());
        for (int i = 0; i < menu_list.size(); i++) {
            switch (menu_list.get(i).getMenuId()) {

                case 1:
                    if (!database.isOpeningDataFilled(journeyPlan.getStoreId().toString())) {
                        status = false;
                    }
                    break;
                case 2:
                    if (!database.isMiddayDataFilled1(journeyPlan.getStoreId())) {
                        status = false;
                    }
                    break;

                case 3:
                    if (!database.isClosingDataFilled1(journeyPlan.getStoreId())) {
                        status = false;
                    }
                    break;
                case 4:

                    if (!database.isSosFieldData(journeyPlan.getStoreId())) {
                        status = false;
                    }
                    break;
                case 5:
                    if (!database.isPaidVisibilityFilledData(journeyPlan.getStoreId())) {
                        status = false;
                    }
                    break;

                case 6:
                    if (!database.isAdditionalVisibilityFilled(journeyPlan.getStoreId())) {
                        status = false;
                    }
                    break;
                case 7:
                    if (!database.isPromotionFilledData(journeyPlan.getStoreId())) {
                        status = false;
                    }

                    break;
                case 8:
                    if (!database.isgROOMINGFilled(journeyPlan)) {
                        status = false;
                    }
                    break;
                case 9:
                    if (!database.issampledDataFilled(journeyPlan.getStoreId().toString())) {
                        status = false;
                    }
                    break;



            }
        }

        return status;
    }
}
