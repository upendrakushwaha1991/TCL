package com.cpm.Marico.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.FocusProductGetterSetter;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.utilities.CommonString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FocusProductActivity extends AppCompatActivity {
    JourneyPlan jcpGetset;
    MaricoDatabase db;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    String store_cd, visit_date, username;
    ExpandableListView lvExp_audit;
    FloatingActionButton storeAudit_fab;
    List<FocusProductGetterSetter> listDataHeader;
    List<FocusProductGetterSetter> questionList;
    HashMap<FocusProductGetterSetter, List<FocusProductGetterSetter>> listDataChild;
    ExpandableListAdapter listAdapter;
    boolean checkflag = true;
    ArrayList<Integer> checkHeaderArray = new ArrayList<>();
    String store_id;
    MenuMaster menuMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_product);
        getviewUI();

        storeAudit_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvExp_audit.clearFocus();
                lvExp_audit.invalidateViews();
                listAdapter.notifyDataSetChanged();
                if (validateData(listDataChild, listDataHeader)) {
                    db.open();
                    db.insertSalesStockData(jcpGetset, listDataChild, listDataHeader);
                    finish();
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                } else {

                    Snackbar.make(lvExp_audit, "Please fill Sku Stock", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getviewUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        lvExp_audit=(ExpandableListView)findViewById(R.id.lvExp_audit);
        storeAudit_fab=(FloatingActionButton)findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        username = preferences.getString(CommonString.KEY_USERNAME, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);


        getSupportActionBar().setTitle("Focus Product -" + visit_date);
        db = new MaricoDatabase(this);
        db.open();
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) !=null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            store_id = String.valueOf(jcpGetset.getStoreId());
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        lvExp_audit.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++){
            lvExp_audit.expandGroup(i);
        }

        lvExp_audit.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lvExp_audit.invalidate();

                int lastItem = firstVisibleItem + visibleItemCount;

                if (firstVisibleItem == 0) {
                    storeAudit_fab.show();//.setVisibility(View.VISIBLE);
                } else if (lastItem == totalItemCount) {
                    storeAudit_fab.hide();//setVisibility(View.INVISIBLE);
                } else {
                    storeAudit_fab.show();//setVisibility(View.VISIBLE);
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
                lvExp_audit.invalidateViews();
            }
        });

        // Listview Group click listener
        lvExp_audit.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });

        // Listview Group expanded listener
        lvExp_audit.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
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
        lvExp_audit.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

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
        lvExp_audit.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });


    }

    private void prepareListData() {

        db.open();
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        listDataHeader = db.getHeaderSalesData(jcpGetset);
        if (listDataHeader.size() > 0) {
            for (int i = 0; i < listDataHeader.size(); i++) {
                questionList = db.getSalesStockInsertedData(jcpGetset, listDataHeader.get(i).getBrand_id());
                if (questionList.size() > 0) {
                     storeAudit_fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
                } else {
                    questionList = db.getSalesStockchildData(jcpGetset, listDataHeader.get(i).getBrand_id());
                }
                listDataChild.put(listDataHeader.get(i), questionList); // Header, Child data
            }
        }
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<FocusProductGetterSetter> _listDataHeader;
        private HashMap<FocusProductGetterSetter, List<FocusProductGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<FocusProductGetterSetter> listDataHeader,
                                     HashMap<FocusProductGetterSetter, List<FocusProductGetterSetter>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {

            final FocusProductGetterSetter childText = (FocusProductGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item_focus_product, null);
                holder = new ViewHolder();
                holder.cardView = convertView.findViewById(R.id.card_view);
                holder.ed_Stock = convertView.findViewById(R.id.ed_Stock);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TextView txtListChild = convertView.findViewById(R.id.lblListItem);
            txtListChild.setText(childText.getSku());
            final ViewHolder finalHolder = holder;

            holder.ed_Stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    final EditText Caption = (EditText) v;
                    String value1 = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                    if (value1.equals("")) {
                        _listDataChild.get(listDataHeader.get(groupPosition))
                                .get(childPosition).setStock("");
                    } else {
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setStock(value1);
                    }

                }
            });
            holder.ed_Stock.setText(childText.getStock());

            if (!checkflag) {
                boolean tempflag = false;
                if (_listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getStock().equals("")) {
                    tempflag = true;
                }
                if (tempflag) {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
            } else {
                holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
            }

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
            final FocusProductGetterSetter headerTitle = (FocusProductGetterSetter) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group_focus_product, null);
            }
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setText(headerTitle.getBrand());
            if (!checkflag) {
                if (checkHeaderArray.contains(groupPosition)) {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
                }
            } else {
                lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
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
        EditText ed_Stock;
        CardView cardView;
    }




    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FocusProductActivity.this);
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        FocusProductActivity.this.finish();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(FocusProductActivity.this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                            FocusProductActivity.this.finish();
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
    boolean validateData(HashMap<FocusProductGetterSetter, List<FocusProductGetterSetter>> listDataChild2,
                         List<FocusProductGetterSetter> listDataHeader2) {
        boolean flag = false;
        checkHeaderArray.clear();
        loop1:
        for (int i = 0; i < listDataHeader2.size(); i++) {

            for (int j = 0; j < listDataChild2.get(listDataHeader.get(i)).size(); j++) {
                String stock = listDataChild.get(listDataHeader.get(i)).get(j).getStock();
                if (stock == null || stock.equalsIgnoreCase("")) {
                    if (!checkHeaderArray.contains(i)) {
                        checkHeaderArray.add(i);
                    }
                    flag = false;
                    break;
                }else {
                    flag = true;
                }

            }
            if (!flag) {
                break;
            }
        }
        if (flag) {
            return checkflag = true;
        } else {

            return checkflag = false;
        }

    }

}