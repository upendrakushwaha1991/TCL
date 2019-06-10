package com.cpm.Tcl.dailyEntry;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.adapter.ChecklistAnswerAdapter;
import com.cpm.Tcl.adapter.ReasonSpinnerAdapter;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.ChecklistAnswer;
import com.cpm.Tcl.getterSetter.ChecklistMaster;
import com.cpm.Tcl.getterSetter.DisplayMaster;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.getterSetter.PosmMaster;
import com.cpm.Tcl.utilities.CommonFunctions;
import com.cpm.Tcl.utilities.CommonString;
import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SecondaryVisibilityActivity extends AppCompatActivity {

    MaricoDatabase database;
    JourneyPlan journeyPlan;
    TextView txt_label;
    SharedPreferences preferences;
    String visit_date;

    ExpandableListView expandableListView;
    String error_msg, pathfor_cam_one = "", pathfor_cam_two = "", path;
    boolean error_flag = false;
    FloatingActionButton fab;
    ArrayList<DisplayMaster> displayMasterList;
    HashMap<DisplayMaster, ArrayList<ChecklistMaster>> hashMapListChildData;
    MenuMaster menuMaster;
    ExpandableListAdapter expandableListAdapter;
    int global_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_visibility);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        visit_date = preferences.getString(CommonString.KEY_DATE, null);

        //recyclerView = (RecyclerView) findViewById(R.id.rec_window);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableSecondaryListView);

        txt_label = (TextView) findViewById(R.id.txt_label);

        fab = findViewById(R.id.fab);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
            getSupportActionBar().setTitle("");
            txt_label.setText("Secondary Visibility" + " - " + visit_date);
        }


        database = new MaricoDatabase(getApplicationContext());
        database.open();

        displayMasterList = database.getSecondaryVisibilityInsertedData(String.valueOf(journeyPlan.getStoreId()), journeyPlan.getVisitDate());

        hashMapListChildData = new HashMap<>();

        //if data is already inserted
        if(displayMasterList.size()>0){

            for (int i = 0; i < displayMasterList.size(); i++) {

                //for Display Checklist
                ArrayList<ChecklistMaster> checklist = database.getSecondaryVisibilityCheckListInsertedData(displayMasterList.get(i));

                if(checklist.size()==0){
                    checklist = database.getCheckListData(menuMaster.getMenuId());

                    for (int j = 0; j < checklist.size(); j++) {
                        ArrayList<ChecklistAnswer> checkListAnswer = database.getCheckListAnswer(checklist.get(j).getChecklistId());
                        checklist.get(j).setCheckListAnswer(checkListAnswer);
                    }
                }

                hashMapListChildData.put(displayMasterList.get(i), checklist);
            }
        }
        else {//default data
            displayMasterList = database.getSecondaryVisibilityDisplayData(journeyPlan.getStoreTypeId(), journeyPlan.getStoreCategoryId(), journeyPlan.getStateId());

            for (int i = 0; i < displayMasterList.size(); i++) {

                //for Display Checklist Menu_Id
                ArrayList<ChecklistMaster> checklist = database.getCheckListData(menuMaster.getMenuId());

                for (int j = 0; j < checklist.size(); j++) {
                    ArrayList<ChecklistAnswer> checkListAnswer = database.getCheckListAnswer(checklist.get(j).getChecklistId());
                    checklist.get(j).setCheckListAnswer(checkListAnswer);
                }

                hashMapListChildData.put(displayMasterList.get(i), checklist);
            }
        }


        expandableListAdapter = new ExpandableListAdapter(this, displayMasterList, hashMapListChildData);
        expandableListView.setAdapter(expandableListAdapter);
        for (int i = 0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                expandableListView.invalidate();

                int lastItem = firstVisibleItem + visibleItemCount;

                if (firstVisibleItem == 0) {
                    fab.show();//.setVisibility(View.VISIBLE);
                } else if (lastItem == totalItemCount) {
                    fab.hide();//setVisibility(View.INVISIBLE);
                } else {
                    fab.show();//setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }

                expandableListView.invalidateViews();
            }
        });

        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableListView.clearFocus();
                expandableListAdapter.notifyDataSetChanged();
                if (isDataValid()) {
                    database.open();
                    database.insertSecondaryVisibilityData(journeyPlan, displayMasterList, hashMapListChildData);

                    finish();
                } else {
                    error_flag = true;
                    Snackbar.make(expandableListView, error_msg, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondaryVisibilityActivity.this);
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
        private ArrayList<DisplayMaster> _listDataHeader;
        private HashMap<DisplayMaster, ArrayList<ChecklistMaster>> _listDataChild;

        public ExpandableListAdapter(Context context, ArrayList<DisplayMaster> listDataHeader,
                                     HashMap<DisplayMaster, ArrayList<ChecklistMaster>> listChildData) {
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
            final DisplayMaster current = (DisplayMaster) getGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.display_header_view, null, false);
            }

            TextView tv_brand = (TextView) convertView.findViewById(R.id.tv_display);
            EditText et_stock = (EditText) convertView.findViewById(R.id.et_stock);
            RelativeLayout rel_parent = (RelativeLayout) convertView.findViewById(R.id.rel_parent);
            ImageView cam_one = (ImageView) convertView.findViewById(R.id.cam_one);
            ImageView cam_two = (ImageView) convertView.findViewById(R.id.cam_two);
            final Spinner spin_present = (Spinner) convertView.findViewById(R.id.spin_present);
            final LinearLayout lin_camera = (LinearLayout) convertView.findViewById(R.id.lin_camera);
            final LinearLayout lin_stock = (LinearLayout) convertView.findViewById(R.id.lin_stock);

            et_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (!hasFocus) {
                        final EditText caption = (EditText) v;
                        String edStock = caption.getText().toString();

                        if (!edStock.equals("")) {
                            String stock = edStock.replaceFirst("^0+(?!$)", "");
                            current.setQuantity(stock);

                        } else {
                            current.setQuantity("");

                        }
                    }
                }
            });

            cam_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global_position = groupPosition;
                    String intime = CommonFunctions.getCurrentTime();
                    pathfor_cam_one = journeyPlan.getStoreId() + "_SECONDARY_VISBILITY_DISPLAY_ONE_IMG-" + current.getDisplayId() + "_" + journeyPlan.getVisitDate().replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString.FILE_PATH + pathfor_cam_one;
                    CommonFunctions.startAnncaCameraActivity(SecondaryVisibilityActivity.this, path, null, false);
                }
            });

            cam_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global_position = groupPosition;
                    String intime = CommonFunctions.getCurrentTime();
                    pathfor_cam_two = journeyPlan.getStoreId() + "_SECONDARY_VISBILITY_DISPLAY_TWO_IMG-" + current.getDisplayId() + "_" + journeyPlan.getVisitDate().replace("/", "") + "_" + intime.replace(":", "") + ".jpg";
                    path = CommonString.FILE_PATH + pathfor_cam_two;
                    CommonFunctions.startAnncaCameraActivity(SecondaryVisibilityActivity.this, path, null, false);
                }
            });

            final ArrayList<PosmMaster> reason_list = database.getastockAnswerData();

            spin_present.setAdapter(new ReasonSpinnerAdapter(SecondaryVisibilityActivity.this, R.layout.spinner_text_view, reason_list));

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


                            final PosmMaster ans = reason_list.get(pos);

                            if (pos == 2) {
                                lin_camera.setVisibility(View.VISIBLE);
                                lin_stock.setVisibility(View.VISIBLE);

                                current.setAnswered_id(ans.getAnswerId());

                                //refresh to show hide views according to Present
                                expandableListView.clearFocus();
                                expandableListAdapter.notifyDataSetChanged();

                            } else {

                                if(current.getAnswered_id()==1 && (!current.getImg_long_shot().equals("") || !current.getImg_close_up().equals("") || !current.getQuantity().equals(""))){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondaryVisibilityActivity.this);
                                    builder.setMessage(R.string.DELETE_ALERT_MESSAGE)
                                            .setCancelable(false)
                                            .setPositiveButton(getResources().getString(R.string.yes),
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,
                                                                            int id) {

                                                            current.setImg_long_shot("");
                                                            current.setImg_close_up("");
                                                            current.setQuantity("");

                                                            current.setAnswered_id(ans.getAnswerId());

                                                            lin_camera.setVisibility(View.GONE);
                                                            lin_stock.setVisibility(View.GONE);

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
                                    lin_camera.setVisibility(View.GONE);
                                    lin_stock.setVisibility(View.GONE);
                                    current.setAnswered_id(ans.getAnswerId());

                                    //refresh to show hide views according to Present
                                    expandableListView.clearFocus();
                                    expandableListAdapter.notifyDataSetChanged();
                                }

                            }


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if(current.getAnswered_id()==1){
                lin_camera.setVisibility(View.VISIBLE);
                lin_stock.setVisibility(View.VISIBLE);
            }
            else {
                lin_camera.setVisibility(View.GONE);
                lin_stock.setVisibility(View.GONE);
            }

            if (current.getImg_close_up().equals("")) {

                cam_one.setBackgroundResource(R.mipmap.cs_black);
            } else {
                cam_one.setBackgroundResource(R.mipmap.cs_green);
            }

            if (current.getImg_long_shot().equals("")) {

                cam_two.setBackgroundResource(R.mipmap.ls_black);
            } else {
                cam_two.setBackgroundResource(R.mipmap.ls_green);
            }


            tv_brand.setText(current.getDisplay());
            et_stock.setText(current.getQuantity());

            if (error_flag) {
                if (current.getQuantity() == null || current.getQuantity().equals("") || current.getAnswered_id()==-1 || current.getImg_close_up().equals("") || current.getImg_long_shot().equals("")) {
                    rel_parent.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    rel_parent.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
                }
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
            ViewHolder holder = null;

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
            ChecklistAnswerAdapter customAdapter = new ChecklistAnswerAdapter(getApplicationContext(), R.layout.spinner_text_view, answerList);
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

            if (error_flag) {
                if (childData.getAnswered_cd() == 0) {
                    holder.card_view.setCardBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.card_view.setCardBackgroundColor(getResources().getColor(R.color.white));
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

    boolean isDataValid() {
        boolean flag = true;

        for (int i = 0; i < displayMasterList.size(); i++) {

            if(displayMasterList.get(i).getAnswered_id()==-1){
                error_msg = getResources().getString(R.string.pl_select_present);
                flag = false;
                break;
            }
            else  if(displayMasterList.get(i).getAnswered_id()==1){
                if (displayMasterList.get(i).getQuantity() == null || displayMasterList.get(i).getQuantity().equals("")) {
                    flag = false;
                    error_msg = getString(R.string.fill_quantity);
                    break;
                }else if (displayMasterList.get(i).getImg_close_up().equals("")) {
                    error_msg = getResources().getString(R.string.pls_click_closeup);
                    flag = false;
                    break;
                } else if (displayMasterList.get(i).getImg_long_shot().equals("")) {
                    error_msg = getResources().getString(R.string.pls_click_longshot);
                    flag = false;
                    break;
                } else {
                    ArrayList<ChecklistMaster> checkList = hashMapListChildData.get(displayMasterList.get(i));

                    for (int j = 0; j < checkList.size(); j++) {
                        if (checkList.get(j).getAnswered_cd() == 0) {
                            flag = false;
                            error_msg = getString(R.string.select_answer);
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }
                }
            }

        }
        return flag;
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
                        if (pathfor_cam_one != null && !pathfor_cam_one.equals("")) {

                            saveFile(pathfor_cam_one);
                            displayMasterList.get(global_position).setImg_close_up(pathfor_cam_one);
                            pathfor_cam_one = "";

                        } else if (pathfor_cam_two != null && !pathfor_cam_two.equals("")) {

                            saveFile(pathfor_cam_two);
                            displayMasterList.get(global_position).setImg_long_shot(pathfor_cam_two);
                            pathfor_cam_two = "";
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
}
