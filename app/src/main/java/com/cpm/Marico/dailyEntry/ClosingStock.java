package com.cpm.Marico.dailyEntry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.cpm.Marico.getterSetter.ClosingStockData;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.StockNewGetterSetter;
import com.cpm.Marico.utilities.CommonString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ClosingStock extends AppCompatActivity implements View.OnClickListener {
    boolean checkflag = true;
    boolean checkpopup = false;
    boolean validate = true;
    boolean flagcoldroom = false;
    boolean flagmccain = false;
    boolean flagstoredf = false;
    List<Integer> checkHeaderArray = new ArrayList<Integer>();
    List<Integer> checkValidHeaderArray = new ArrayList<Integer>();
    List<Integer> checkValidChildArray = new ArrayList<Integer>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    FloatingActionButton btnSave;
    List<StockNewGetterSetter> listDataHeader;
    HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild;
    boolean ischangedflag = false;
    private SharedPreferences preferences;
    ArrayList<StockNewGetterSetter> brandData;
    ArrayList<StockNewGetterSetter> skuData;
    MaricoDatabase db;
    String visit_date, username, intime,tag_from="";
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    ClosingStockData closingStockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing_stock);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        btnSave = (FloatingActionButton) findViewById(R.id.save_btn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new MaricoDatabase(getApplicationContext());
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        intime = preferences.getString(CommonString.KEY_STORE_IN_TIME, "");
        setTitle("Closing Stock- " + visit_date);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
            tag_from   = getIntent().getStringExtra(CommonString.TAG_FROM);
        }

        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++){
            expListView.expandGroup(i);
        }
        btnSave.setOnClickListener(this);

        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                expListView.invalidate();

                int lastItem = firstVisibleItem + visibleItemCount;

                if (firstVisibleItem == 0) {
                    btnSave.show();//.setVisibility(View.VISIBLE);
                } else if (lastItem == totalItemCount) {
                    btnSave.hide();//setVisibility(View.INVISIBLE);
                } else {
                    btnSave.show();//setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                expListView.invalidateViews();

                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }

        });

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
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
                if (getCurrentFocus() != null) {
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

    //Preparing the list data
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        db.open();
        brandData = db.getmappingStockDataNew(jcpGetset);
        if (brandData.size() > 0) {
            // Adding child data
            for (int i = 0; i < brandData.size(); i++) {
                listDataHeader.add(brandData.get(i));
                skuData = db.getClosingStockDataFromDatabase(brandData.get(i).getCategory_cd(), jcpGetset.getStoreId());
                if (skuData.size() > 0) {
                    if (skuData.get(0).getEd_closingFacing() == null || skuData.get(0).getEd_closingFacing().equals("")) {

                    } else {
                        btnSave.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
                    }
                }
                List<StockNewGetterSetter> skulist = new ArrayList<StockNewGetterSetter>();
                for (int j = 0; j < skuData.size(); j++) {
                    skulist.add(skuData.get(j));
                }
                listDataChild.put(listDataHeader.get(i), skulist); // Header, Child data
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save_btn) {
            expListView.clearFocus();
            expListView.invalidateViews();
            if (!checkpopup) {
                flagcoldroom = flagmccain = flagstoredf = false;
                if (validateData(listDataChild, listDataHeader)) {

                    Intent intent = new Intent(ClosingStock.this,CheckOutConfirmationActivity.class);
                    intent.putExtra(CommonString.TAG_OBJECT,jcpGetset);
                    intent.putExtra(CommonString.KEY_MENU_ID, menuMaster);
                    intent.putExtra(CommonString.TAG_FROM, tag_from);

                    closingStockData = new ClosingStockData();
                    closingStockData.setHashMapData(listDataChild);
                    closingStockData.setStockList(listDataHeader);

                    intent.putExtra(CommonString.KEY_LIST, closingStockData);

                    startActivity(intent);
                    //  Snackbar.make(expListView, "Data has been saved", Snackbar.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    finish();
                } else {
                    listAdapter.notifyDataSetChanged();
                    Snackbar.make(expListView, "Please fill all the fields", Snackbar.LENGTH_LONG).show();
                }
            }

        }
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<StockNewGetterSetter> _listDataHeader; // header titles
        private HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<StockNewGetterSetter> listDataHeader, HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listChildData) {
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

        @SuppressLint("NewApi")
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final StockNewGetterSetter childText = (StockNewGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.closing_stock_entry, null);
                holder = new ViewHolder();
                holder.cardView = (CardView) convertView.findViewById(R.id.card_view);
                holder.ed_closingStock = (EditText) convertView.findViewById(R.id.ed_closingStock);
                holder.txt_skuHeader = (TextView) convertView.findViewById(R.id.txt_closingStock_skuHeader);
                holder.txt_openingStock = (TextView) convertView.findViewById(R.id.txt_closingStock_openingStock);
                holder.txt_midValue = (TextView) convertView.findViewById(R.id.txt_closingStock_midValue);
                holder.txt_openingfloreStock = (TextView) convertView.findViewById(R.id.txt_closingStock_openingfloreStock);
                holder.txt_closingStock_br = (TextView) convertView.findViewById(R.id.txt_closingStock_br);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.txt_skuHeader.setText(childText.getSku());

            if (childText.getFocus().equals("1")){
                holder.txt_skuHeader.setTextColor(Color.GREEN);

            }else {
                holder.txt_skuHeader.setTextColor(Color.BLACK);
            }

            holder.txt_midValue.setText("Stock Added to Shelf :" + childText.getEd_midFacing());
            holder.txt_openingStock.setText("Opening Stock: " + childText.getSumofSTOCK());

            final ViewHolder finalHolder = holder;
            holder.ed_closingStock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int consolidateValue = Integer.parseInt(childText.getSumofSTOCK()) + Integer.parseInt(childText.getEd_midFacing());

                    final EditText Caption = (EditText) v;
                    String value1 = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                    if (value1.equals("")) {
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setEd_closingFacing("");
                    } else {
                        int closingS = Integer.parseInt(value1);
                        if (closingS <= consolidateValue) {
                            ischangedflag = true;
                            _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setEd_closingFacing(value1);
                        } else {
                            checkpopup = true;
                            AlertDialog.Builder builder = new AlertDialog.Builder(ClosingStock.this);
                            builder.setMessage("Closing stock cannot be greater than Opening Stock and Stock Added to Shelf.")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            checkpopup = false;
                                            expListView.invalidateViews();
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                            checkpopup = false;
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }

                }
            });

            holder.ed_closingStock.setText(childText.getEd_closingFacing());
            if (!checkflag) {
                boolean tempflag = false;

                if (holder.ed_closingStock.getText().toString().equals("")) {
                    holder.ed_closingStock.setHintTextColor(getResources().getColor(R.color.red));
                    holder.ed_closingStock.setHint("Empty");
                    tempflag = true;
                }

                if (tempflag) {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            if (!validate) {
                if (checkValidHeaderArray.contains(groupPosition)) {
                    if (checkValidChildArray.contains(childPosition)) {
                        boolean tempflag = false;
                        holder.ed_closingStock.setTextColor(getResources().getColor(R.color.black));
                        holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        holder.ed_closingStock.setTextColor(getResources().getColor(R.color.black));
                        holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {
                    holder.ed_closingStock.setTextColor(getResources().getColor(R.color.black));
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
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
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            StockNewGetterSetter headerTitle = (StockNewGetterSetter) getGroup(groupPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.getCategory());

            if (!checkflag) {
                if (checkHeaderArray.contains(groupPosition)) {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
                }
            } else {
                lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
            }


            if (!validate) {
                if (checkValidHeaderArray.contains(groupPosition)) {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
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
        TextView txt_skuHeader, txt_openingStock, txt_midValue, txt_openingfloreStock, txt_closingStock_br;
        EditText ed_closingStock;
        CardView cardView;
    }

    boolean validateData(HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
                         List<StockNewGetterSetter> listDataHeader2) {
        boolean flag = true;
        checkHeaderArray.clear();
        for (int i = 0; i < listDataHeader2.size(); i++) {
            for (int j = 0; j < listDataChild2.get(listDataHeader.get(i)).size(); j++) {
                String coldroom = listDataChild.get(listDataHeader.get(i)).get(j).getEd_closingFacing();
                if (coldroom == null || coldroom.equalsIgnoreCase("")) {
                    if (!checkHeaderArray.contains(i)) {
                        checkHeaderArray.add(i);
                    }
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

        if (flag) {
            checkflag = true;
        } else {
            checkflag = false;
        }

        //expListView
        return checkflag;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClosingStock.this);
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog, int id) {
                        finish();
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());


        return cdate;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ClosingStock.this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(
                                DialogInterface dialog, int id) {
                            finish();
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(
                                DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
