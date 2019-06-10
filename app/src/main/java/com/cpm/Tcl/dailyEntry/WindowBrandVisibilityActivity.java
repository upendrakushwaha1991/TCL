package com.cpm.Tcl.dailyEntry;

import android.app.Activity;
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
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.adapter.ChecklistAnswerAdapter;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.BrandMaster;
import com.cpm.Tcl.getterSetter.ChecklistAnswer;
import com.cpm.Tcl.getterSetter.ChecklistMaster;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.WindowMaster;
import com.cpm.Tcl.utilities.CommonString;

import java.util.ArrayList;
import java.util.HashMap;

public class WindowBrandVisibilityActivity extends AppCompatActivity {

    MaricoDatabase database;
    JourneyPlan journeyPlan;
    WindowMaster windowMaster;
    TextView txt_label;
    SharedPreferences preferences;
    String visit_date;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    ArrayList<BrandMaster> brandList;
    HashMap<BrandMaster, ArrayList<ChecklistMaster>> hashMapListChildData;
    String error_msg;
    boolean error_flag = false;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_brand_visibility);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        visit_date = preferences.getString(CommonString.KEY_DATE, null);

        //recyclerView = (RecyclerView) findViewById(R.id.rec_window);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableBrandListView);

        txt_label = (TextView) findViewById(R.id.txt_label);

        fab = findViewById(R.id.fab);

        database = new MaricoDatabase(getApplicationContext());
        database.open();

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            windowMaster = (WindowMaster) getIntent().getSerializableExtra(CommonString.TAG_WINDOW_OBJECT);
            getSupportActionBar().setTitle("");
            txt_label.setText("Brand Visibility" + " - " + windowMaster.getWindow());
        }

        if(windowMaster.getBrandList().size()==0){

            brandList = database.getBrandDefaultData(journeyPlan.getStoreTypeId(), journeyPlan.getStoreCategoryId(), journeyPlan.getStateId(), windowMaster.getWindowId());

            hashMapListChildData = new HashMap<>();

            for(int i=0; i<brandList.size();i++){

                //for Brand Checklist Menu_Id is - 0
                ArrayList<ChecklistMaster> checklist = database.getCheckListData(0);

                for(int j=0;j<checklist.size();j++){
                    ArrayList<ChecklistAnswer> checkListAnswer = database.getCheckListAnswer(checklist.get(j).getChecklistId());
                    checklist.get(j).setCheckListAnswer(checkListAnswer);
                }

                hashMapListChildData.put(brandList.get(i), checklist);
            }
        }
        else {
            hashMapListChildData = windowMaster.getHashMapListChildData();
            brandList = windowMaster.getBrandList();
        }


        expandableListAdapter = new ExpandableListAdapter(this, brandList, hashMapListChildData);
        expandableListView.setAdapter(expandableListAdapter);
        for(int i=0; i < expandableListAdapter.getGroupCount(); i++)
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
            public void onClick(View view) {

                expandableListView.clearFocus();
                expandableListAdapter.notifyDataSetChanged();

                if(isDataValid()){

                    try{
                        Intent returnIntent = getIntent();
                        WindowMaster windowMaster = new WindowMaster();
                        windowMaster.setBrandList(brandList);
                        windowMaster.setHashMapListChildData(hashMapListChildData);
                        returnIntent.putExtra(CommonString.TAG_WINDOW_OBJECT,windowMaster);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }
                    catch (Exception e){
                        String str = e.toString();
                    }

                }
                else {
                    error_flag = true;
                    /*expandableListView.clearFocus();
                    expandableListAdapter.notifyDataSetChanged();*/

                    Snackbar.make(expandableListView,error_msg, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
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
                convertView = infalInflater.inflate(R.layout.brand_header_layout, null, false);
            }

            TextView tv_brand = (TextView) convertView.findViewById(R.id.tv_brand);
            EditText et_stock = (EditText) convertView.findViewById(R.id.et_stock);
            RelativeLayout rel_parent = (RelativeLayout) convertView.findViewById(R.id.rel_parent);

            if(_listDataHeader.size()==1){
                et_stock.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable s) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        current.setQuantity(s.toString());
                    }
                });
            }
            else {
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
            }



            tv_brand.setText(current.getBrand());
            et_stock.setText(current.getQuantity());

            if(error_flag){
                if(current.getQuantity()==null || current.getQuantity().equals("")){
                    rel_parent.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else {
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
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
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
            ChecklistAnswerAdapter customAdapter = new ChecklistAnswerAdapter(WindowBrandVisibilityActivity.this, R.layout.spinner_text_view, answerList);
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

            if(error_flag){
                if(childData.getAnswered_cd()==0){
                    holder.card_view.setCardBackgroundColor(getResources().getColor(R.color.red));
                }
                else {
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

    boolean isDataValid(){
        boolean flag = true;

        for(int i=0; i<brandList.size();i++){

            if(brandList.get(i).getQuantity()==null || brandList.get(i).getQuantity().equals("")){
                flag = false;
                error_msg = getString(R.string.fill_quantity);
                break;
            }
            else {
                ArrayList<ChecklistMaster> checkList = hashMapListChildData.get(brandList.get(i));

                for(int j=0; j<checkList.size();j++){
                    if(checkList.get(j).getAnswered_cd()==0){
                        flag = false;
                        error_msg = getString(R.string.select_answer);
                        break;
                    }
                }
               if(!flag){
                   break;
               }
            }
        }
        return flag;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WindowBrandVisibilityActivity.this);
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

}
