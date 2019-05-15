package com.cpm.Marico.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.adapter.ChecklistAnswerAdapter;
import com.cpm.Marico.adapter.NonExecutionAdapter;
import com.cpm.Marico.adapter.ReasonSpinnerAdapter;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.BrandMaster;
import com.cpm.Marico.getterSetter.ChecklistAnswer;
import com.cpm.Marico.getterSetter.ChecklistMaster;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.NonExecutionReason;
import com.cpm.Marico.getterSetter.PosmMaster;
import com.cpm.Marico.utilities.CommonFunctions;
import com.cpm.Marico.utilities.CommonString;
import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CTUActivity extends AppCompatActivity {

    MaricoDatabase database;
    JourneyPlan journeyPlan;
    TextView txt_label;
    SharedPreferences preferences;
    String visit_date, pathfor_close_up = "", pathfor_longshot = "", path;
    ArrayList<BrandMaster> brandList;
    RecyclerView recyclerView;
    int global_position;
    String image;
    HashMap<BrandMaster, ArrayList<ChecklistMaster>> hashMapListChildData;
    MenuMaster menuMaster;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    ArrayList<NonExecutionReason> nonExecutionReason;
    String error_msg;
    boolean error_flag = false;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        visit_date = preferences.getString(CommonString.KEY_DATE, null);

        //recyclerView = (RecyclerView) findViewById(R.id.rec_window);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        fab = findViewById(R.id.fab);

        txt_label = (TextView) findViewById(R.id.txt_label);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
            getSupportActionBar().setTitle("");
            txt_label.setText("CTU" + " - " + visit_date);
        }

        database = new MaricoDatabase(getApplicationContext());
        database.open();

        nonExecutionReason = database.getNonExecutionReason(menuMaster.getMenuId());

        brandList = database.getCTUInsertedData(String.valueOf(journeyPlan.getStoreId()), journeyPlan.getVisitDate());
        hashMapListChildData = new HashMap<>();

        //default data
        if(brandList.size()==0){
            brandList = database.getCTUBrandData(journeyPlan.getStoreId());

            for (int i = 0; i < brandList.size(); i++) {

                ArrayList<ChecklistMaster> checklist = database.getCheckListData(menuMaster.getMenuId());

                for (int j = 0; j < checklist.size(); j++) {
                    ArrayList<ChecklistAnswer> checkListAnswer = database.getCheckListAnswer(checklist.get(j).getChecklistId());
                    checklist.get(j).setCheckListAnswer(checkListAnswer);
                }

                hashMapListChildData.put(brandList.get(i), checklist);
            }
        }
        else {//inserted data

            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
            for (int i = 0; i < brandList.size(); i++) {

                ArrayList<ChecklistMaster> checklist = database.getCTUCheckListInsertedData(brandList.get(i));
                if(checklist.size()==0){
                    checklist = database.getCheckListData(menuMaster.getMenuId());

                    for (int j = 0; j < checklist.size(); j++) {
                        ArrayList<ChecklistAnswer> checkListAnswer = database.getCheckListAnswer(checklist.get(j).getChecklistId());
                        checklist.get(j).setCheckListAnswer(checkListAnswer);
                    }

                }

                hashMapListChildData.put(brandList.get(i), checklist);
            }
        }


        expandableListAdapter = new ExpandableListAdapter(this, brandList, hashMapListChildData);
        expandableListView.setAdapter(expandableListAdapter);
        for (int i = 0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    database.open();
                    database.insertCTUData(journeyPlan, brandList, hashMapListChildData);
                    finish();
                }
                else {
                    error_flag = true;
                    Snackbar.make(expandableListView, error_msg, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(CTUActivity.this);
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CTUActivity.this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                            finish();
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


    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private ArrayList<BrandMaster> _listDataHeader;
        private HashMap<BrandMaster, ArrayList<ChecklistMaster>> _listDataChild;

        public ExpandableListAdapter(Context context, ArrayList<BrandMaster> listDataHeader,
                                     HashMap<BrandMaster, ArrayList<ChecklistMaster>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            final BrandMaster current = (BrandMaster) getGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_window_with_brand, null, false);
            }

            TextView tv_window = (TextView) convertView.findViewById(R.id.tv_window);
            final LinearLayout lay_present_yes = (LinearLayout) convertView.findViewById(R.id.lay_present_yes);
            final LinearLayout lay_reason = (LinearLayout) convertView.findViewById(R.id.lay_reason);
            ImageView cam_close_up = (ImageView) convertView.findViewById(R.id.cam_close_up);
            ImageView cam_long_shot = (ImageView) convertView.findViewById(R.id.cam_long_shot);
            final Spinner spin_present = (Spinner) convertView.findViewById(R.id.spin_present);
            final Spinner spin_reason = (Spinner) convertView.findViewById(R.id.spin_reason);
            Button btn_brand_visibility = (Button) convertView.findViewById(R.id.btn_brand_visibility);
            ImageView ref_image = (ImageView) convertView.findViewById(R.id.ref_image);
            ref_image.setVisibility(View.GONE);
            btn_brand_visibility.setVisibility(View.GONE);

            tv_window.setText(current.getBrand());


            cam_close_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global_position = groupPosition;
                    String intime = CommonFunctions.getCurrentTime();
                    pathfor_close_up = journeyPlan.getStoreId() + "_CTU_CLOSEUP_IMG-" + current.getBrandId() + "_" + journeyPlan.getVisitDate().replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString.FILE_PATH + pathfor_close_up;
                    CommonFunctions.startAnncaCameraActivity(CTUActivity.this, path, null, false);
                }
            });

            cam_long_shot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global_position = groupPosition;
                    String intime = CommonFunctions.getCurrentTime();
                    pathfor_longshot = journeyPlan.getStoreId() + "_CTU_LONGSHOT_IMG-" + current.getBrandId() + "_" + journeyPlan.getVisitDate().replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString.FILE_PATH + pathfor_longshot;
                    CommonFunctions.startAnncaCameraActivity(CTUActivity.this, path, null, false);
                }
            });

            final ArrayList<PosmMaster> reason_list = database.getastockAnswerData();

            spin_present.setAdapter(new ReasonSpinnerAdapter(CTUActivity.this, R.layout.spinner_text_view, reason_list));

            for (int i = 0; i < reason_list.size(); i++) {
                if (reason_list.get(i).getAnswerId() == current.getAnswered_id()) {
                    spin_present.setSelection(i);
                    break;
                }
            }

            final boolean[] userSelect = {false};

            spin_present.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    userSelect[0] = true;
                    return false;
                }
            });

            spin_present.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                    if (userSelect[0]) {
                        userSelect[0] = false;

                        if (pos != -1) {
                            final PosmMaster ans = reason_list.get(pos);

                            if (pos == 2) {

                                current.setAnswered_id(ans.getAnswerId());
                                current.setAnswered(ans.getAnswer());

                                lay_present_yes.setVisibility(View.VISIBLE);
                                lay_reason.setVisibility(View.GONE);
                                //refresh to show hide views according to Present
                                expandableListView.clearFocus();
                                expandableListAdapter.notifyDataSetChanged();

                            } else {

                                if(current.getAnswered_id()==1 && (!current.getImg_long_shot().equals("") || !current.getImg_close_up().equals(""))){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(CTUActivity.this);
                                    builder.setMessage(R.string.DELETE_ALERT_MESSAGE)
                                            .setCancelable(false)
                                            .setPositiveButton(getResources().getString(R.string.yes),
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,
                                                                            int id) {

                                                            current.setImg_long_shot("");
                                                            current.setImg_close_up("");

                                                            lay_present_yes.setVisibility(View.GONE);
                                                            lay_reason.setVisibility(View.VISIBLE);

                                                            current.setAnswered_id(ans.getAnswerId());
                                                            current.setAnswered(ans.getAnswer());

                                                            clearCheckList(current);

                                                            //refresh to show hide views according to Present
                                                            expandableListView.clearFocus();
                                                            expandableListAdapter.notifyDataSetChanged();
                                                        }
                                                    })
                                            .setNegativeButton(getResources().getString(R.string.no),
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,
                                                                            int id) {

                                                            spin_present.setSelection(2);
                                                            dialog.cancel();
                                                        }
                                                    });
                                    AlertDialog alert = builder.create();

                                    alert.show();
                                }
                                else {

                                    current.setAnswered_id(ans.getAnswerId());
                                    current.setAnswered(ans.getAnswer());
                                    clearCheckList(current);
                                    //refresh to show hide views according to Present
                                    expandableListView.clearFocus();
                                    expandableListAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                        else {
                            lay_present_yes.setVisibility(View.GONE);
                            lay_reason.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if(current.getAnswered_id()==1){
                lay_present_yes.setVisibility(View.VISIBLE);
                lay_reason.setVisibility(View.GONE);
            }
            else if(current.getAnswered_id()==0){
                lay_present_yes.setVisibility(View.GONE);
                lay_reason.setVisibility(View.VISIBLE);
                spin_reason.setAdapter(new NonExecutionAdapter(CTUActivity.this, R.layout.spinner_text_view, nonExecutionReason));

                for (int i = 0; i < nonExecutionReason.size(); i++) {
                    if (nonExecutionReason.get(i).getReasonId() == current.getNonExecutionReasonId()) {
                        spin_reason.setSelection(i);
                        break;
                    }
                }

                spin_reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        if (pos != -1) {
                            NonExecutionReason ans = nonExecutionReason.get(pos);
                            current.setNonExecutionReasonId(ans.getReasonId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

            }
            else {
                lay_present_yes.setVisibility(View.GONE);
                lay_reason.setVisibility(View.GONE);
            }

            if (current.getImg_close_up().equals("")) {

                cam_close_up.setBackgroundResource(R.mipmap.cs_black);
            } else {
                cam_close_up.setBackgroundResource(R.mipmap.cs_green);
            }

            if (current.getImg_long_shot().equals("")) {

                cam_long_shot.setBackgroundResource(R.mipmap.ls_black);
            } else {
                cam_long_shot.setBackgroundResource(R.mipmap.ls_green);
            }


            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            //if present yes ChildList is shown else hidden
            if (this._listDataHeader.get(groupPosition).getAnswered_id() == 1) {
                return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
            } else {
                return 0;
            }

        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {

            final ChecklistMaster childData =
                    (ChecklistMaster) getChild(groupPosition, childPosition);
            CTUActivity.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_checklist_view, null, false);

                holder = new ViewHolder();
                holder.cheklist = (TextView) convertView.findViewById(R.id.cheklist);
                holder.sp_cheklist = (Spinner) convertView.findViewById(R.id.sp_cheklist);
                holder.card_view = (CardView) convertView.findViewById(R.id.card_view);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ArrayList<ChecklistAnswer> answerList = childData.getCheckListAnswer();

            holder.cheklist.setText(childData.getChecklist());
            ChecklistAnswerAdapter customAdapter = new ChecklistAnswerAdapter(CTUActivity.this, R.layout.spinner_text_view, answerList);
            holder.sp_cheklist.setAdapter(customAdapter);

            holder.sp_cheklist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    childData.setAnswered_cd(answerList.get(i).getAnswerId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            for (int i = 0; i < answerList.size(); i++) {
                if (answerList.get(i).getAnswerId() == childData.getAnswered_cd()) {
                    holder.sp_cheklist.setSelection(i);
                    break;
                }
            }


            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class ViewHolder {
        TextView cheklist;
        Spinner sp_cheklist;
        CardView card_view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:

                try {
                    if (requestCode == 131) {
                        if (pathfor_close_up != null && !pathfor_close_up.equals("")) {

                            saveFile(pathfor_close_up);
                            brandList.get(global_position).setImg_close_up(pathfor_close_up);
                            pathfor_close_up = "";

                        } else if (pathfor_longshot != null && !pathfor_longshot.equals("")) {

                            saveFile(pathfor_longshot);
                            brandList.get(global_position).setImg_long_shot(pathfor_longshot);
                            pathfor_longshot = "";
                        }
                    }

                    expandableListView.clearFocus();
                    expandableListAdapter.notifyDataSetChanged();

                } catch (Resources.NotFoundException e) {
                    Crashlytics.logException(e);
                    e.printStackTrace();
                }
                break;
        }
    }

    void saveFile(final String path_for_check) {

        if (new File(CommonString.FILE_PATH + path_for_check).exists()) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    Bitmap bmp = BitmapFactory.decodeFile(CommonString.FILE_PATH + path_for_check);
                    Bitmap dest = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                    String dateTime = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system

                    Canvas cs = new Canvas(dest);
                    Paint tPaint = new Paint();
                    tPaint.setTextSize(70);
                    tPaint.setColor(Color.RED);
                    tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    cs.drawBitmap(bmp, 0f, 0f, null);
                    float height = tPaint.measureText("yY");
                    cs.drawText(dateTime, 20f, height + 15f, tPaint);
                    try {
                        dest.compress(Bitmap.CompressFormat.JPEG, 100,
                                new FileOutputStream(new File(CommonString.FILE_PATH + path_for_check)));
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };

            new Thread(runnable).start();//to work in Background

            //camera.setImageDrawable(getResources().getDrawable(R.mipmap.camera_green));
        }
    }

    boolean isValid() {
        boolean flag = true;

        for (int i = 0; i < brandList.size(); i++) {
            BrandMaster brand = brandList.get(i);
            if (brand.getAnswered_id() == -1) {
                error_msg = getResources().getString(R.string.pl_select_present);
                flag = false;
                break;
            } else if (brand.getAnswered_id() == 1) {
                 if (brand.getImg_close_up().equals("")) {
                    error_msg = getResources().getString(R.string.pls_click_closeup);
                    flag = false;
                    break;
                } else if (brand.getImg_long_shot().equals("")) {
                    error_msg = getResources().getString(R.string.pls_click_longshot);
                    flag = false;
                    break;
                } else {
                    ArrayList<ChecklistMaster> checklist = hashMapListChildData.get(brand);

                    for (int j = 0; j < checklist.size(); j++) {
                        if (checklist.get(j).getAnswered_cd() == 0) {
                            error_msg = getResources().getString(R.string.select_answer);
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }
                }
            } else {
                if (brand.getNonExecutionReasonId() == 0) {
                    error_msg = getResources().getString(R.string.pl_select_reason);
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
    void clearCheckList(BrandMaster brand){
        ArrayList<ChecklistMaster> checklist =  hashMapListChildData.get(brand);

        for(int i=0; i<checklist.size();i++){

            ChecklistMaster ch = checklist.get(i);
            ch.setAnswered_cd(0);
        }
    }
}
