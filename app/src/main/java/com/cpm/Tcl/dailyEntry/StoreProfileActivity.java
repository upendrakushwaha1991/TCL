package com.cpm.Tcl.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.StoreProfileGetterSetter;
import com.cpm.Tcl.getterSetter.StoreTypeMaster;
import com.cpm.Tcl.utilities.AlertandMessages;
import com.cpm.Tcl.utilities.CommonString;

import java.util.ArrayList;

public class StoreProfileActivity extends AppCompatActivity {
    private TextView store_name, address, city;
    private Spinner storeType;
    FloatingActionButton fab;
    private ArrayAdapter<CharSequence> store_visited_adapter;
    MaricoDatabase db;
    ArrayList<StoreTypeMaster> storeVisitedWitheData = new ArrayList<>();
    String store_type, store_type_cd;
    Context context;
    private SharedPreferences preferences;
    String username;
    JourneyPlan journeyPlan;
    StoreProfileGetterSetter storeProfileGetterSetter;
    String tag_from = "";
    String str_name,str_address,str_city,str_visitdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        store_name = (TextView) findViewById(R.id.store_name);
        address = (TextView) findViewById(R.id.address);
        city = (TextView) findViewById(R.id.city);
        storeType = (Spinner) findViewById(R.id.storeType);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        db = new MaricoDatabase(this);
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            str_name=journeyPlan.getStoreName().toString();
            str_address=journeyPlan.getAddress1().toString();
            str_city=journeyPlan.getCity().toString();
            str_visitdate=journeyPlan.getVisitDate().toString();

        }

        if (getIntent().getStringExtra(CommonString.TAG_FROM) != null) {
            tag_from = getIntent().getStringExtra(CommonString.TAG_FROM);
        }
        getSupportActionBar().setTitle("Store Profile ");
        db.open();
        storeVisitedWitheData = db.geVISITED_WITH_MASTERData();

        store_name.setText(journeyPlan.getStoreName());
        address.setText(journeyPlan.getAddress1()+" , "+journeyPlan.getAddress2());
        city.setText(journeyPlan.getCity());

        store_visited_adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        store_visited_adapter.add("Select Store Type");
        for (int i = 0; i < storeVisitedWitheData.size(); i++) {
            store_visited_adapter.add(storeVisitedWitheData.get(i).getStoreType());
        }
        storeType.setAdapter(store_visited_adapter);


        storeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    store_type = storeVisitedWitheData.get(position - 1).getStoreType();
                    store_type_cd = String.valueOf(storeVisitedWitheData.get(position - 1).getStoreTypeId());
                } else {
                    store_type_cd = "0";
                    store_type = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(StoreProfileActivity.this);
                    builder1.setMessage(
                            "Are you sure you want to save your data?")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @SuppressWarnings("resource")
                                        public void onClick(DialogInterface dialog, int id) {
                                            storeProfileGetterSetter=new StoreProfileGetterSetter();
                                            storeProfileGetterSetter.setStore_type(store_type);
                                            storeProfileGetterSetter.setStore_type_cd(store_type_cd);
                                            db.insertStoreProfileData(journeyPlan,storeProfileGetterSetter);
                                            Intent in = new Intent(StoreProfileActivity.this, EntryMenuActivity.class);
                                            in.putExtra(CommonString.TAG_OBJECT, journeyPlan);
                                            in.putExtra(CommonString.TAG_FROM, tag_from);
                                            startActivity(in);
                                            AlertandMessages.showToastMsg(StoreProfileActivity.this, "Data Saved");
                                            finish();
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert1 = builder1.create();
                    alert1.show();
                }


/*
                if (validation()){
                    storeProfileGetterSetter=new StoreProfileGetterSetter();
                    storeProfileGetterSetter.setStore_type(store_type);
                    storeProfileGetterSetter.setStore_type_cd(store_type_cd);
                    db.insertStoreProfileData(journeyPlan,storeProfileGetterSetter);
                    Intent in = new Intent(StoreProfileActivity.this, EntryMenuActivity.class);
                    in.putExtra(CommonString.TAG_OBJECT, journeyPlan);
                    in.putExtra(CommonString.TAG_FROM, tag_from);
                    startActivity(in);
                    finish();
                }
*/
            }
        });
    }


    public boolean validation() {
        boolean value = true;
        if (storeType.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Store Type");
        } else {

            value = true;
        }
        return value;

    }
    public void showMessage(String message) {
        Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show();

    }


}
