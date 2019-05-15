package com.cpm.Marico.dailyEntry;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.CategoryMaster;
import com.cpm.Marico.getterSetter.ChecklistAnswer;
import com.cpm.Marico.getterSetter.ChecklistMaster;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MappingMenuChecklist;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.utilities.AlertandMessages;
import com.cpm.Marico.utilities.CommonFunctions;
import com.cpm.Marico.utilities.CommonString;
import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SOSActivity extends AppCompatActivity {

    List<Integer> checkHeaderArray = new ArrayList<Integer>();
    boolean checkflag = true,category_img=true;
    private MaricoDatabase database;
    Dialog dialog;
    Context context;
    SharedPreferences preferences;
    FloatingActionButton fab;
    boolean flag = true;
    Toolbar toolbar;
    String _pathforcheck, _path, str;
    private String username, store_id, visit_date, visit_date_formatted,menu_id="";
    String img_str2 = "";
    boolean spinnerTouched = false;
    Bitmap bmp, dest;
    JourneyPlan journeyPlan;
    MenuMaster menuMaster;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<CategoryMaster> listDataHeader;
    HashMap<CategoryMaster, List<CategoryMaster>> listDataChild;
    ArrayList<CategoryMaster> brandData;
    static int grp_position = -1;
    String Error_Message;
    float sum = 0;
    int brand_facing_sum = 0;
    QuesutionAdapter quesutionAdapter;
    ChecklistMaster checklistQuestionObj;
    ArrayList<MappingMenuChecklist>  menuChecklist = new ArrayList<>();
    ArrayList<ChecklistMaster> checklistQuestions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        declaration();
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        for (int i = 0; i < listAdapter.getGroupCount(); i++)
            expListView.expandGroup(i);

        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
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
                expListView.clearFocus();
                expListView.invalidateViews();
            }
        });

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
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
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

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
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });


    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<CategoryMaster, List<CategoryMaster>>();
        database.open();
        listDataHeader = database.getSavedSOSHeaderData(store_id,visit_date);
        if(listDataHeader.size() == 0) {
            listDataHeader = database.getSOSCategoryMasterData(journeyPlan);
        }
        else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
        }
        if (listDataHeader.size() > 0) {
            // Adding child data
            for (int i = 0; i < listDataHeader.size(); i++) {
                database.open();
                brandData = database.getSavedSOSInsertedChildData(listDataHeader.get(i).getCategoryId(),store_id,visit_date);
                if(brandData.size() == 0) {
                    brandData = database.getSOSBrandData(listDataHeader.get(i).getCategoryId());
                }
                listDataChild.put(listDataHeader.get(i), brandData);
            }
        }
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
        visit_date_formatted =  preferences.getString(CommonString.KEY_YYYYMMDD_DATE, "");
        store_id = String.valueOf(journeyPlan.getStoreId());
        visit_date = String.valueOf(journeyPlan.getVisitDate());
        username         =  preferences.getString(CommonString.KEY_USERNAME, null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        expListView = (ExpandableListView) findViewById(R.id.lvExp_backoffice);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SOS - " + visit_date);
        getSupportActionBar().setSubtitle( journeyPlan.getStoreName() + " - " + journeyPlan.getStoreId());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expListView.clearFocus();
                if (validateData(listDataChild, listDataHeader)) {
                    saveData();
                    Snackbar.make(expListView, "Data has been saved", Snackbar.LENGTH_LONG).show();
                    finish();
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                } else {
                    Snackbar.make(expListView, Error_Message, Snackbar.LENGTH_LONG).show();
                }
            }
        });
        str = CommonString.FILE_PATH;
    }



    // @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:

                if (new File(str + _pathforcheck).exists()) {
                    bmp = convertBitmap(str + _pathforcheck);
                    dest = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                    String dateTime = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system

                    Canvas cs = new Canvas(dest);
                    Paint tPaint = new Paint();
                    tPaint.setTextSize(100);
                    tPaint.setColor(Color.RED);
                    tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    cs.drawBitmap(bmp, 0f, 0f, null);
                    float height = tPaint.measureText("yY");
                    cs.drawText(dateTime, 20f, height + 15f, tPaint);
                    try {
                        dest.compress(Bitmap.CompressFormat.JPEG, 100,
                                new FileOutputStream(new File(str + _pathforcheck)));
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {
                        bmp = convertBitmap(str + _pathforcheck);
                        img_str2 = _pathforcheck;
                        expListView.invalidateViews();
                        _pathforcheck= "";

                    } catch (Exception e) {
                        Crashlytics.logException(e);
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static Bitmap convertBitmap(String path) {
        Bitmap bitmap = null;
        BitmapFactory.Options ourOptions = new BitmapFactory.Options();
        ourOptions.inDither = false;
        ourOptions.inPurgeable = true;
        ourOptions.inInputShareable = true;
        ourOptions.inTempStorage = new byte[32 * 1024];
        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fs != null) {
                bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, ourOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }


    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<CategoryMaster> _listDataHeader;
        private HashMap<CategoryMaster, List<CategoryMaster>> _listDataChild;

        public ExpandableListAdapter(Context context, List<CategoryMaster> listDataHeader,
                                     HashMap<CategoryMaster, List<CategoryMaster>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {

            final CategoryMaster childText    = (CategoryMaster) getChild(groupPosition, childPosition);
            final  CategoryMaster headerTitle = (CategoryMaster) getGroup(groupPosition);

            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.sos_child_list, null);
                holder = new ViewHolder();
                holder.brand_facing  = (EditText) convertView.findViewById(R.id.brand_facing_txt);
                holder.brand_txt     = (TextView) convertView.findViewById(R.id.brand_txt);
                holder.chceklist_btn = (Button) convertView.findViewById(R.id.checklist_btn);
                holder.child_card_view = (CardView)convertView.findViewById(R.id.child_card_view);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.brand_txt.setTypeface(null, Typeface.BOLD);
            holder.brand_txt.setText(childText.getBrand());

            final ViewHolder finalHolder = holder;

            holder.brand_facing.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    final EditText Caption = (EditText) view;
                    String value1 = Caption.getText().toString().replaceAll("[&^<>{}'$]", "").replaceFirst("^0+(?!$)", "");
                    finalHolder.brand_facing.setEnabled(true);
                    if(!hasFocus){
                        if (value1.equals("")) {
                            childText.setBrand_Facing("");
                        } else {
                            if (!headerTitle.getCategory_Facing().equalsIgnoreCase("")) {
                                float cat_facing = Float.parseFloat(headerTitle.getCategory_Facing());
                                int brand_facing_sum1 = headerTitle.getBrand_facing_sum() + Integer.parseInt(value1);
                                if (brand_facing_sum1 < cat_facing) {
                                    float percentage = (brand_facing_sum1 / cat_facing) * 100;
                                    // get float value up to two decimal points
                                    DecimalFormat df = new DecimalFormat("0.00");
                                    percentage = Float.parseFloat(df.format(percentage));

                                    headerTitle.setPercentage(String.valueOf(percentage));
                                    childText.setBrand_Facing(value1);
                                    headerTitle.setBrand_facing_sum(brand_facing_sum1);
                                    //  expListView.invalidateViews();
                                }else{
                                    finalHolder.brand_facing.setText("");
                                    childText.setBrand_Facing("");
                                    AlertandMessages.showSnackbarMsg(view, getResources().getString(R.string.brand_facing_category_error));
                                }
                            }else{
                                childText.setBrand_Facing(value1);
                            }
                        }
                    }
                    else{
                        if(headerTitle.getCategory_Facing().equalsIgnoreCase("")){
                            AlertandMessages.showSnackbarMsg(view, getResources().getString(R.string.cat_facing_error));
                            finalHolder.brand_facing.setEnabled(false);
                        }else if(!childText.getBrand_Facing().equalsIgnoreCase("")){
                            int brandFacingSum = Integer.valueOf(headerTitle.getBrand_facing_sum()) -Integer.valueOf(childText.getBrand_Facing());
                            headerTitle.setBrand_facing_sum(brandFacingSum);
                        }else{
                            finalHolder.brand_facing.setEnabled(true);
                        }
                        //headerTitle.setBrand_facing_sum(0);
                    }
                }
            });


            holder.brand_facing.setText(childText.getBrand_Facing());

            if(childText.getChecklistQuestions().size() >0){
                for(int i=0;i<childText.getChecklistQuestions().size();i++){
                    if(!childText.getChecklistQuestions().get(i).getCorrectAnswer_Id().equalsIgnoreCase("0")) {
                        if (childText.getChecklistQuestions().get(i).getBrand_Id().equalsIgnoreCase(childText.getBrand_Id())) {
                            holder.chceklist_btn.setBackgroundColor(getApplication().getResources().getColor(R.color.green));
                        } else {
                            holder.chceklist_btn.setBackgroundColor(getApplication().getResources().getColor(R.color.ColorPrimaryLight));
                        }
                    }else{
                        holder.chceklist_btn.setBackgroundColor(getApplication().getResources().getColor(R.color.ColorPrimaryLight));
                    }
                }
            }else{
                holder.chceklist_btn.setBackgroundColor(getApplication().getResources().getColor(R.color.ColorPrimaryLight));
            }


            holder.chceklist_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flag = true;
                    database.open();
                    checklistQuestions = childText.getChecklistQuestions();
                    if(checklistQuestions.size() == 0){
                        database.open();
                        menuChecklist = database.getCheckListId(menu_id);
                        if (menuChecklist.size() > 0) {
                            for (int i = 0; i < menuChecklist.size(); i++) {
                                checklistQuestionObj = database.getCheckListQuestions(menuChecklist.get(i).getChecklistId());
                                checklistQuestions.add(checklistQuestionObj);
                            }
                        }
                        if (checklistQuestions.size() > 0) {
                            for (int i = 0; i < checklistQuestions.size(); i++) {
                                checklistQuestions.get(i).setChecklistAnswers(database.getCheckListQuestionsAnswer(checklistQuestions.get(i).getChecklistId()));
                            }
                        } else {
                            AlertandMessages.showToastMsg(SOSActivity.this, "Data not found for checklist");
                        }
                    }else{
                        if (checklistQuestions.size() > 0) {
                            for (int i = 0; i < checklistQuestions.size(); i++) {
                                checklistQuestions.get(i).setChecklistAnswers(database.getCheckListQuestionsAnswer(checklistQuestions.get(i).getChecklistId()));
                            }
                        } else {
                            AlertandMessages.showToastMsg(SOSActivity.this, "Data not found for checklist");
                        }
                    }
                    dialog = new Dialog(SOSActivity.this);
                    dialog.setContentView(R.layout.checklist_dialog); //layout for dialog
                    dialog.setTitle("CheckList");
                    dialog.setCancelable(false); //none-dismiss when touching outside Dialog

                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    final int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                    dialog.getWindow().setLayout(width, height);

                    Button okBtn = (Button) dialog.findViewById(R.id.ok_btn);
                    Button cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn);
                    RecyclerView checklistView = (RecyclerView) dialog.findViewById(R.id.checklist_view);

                    quesutionAdapter = new QuesutionAdapter(context, checklistQuestions,childText.getBrand_Id(),_listDataHeader.get(groupPosition).getCategoryId());
                    checklistView.setAdapter(quesutionAdapter);
                    checklistView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (checkDataFiled(checklistQuestions)) {
                                childText.setChecklistQuestions(checklistQuestions);
                                dialog.dismiss();
                                expListView.clearFocus();
                                expListView.invalidateViews();
                                listAdapter.notifyDataSetChanged();
                            }else{
                                AlertandMessages.showSnackbarMsg(view,Error_Message);
                            }
                        }
                    });

                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });

            if(checkHeaderArray.contains(groupPosition)) {
                if (!checkflag) {
                    holder.child_card_view.setCardBackgroundColor(getResources().getColor(R.color.red));
                }else holder.child_card_view.setCardBackgroundColor(getResources().getColor(R.color.white));
            }else holder.child_card_view.setCardBackgroundColor(getResources().getColor(R.color.white));

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
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

            final  CategoryMaster headerTitle = (CategoryMaster) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group_sos, null);
            }

            TextView lblListHeader  = (TextView) convertView.findViewById(R.id.lblListHeader);
            ImageView cam_img       = (ImageView) convertView.findViewById(R.id.cam_img);
            CardView cardView       = (CardView) convertView.findViewById(R.id.card_view);
            EditText facingTxt      = (EditText)convertView.findViewById(R.id.header_facing_txt);
            LinearLayout groupView  = (LinearLayout)convertView.findViewById(R.id.group_ll_view);

            TextView SOSPer         = (TextView) convertView.findViewById(R.id.sos_per);

            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.getCategory());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expListView.isGroupExpanded(groupPosition)) {
                        expListView.collapseGroup(groupPosition);
                    } else {
                        expListView.expandGroup(groupPosition);
                    }
                }
            });

            SOSPer.setText("SOS % : " +headerTitle.getPercentage());

            facingTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        grp_position = groupPosition;
                        final EditText Caption = (EditText) view;
                        String value = Caption.getText().toString().replaceAll("[&^<>{}'$]", "").replaceFirst("^0+(?!$)", "");
                        if (value.equals("")) {
                            headerTitle.setCategory_Facing("");
                        } else {
                            headerTitle.setCategory_Facing(value);
                        }
                    }
                }
            });

            facingTxt.setText(headerTitle.getCategory_Facing());

            if (!img_str2.equalsIgnoreCase("")) {
                if (grp_position == groupPosition) {
                    headerTitle.setCategory_Image(img_str2);
                    img_str2 = "";
                }
            }

            if (headerTitle.getCategory_Image() != null && !headerTitle.getCategory_Image().equals("")) {
                cam_img.setBackgroundResource(R.mipmap.camera_green);
            } else {
                cam_img.setBackgroundResource(R.mipmap.camera_pink);
            }

            if (!checkflag) {
                if (checkHeaderArray.contains(groupPosition)) {
                    groupView.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    groupView.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
                }
            } else {
                groupView.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
            }

            cam_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grp_position = groupPosition;
                    _pathforcheck =  store_id + "_" + username.replace(".", "") + "_SOS_IMAGE-" + visit_date_formatted + "-" + CommonFunctions.getCurrentTimeHHMMSS() + ".jpg";
                    _path = CommonString.FILE_PATH + _pathforcheck;
                    CommonFunctions.startAnncaCameraActivity(context, _path, null,false);
                    // startCameraActivity();
                    getCurrentFocus().clearFocus();
                }
            });

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
        TextView brand_txt;
        EditText brand_facing;
        Button chceklist_btn;
        CardView child_card_view;

    }

    private class QuesutionAdapter extends RecyclerView.Adapter<QuesutionAdapter.ViewHolder> {

        private Context context;
        int itemPos = 0;
        List<ChecklistMaster> data = new ArrayList<>();
        String brand_id="0";
        int  categoryId = 0;

        public QuesutionAdapter(Context context, List<ChecklistMaster> data, String brand_id, Integer categoryId) {
            this.context = context;
            this.data = data;
            this.brand_id = brand_id;
            this.categoryId = categoryId;
        }

        @NonNull
        @Override
        public QuesutionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list, parent, false);
            return new QuesutionAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final QuesutionAdapter.ViewHolder holder, final int position) {
            final ChecklistMaster object = data.get(position);

            holder.question.setText(object.getChecklist());

            holder.answer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    System.out.println("Real touch felt.");
                    spinnerTouched = true;
                    return false;
                }
            });

            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), object.getChecklistAnswers());
            holder.answer.setAdapter(customAdapter);
            holder.answer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
                    if (spinnerTouched) {
                        if (pos != 0) {
                            itemPos = pos;
                            checklistQuestions.get(position).setCorrectAnswer_Id(String.valueOf(object.getChecklistAnswers().get(itemPos).getAnswerId()));
                            quesutionAdapter.notifyDataSetChanged();
                        } else {
                            object.setCorrectAnswer_Id("0");
                        }
                    }
                    spinnerTouched = false;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            object.setBrand_Id(brand_id);
            object.setCategory_Id(String.valueOf(categoryId));

            if (!data.get(position).getCorrectAnswer_Id().equals("0"))
            {
                for (int j = 0; j < object.getChecklistAnswers().size(); j++) {
                    // compare correct answer cd with answer cd of question
                    if (Integer.parseInt(data.get(position).getCorrectAnswer_Id()) == object.getChecklistAnswers().get(j).getAnswerId()) {
                        holder.answer.setSelection(j);
                    }
                }
            }

            if (!flag)
            {
                boolean checkFlag = true;
                if(checklistQuestions.get(position).getCorrectAnswer_Id().equalsIgnoreCase("0")) {
                    checkFlag = false;
                }
                if (!checkFlag) {
                    holder.ll_view.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.ll_view.setBackgroundColor(getResources().getColor(R.color.white));
                }
            } else {
                holder.ll_view.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView question;
            Spinner answer;
            CardView question_view;
            LinearLayout ll_view;

            public ViewHolder(View itemView) {
                super(itemView);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.spinner_ans);
                question_view = itemView.findViewById(R.id.question_view);
                ll_view = itemView.findViewById(R.id.ll_view);
            }
        }
    }


    private class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<ChecklistAnswer> answerList;
        LayoutInflater inflter;

        public CustomAdapter(Context context, ArrayList<ChecklistAnswer> answerList) {
            this.context = context;
            this.answerList = answerList;
            inflter = (LayoutInflater.from(context));
        }

        @Override
        public int getCount() {
            return answerList.size();
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
            view = inflter.inflate(R.layout.answer_items, null);
            TextView names = (TextView) view.findViewById(R.id.answer);
            names.setText(answerList.get(i).getAnswer());
            return view;
        }
    }


    private boolean checkDataFiled(ArrayList<ChecklistMaster> checklistQuestions) {
        flag = true;
        for (int i = 0; i < checklistQuestions.size(); i++) {
            if (checklistQuestions.get(i).getCorrectAnswer_Id().equalsIgnoreCase("0")) {
                flag = false;
                Error_Message = getString(R.string.answer_error);
                break;
            }
        }
        quesutionAdapter.notifyDataSetChanged();
        return flag;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            if ( dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }


    boolean validateData(HashMap<CategoryMaster, List<CategoryMaster>> listDataChild2, List<CategoryMaster> listDataHeader2) {
        checkHeaderArray.clear();
        category_img  = true;
        checkflag = true;

        for (int i = 0; i < listDataHeader2.size(); i++) {
            if (listDataHeader2.get(i).getCategory_Facing().equalsIgnoreCase("")) {
                checkflag = false;
                Error_Message = getResources().getString(R.string.cat_facing_error);
            } else if (listDataHeader2.get(i).getCategory_Image().equalsIgnoreCase("")) {
                checkflag = false;
                Error_Message = getResources().getString(R.string.cat_img_error);
            }else {
                for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i)).size(); j++) {
                    if(listDataChild2.get(listDataHeader2.get(i)).get(j).getBrand_Facing().equalsIgnoreCase("")) {
                        checkflag = false;
                        Error_Message = getResources().getString(R.string.brand_facing_error);
                    }else if(Integer.parseInt(listDataChild2.get(listDataHeader2.get(i)).get(j).getBrand_Facing()) > 0){

                        brand_facing_sum = brand_facing_sum + Integer.parseInt(listDataChild2.get(listDataHeader2.get(i)).get(j).getBrand_Facing());

                        if(brand_facing_sum >  Integer.parseInt(listDataHeader2.get(i).getCategory_Facing())) {
                            listDataChild2.get(listDataHeader2.get(i)).get(j).setBrand_Facing("");
                            checkflag = false;
                            Error_Message = getResources().getString(R.string.brand_facing_category_error);
                        }
                        else if (listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() == 0 || listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() > 0) {
                            if(listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() > 0) {
                                float percentage = (brand_facing_sum /  Float.parseFloat(listDataHeader2.get(i).getCategory_Facing())) * 100;
                                DecimalFormat df = new DecimalFormat("0.00");
                                percentage = Float.parseFloat(df.format(percentage));
                                listDataHeader2.get(i).setPercentage(String.valueOf(percentage));
                                for (int k = 0; k < listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size(); k++) {
                                    String correct_answer_cd = listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().get(k).getCorrectAnswer_Id();
                                    if (correct_answer_cd.equalsIgnoreCase("0")) {
                                        checkflag = false;
                                        Error_Message = getResources().getString(R.string.checklist_error);
                                        break;
                                    }
                                }
                            }else{
                                float percentage = (brand_facing_sum /  Float.parseFloat(listDataHeader2.get(i).getCategory_Facing())) * 100;
                                listDataHeader2.get(i).setPercentage(String.valueOf(percentage));
                                checkflag = false;
                                Error_Message = getResources().getString(R.string.checklist_error);
                            }
                        }  else {
                            float percentage = (brand_facing_sum /  Float.parseFloat(listDataHeader2.get(i).getCategory_Facing())) * 100;
                            listDataHeader2.get(i).setPercentage(String.valueOf(percentage));
                            checkflag = true;
                        }
                    }else if (listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() == 0 || listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() > 0) {
                        if(listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size() > 0) {
                            for (int k = 0; k < listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().size(); k++) {
                                String correct_answer_cd = listDataChild2.get(listDataHeader2.get(i)).get(j).getChecklistQuestions().get(k).getCorrectAnswer_Id();
                                if (correct_answer_cd.equalsIgnoreCase("0")) {
                                    checkflag = false;
                                    Error_Message = getResources().getString(R.string.checklist_error);
                                    break;
                                }
                            }
                        }else{
                            checkflag = false;
                            Error_Message = getResources().getString(R.string.checklist_error);
                        }
                    }  else {
                        checkflag = true;
                    }
                }
                brand_facing_sum = 0;
            }

            if (checkflag == false) {
                if (!checkHeaderArray.contains(i)) {
                    checkHeaderArray.add(i);
                }
                break;
            }
        }
        expListView.invalidateViews();
        listAdapter.notifyDataSetChanged();
        return checkflag;
    }


    public void saveData() {

        long i1 = database.insertSOSCompleteData(listDataChild,listDataHeader,store_id,menu_id, visit_date,username);
        if (i1 > 0) {
            AlertandMessages.showToastMsg(SOSActivity.this,"Data saved successfully");
            finish();
        } else {
            AlertandMessages.showToastMsg(SOSActivity.this,"Data not saved");
        }
    }


    @Override
    public void onBackPressed() {
        new AlertandMessages(SOSActivity.this, null, null, null).backpressedAlert();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            new AlertandMessages(SOSActivity.this, null, null, null).backpressedAlert();

        }

        return super.onOptionsItemSelected(item);
    }
}
