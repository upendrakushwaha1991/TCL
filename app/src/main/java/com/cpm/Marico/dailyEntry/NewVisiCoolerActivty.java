package com.cpm.Marico.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.ChecklistAnswer;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.NonExecutionReason;
import com.cpm.Marico.getterSetter.VisiColoersGetterSetter;
import com.cpm.Marico.utilities.AlertandMessages;
import com.cpm.Marico.utilities.CommonFunctions;
import com.cpm.Marico.utilities.CommonString;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewVisiCoolerActivty extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp_present, sp_reason;
    private ImageView image_closeup, image_long_shot;
    FloatingActionButton fab;
    String[] string_present = {"Select", "YES", "NO"};
    String string_present_cd;
    String visit_date, username, _pathforcheck, _pathforcheck2, _path, str, image1 = "", image2 = "";
    MaricoDatabase db;
    Context context;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    VisiColoersGetterSetter visiColoersGetterSetter;
    POSMDeploymentAdapter adapter;
    ArrayList<VisiColoersGetterSetter> deploymentData = new ArrayList<>();
    private ArrayList<ChecklistAnswer> reasonData = new ArrayList<>();
    int indexVal = 0;
    RecyclerView recyclerView;
    private ArrayList<VisiColoersGetterSetter> posmDeploymentData = new ArrayList<>();
    private ArrayList<NonExecutionReason> reasonDataHeader = new ArrayList<>();
    String reason_name, reason_cd;
    private LinearLayout lay_image, lay_image_name, lay_reason;
    String Error_Message = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visi_cooler_activty);
        getId();
    }

    private void getId() {

        lay_image = (LinearLayout) findViewById(R.id.lay_image);
        lay_reason = (LinearLayout) findViewById(R.id.lay_reason);
        lay_image_name = (LinearLayout) findViewById(R.id.lay_image_name);
        sp_present = (Spinner) findViewById(R.id.sp_present);
        sp_reason = (Spinner) findViewById(R.id.sp_reason);
        image_closeup = (ImageView) findViewById(R.id.image_closeup);
        image_long_shot = (ImageView) findViewById(R.id.image_long_shot);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.posm_deployment_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        db = new MaricoDatabase(context);
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        getSupportActionBar().setTitle("Visi Cooler -" + visit_date);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }

        //get data reason sppiner
        reasonDataHeader = db.getPOSMReason(menuMaster.getMenuId());
        fab.setOnClickListener(this);
        image_closeup.setOnClickListener(this);
        image_long_shot.setOnClickListener(this);
        visiColoersGetterSetter = new VisiColoersGetterSetter();
        str = CommonString.FILE_PATH;
        setSppinerData();
        setInsertData();

        //RECYCLER
        posmDeploymentData = db.getVisicoolerSavedData(Integer.valueOf(jcpGetset.getStoreId()), visit_date);
        if (posmDeploymentData.size() > 0) {
            createView(posmDeploymentData);
        } else {
            //set recyclerview data
            posmDeploymentData = db.getVISICOOLER_Data(jcpGetset, menuMaster.getMenuId());
            createView(posmDeploymentData);
            recyclerView.setVisibility(View.VISIBLE);

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (validation()) {
                    db.open();
                    long val = db.insertNewVisiCoolerData(jcpGetset, visiColoersGetterSetter, deploymentData);
                    if (val > 0) {
                        AlertandMessages.showToastMsg(context, "Data Saved");
                        finish();
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    } else {
                        AlertandMessages.showToastMsg(context, "Error in Data Saving");
                    }
                   /* android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setMessage("Do you want to save Data?").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    db.open();
                                    long val = db.insertNewVisiCoolerData(jcpGetset, visiColoersGetterSetter, deploymentData);
                                    if (val > 0) {
                                        AlertandMessages.showToastMsg(context, "Data Saved");
                                        finish();
                                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                                    } else {
                                        AlertandMessages.showToastMsg(context, "Error in Data Saving");
                                    }

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    android.support.v7.app.AlertDialog alert = builder.create();
                    alert.show();*/

                }

                break;
            case R.id.image_closeup:

                _pathforcheck = "_VISI_COOLER_CLOSEUPIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck;
                CommonFunctions.startAnncaCameraActivity(NewVisiCoolerActivty.this, _path, null, false);
                break;
            case R.id.image_long_shot:
                _pathforcheck2 = "_VISI_COOLER_LONGSHOTIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck2;
                CommonFunctions.startAnncaCameraActivity(NewVisiCoolerActivty.this, _path, null, false);

                break;
        }

    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(CommonString.FILE_PATH + _pathforcheck).exists()) {
                        image_closeup.setImageResource(R.mipmap.cs_green);
                        image1 = _pathforcheck;
                        visiColoersGetterSetter.setImage_close_up(image1);

                    }
                    _pathforcheck = "";
                } else if (_pathforcheck2 != null && !_pathforcheck2.equals("")) {
                    if (new File(CommonString.FILE_PATH + _pathforcheck2).exists()) {
                        image_long_shot.setImageResource(R.mipmap.ls_green);
                        image2 = _pathforcheck2;
                        visiColoersGetterSetter.setImage_long_shot(image2);
                    }
                    _pathforcheck2 = "";
                }

                break;

        }

    }

    private void setSppinerData() {

        ArrayAdapter present = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        present.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_present.setAdapter(present);

        sp_present.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_present_cd = String.valueOf(parent.getSelectedItemPosition());
                    visiColoersGetterSetter.setPresent_name(string_present_cd);
                    if (string_present_cd.equalsIgnoreCase("1")) {
                        lay_image.setVisibility(View.VISIBLE);
                        lay_image_name.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        lay_reason.setVisibility(View.GONE);
                        sp_reason.setSelection(0);

                    } else {
                        if (visiColoersGetterSetter.getImage_close_up().equalsIgnoreCase("")){
                            ClearData();
                            lay_image.setVisibility(View.GONE);
                            lay_image_name.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                            lay_reason.setVisibility(View.VISIBLE);
                            visiColoersGetterSetter.setImage_close_up("");
                            visiColoersGetterSetter.setImage_long_shot("");
                            image_closeup.setImageResource(R.mipmap.cs_black);
                            image_long_shot.setImageResource(R.mipmap.ls_black);
                        }
                        else {
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                            builder.setCancelable(false);
                            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE).setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            ClearData();
                                            lay_image.setVisibility(View.GONE);
                                            lay_image_name.setVisibility(View.GONE);
                                            recyclerView.setVisibility(View.GONE);
                                            lay_reason.setVisibility(View.VISIBLE);
                                            visiColoersGetterSetter.setImage_close_up("");
                                            visiColoersGetterSetter.setImage_long_shot("");
                                            image_closeup.setImageResource(R.mipmap.cs_black);
                                            image_long_shot.setImageResource(R.mipmap.ls_black);
                                        }

                                    })
                             .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                            android.support.v7.app.AlertDialog alert = builder.create();
                            alert.show();

                        }


                    }
                } else {
                    ClearData();
                    sp_reason.setSelection(0);
                    visiColoersGetterSetter.setPresent_name("");
                    visiColoersGetterSetter.setImage_close_up("");
                    visiColoersGetterSetter.setImage_long_shot("");
                    image_closeup.setImageResource(R.mipmap.cs_black);
                    image_long_shot.setImageResource(R.mipmap.ls_black);
                    recyclerView.setVisibility(View.GONE);
                    lay_image.setVisibility(View.GONE);
                    lay_image_name.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //reason
        ReasonAdapter customAdapter = new ReasonAdapter(getApplicationContext(), reasonDataHeader);
        sp_reason.setAdapter(customAdapter);

        sp_reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {

                    reason_name = reasonDataHeader.get(position).getReason();
                    reason_cd = String.valueOf(reasonDataHeader.get(position).getReasonId());
                    visiColoersGetterSetter.setReason(reason_name);
                    visiColoersGetterSetter.setReason_cd(reason_cd);
                } else {
                    reason_cd = "0";
                    reason_name = "";
                    visiColoersGetterSetter.setReason(reason_name);
                    visiColoersGetterSetter.setReason_cd(reason_cd);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void setInsertData() {
        db.open();
        visiColoersGetterSetter = db.getNewVisicoolerData(jcpGetset);
        if (visiColoersGetterSetter.getImage_close_up().equalsIgnoreCase("")) {
            image_closeup.setImageResource(R.mipmap.cs_black);

        } else {
            image_closeup.setImageResource(R.mipmap.cs_green);
            visiColoersGetterSetter.setImage_close_up(visiColoersGetterSetter.getImage_close_up());
        }

        if (visiColoersGetterSetter.getImage_long_shot().equalsIgnoreCase("")) {
            image_long_shot.setImageResource(R.mipmap.ls_black);

        } else {
            image_long_shot.setImageResource(R.mipmap.ls_green);
            visiColoersGetterSetter.setImage_long_shot(visiColoersGetterSetter.getImage_long_shot());
        }

        if (!visiColoersGetterSetter.getPresent_name().equalsIgnoreCase("")) {
            if (visiColoersGetterSetter.getPresent_name().equalsIgnoreCase("1")) {
                sp_present.setSelection(1);
            } else {
                sp_present.setSelection(2);
            }
        } else {
            sp_present.setSelection(-1);
        }

        for (int i = 0; i < reasonDataHeader.size(); i++) {
            if (reasonDataHeader.get(i).getReason().equalsIgnoreCase(visiColoersGetterSetter.getReason())) {
                indexVal = i;
                break;
            }
        }
        sp_reason.setSelection(indexVal);


    }

    private class POSMDeploymentAdapter extends RecyclerView.Adapter<POSMDeploymentAdapter.ViewHolder> {
        Context context;

        public POSMDeploymentAdapter(NewVisiCoolerActivty context, ArrayList<VisiColoersGetterSetter> posmDeploymentData) {
            this.context = context;
            deploymentData = posmDeploymentData;

        }

        @NonNull
        @Override
        public POSMDeploymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visi_cooler_list, parent, false);
            return new POSMDeploymentAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final POSMDeploymentAdapter.ViewHolder holder, final int position) {

            holder.cheklist.setText(deploymentData.get(position).getCheklist());
            //set inserted sppiner data
            reasonData = db.getVisicoolereason(Integer.valueOf(deploymentData.get(position).getCheklist_cd()));

            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), reasonData);
            holder.sp_cheklist.setAdapter(customAdapter);

            holder.sp_cheklist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    deploymentData.get(position).setAnswer_cd(String.valueOf(reasonData.get(i).getAnswerId()));
                    deploymentData.get(position).setAnswer(reasonData.get(i).getAnswer());

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            for (int i = 0; i < reasonData.size(); i++) {
                if (reasonData.get(i).getAnswer().equalsIgnoreCase(deploymentData.get(position).getAnswer())) {
                    holder.sp_cheklist.setSelection(i);
                    break;
                }
            }

        }

        @Override
        public int getItemCount() {
            return deploymentData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView cheklist;
            Spinner sp_cheklist;
            CardView card_view;

            public ViewHolder(View itemView) {
                super(itemView);
                cheklist = (TextView) itemView.findViewById(R.id.cheklist);
                sp_cheklist = (Spinner) itemView.findViewById(R.id.sp_cheklist);
                card_view = (CardView) itemView.findViewById(R.id.card_view);

            }
        }
    }


    private void createView(ArrayList<VisiColoersGetterSetter> posmDeploymentData) {
        adapter = new POSMDeploymentAdapter(this, posmDeploymentData);
        recyclerView.setHasFixedSize(true);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    private class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<ChecklistAnswer> reasonData;

        public CustomAdapter(Context context, ArrayList<ChecklistAnswer> reasonData) {
            this.context = context;
            this.reasonData = reasonData;
        }

        @Override
        public int getCount() {
            return reasonData.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, null);
            TextView names = (TextView) view.findViewById(R.id.tv_ans);
            names.setText(reasonData.get(i).getAnswer());
            return view;
        }
    }

    private class ReasonAdapter extends BaseAdapter {

        Context context;
        ArrayList<NonExecutionReason> reasonData;

        public ReasonAdapter(Context context, ArrayList<NonExecutionReason> reasonData) {
            this.context = context;
            this.reasonData = reasonData;
        }

        @Override
        public int getCount() {
            return reasonData.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, null);
            TextView names = (TextView) view.findViewById(R.id.tv_ans);
            names.setText(reasonData.get(i).getReason());
            return view;
        }
    }

    public boolean validation() {

        boolean value = true;
        if (sp_present.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Present");
        } else if (string_present_cd.equalsIgnoreCase("1")) {
            if (visiColoersGetterSetter.getImage_close_up().equalsIgnoreCase("")) {
                value = false;
                showMessage(getString(R.string.pls_click_closeup));
            } else if (visiColoersGetterSetter.getImage_long_shot().equalsIgnoreCase("")) {
                value = false;
                showMessage(getString(R.string.pls_click_longshot));
            } else if (checkValidation(deploymentData)) {
                value = true;
            } else {
                value = false;
                AlertandMessages.showToastMsg(NewVisiCoolerActivty.this, Error_Message);
            }
        } else if (sp_reason.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Reason");
        } else {
            value = true;

        }
        return value;
    }


    public void showMessage(String message) {

        Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show();

    }


    private boolean checkValidation(ArrayList<VisiColoersGetterSetter> deploymentData) {
        Boolean checkflag = true;
        for (int i = 0; i < deploymentData.size(); i++) {
            if (deploymentData.get(i).getAnswer_cd().equalsIgnoreCase("0")) {
                checkflag = false;
                Error_Message = getString(R.string.select_reason_error);
                break;
            } else {
                checkflag = true;
            }
        }

        adapter.notifyDataSetChanged();
        return checkflag;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NewVisiCoolerActivty.this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                            NewVisiCoolerActivty.this.finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ClearData() {
        for (int i = 0; i < posmDeploymentData.size(); i++) {
            posmDeploymentData.get(i).setAnswer("");
            posmDeploymentData.get(i).setAnswer_cd("0");

        }
        adapter.notifyDataSetChanged();

    }


}
