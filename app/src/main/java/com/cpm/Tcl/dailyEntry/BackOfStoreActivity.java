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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.BackofStoreGetterSetter;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.utilities.AlertandMessages;
import com.cpm.Tcl.utilities.CommonFunctions;
import com.cpm.Tcl.utilities.CommonString;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class BackOfStoreActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton storeAudit_fab;
    private Spinner sp_present;
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
    private LinearLayout lay_image, lay_image_name;
    String Error_Message = "";
    ExpandableListView lvExp_audit;
    BackofStoreGetterSetter backofstoregs;
    ArrayList<Integer> checkHeaderArray = new ArrayList<>();
    String store_id;
    List<BackofStoreGetterSetter> listDataHeader;
    List<BackofStoreGetterSetter> questionList;
    HashMap<BackofStoreGetterSetter, List<BackofStoreGetterSetter>> listDataChild;
    ExpandableListAdapter listAdapter;
    boolean checkflag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_of_store);
        getId();

    }

    private void getId() {

        storeAudit_fab = (FloatingActionButton) findViewById(R.id.fab);
        lay_image = (LinearLayout) findViewById(R.id.lay_image);
        lay_image_name = (LinearLayout) findViewById(R.id.lay_image_name);
        sp_present = (Spinner) findViewById(R.id.sp_present);
        image_closeup = (ImageView) findViewById(R.id.image_closeup);
        image_long_shot = (ImageView) findViewById(R.id.image_long_shot);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvExp_audit = (ExpandableListView) findViewById(R.id.lvExp_audit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        db = new MaricoDatabase(context);
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        getSupportActionBar().setTitle("Back Of Store -" + visit_date);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }
        fab.setOnClickListener(this);
        image_closeup.setOnClickListener(this);
        image_long_shot.setOnClickListener(this);
        backofstoregs = new BackofStoreGetterSetter();
        str = CommonString.FILE_PATH;
        setSppinerData();
        setInsertData();

        //expend list
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        lvExp_audit.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            lvExp_audit.expandGroup(i);
        }

        setExpendablelistData();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fab:
                lvExp_audit.clearFocus();
                lvExp_audit.invalidateViews();
                if (validation()) {
                    db.open();
                    long val = db.insertBackofStoreData(jcpGetset, backofstoregs);
                    if (val > 0) {
                        db.InsertBackofStoreData(jcpGetset, listDataChild, listDataHeader);
                        AlertandMessages.showToastMsg(context, "Data Saved");
                        finish();
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    } else {
                        AlertandMessages.showToastMsg(context, "Error in Data Saving");
                    }
                    /*android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setMessage("Do you want to save Data?").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    db.open();
                                    long val = db.insertBackofStoreData(jcpGetset, backofstoregs);
                                    if (val > 0) {
                                        db.InsertBackofStoreData(jcpGetset, listDataChild, listDataHeader);
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

                _pathforcheck = "_BACK_OF_STORE_CLOSEUPIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck;
                CommonFunctions.startAnncaCameraActivity(BackOfStoreActivity.this, _path, null, false);

                break;
            case R.id.image_long_shot:
                _pathforcheck2 = "_BACK_OF_STORE_LONGSHOTIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck2;
                CommonFunctions.startAnncaCameraActivity(BackOfStoreActivity.this, _path, null, false);

                break;
        }

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
                        backofstoregs.setImage_close_up(image1);

                    }
                    _pathforcheck = "";
                } else if (_pathforcheck2 != null && !_pathforcheck2.equals("")) {
                    if (new File(CommonString.FILE_PATH + _pathforcheck2).exists()) {
                        image_long_shot.setImageResource(R.mipmap.ls_green);
                        image2 = _pathforcheck2;
                        backofstoregs.setImage_long_shot(image2);
                    }
                    _pathforcheck2 = "";
                }

                break;

        }

    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
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
                    backofstoregs.setPresent_name(string_present_cd);
                    if (string_present_cd.equalsIgnoreCase("1")) {
                        lay_image.setVisibility(View.VISIBLE);
                        lay_image_name.setVisibility(View.VISIBLE);
                        lvExp_audit.setVisibility(View.VISIBLE);

                    } else {
                        if (backofstoregs.getImage_close_up().equalsIgnoreCase("")) {
                            clearValidateData();
                            lay_image.setVisibility(View.GONE);
                            lay_image_name.setVisibility(View.GONE);
                            lvExp_audit.setVisibility(View.GONE);
                            backofstoregs.setImage_close_up("");
                            backofstoregs.setImage_long_shot("");
                            image_closeup.setImageResource(R.mipmap.cs_black);
                            image_long_shot.setImageResource(R.mipmap.ls_black);

                        } else {
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                            builder.setCancelable(false);
                            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE).setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            clearValidateData();
                                            lay_image.setVisibility(View.GONE);
                                            lay_image_name.setVisibility(View.GONE);
                                            lvExp_audit.setVisibility(View.GONE);
                                            backofstoregs.setImage_close_up("");
                                            backofstoregs.setImage_long_shot("");
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

                    clearValidateData();
                    backofstoregs.setPresent_name("");
                    backofstoregs.setImage_close_up("");
                    backofstoregs.setImage_long_shot("");
                    image_closeup.setImageResource(R.mipmap.cs_black);
                    image_long_shot.setImageResource(R.mipmap.ls_black);
                    lvExp_audit.setVisibility(View.GONE);
                    lay_image.setVisibility(View.GONE);
                    lay_image_name.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setInsertData() {
        db.open();
        backofstoregs = db.getBackofStoreData(jcpGetset);
        if (backofstoregs.getImage_close_up().equalsIgnoreCase("")) {
            image_closeup.setImageResource(R.mipmap.cs_black);

        } else {
            image_closeup.setImageResource(R.mipmap.cs_green);
            backofstoregs.setImage_close_up(backofstoregs.getImage_close_up());
        }

        if (backofstoregs.getImage_long_shot().equalsIgnoreCase("")) {
            image_long_shot.setImageResource(R.mipmap.ls_black);

        } else {
            image_long_shot.setImageResource(R.mipmap.ls_green);
            backofstoregs.setImage_long_shot(backofstoregs.getImage_long_shot());
        }
        if (!backofstoregs.getPresent_name().equalsIgnoreCase("")) {
            if (backofstoregs.getPresent_name().equalsIgnoreCase("1")) {
                sp_present.setSelection(1);
            } else {
                sp_present.setSelection(2);
            }
        } else {
            sp_present.setSelection(-1);
        }
    }

    public boolean validation() {
        boolean value = true;
        if (sp_present.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Present");

        } else if (string_present_cd.equalsIgnoreCase("1")) {
            if (backofstoregs.getImage_close_up().equalsIgnoreCase("")) {
                value = false;
                showMessage("Please Capture Close Up photo ");
            } else if (backofstoregs.getImage_long_shot().equalsIgnoreCase("")) {
                value = false;
                showMessage("Please Capture Long shot photo ");
            } else if (validateData(listDataChild, listDataHeader)) {

                value = true;
            } else {
                value = false;
                AlertandMessages.showToastMsg(BackOfStoreActivity.this, Error_Message);
            }
        } else {
            value = true;

        }
        return value;
    }

    public void showMessage(String message) {

        Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show();

    }

    private void prepareListData() {
        db.open();
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader = db.getInsertBackofStoreHeaderData(jcpGetset);
        if (!(listDataHeader.size() > 0)) {
            listDataHeader = db.getHeaderBackofStoreData(jcpGetset);
        }
        if (listDataHeader.size() > 0) {
            for (int i = 0; i < listDataHeader.size(); i++) {
                questionList = db.getChildCheklistBackofStoreInsertData(jcpGetset, listDataHeader.get(i).getCommon_id());
                if (questionList.size() > 0) {
                    storeAudit_fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
                } else {
                    questionList = db.getBackofStoreChildData(menuMaster);
                }
                listDataChild.put(listDataHeader.get(i), questionList); // Header, Child data
            }
        }
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<BackofStoreGetterSetter> _listDataHeader;
        private HashMap<BackofStoreGetterSetter, List<BackofStoreGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<BackofStoreGetterSetter> listDataHeader,
                                     HashMap<BackofStoreGetterSetter, List<BackofStoreGetterSetter>> listChildData) {
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

            final BackofStoreGetterSetter childText = (BackofStoreGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item_backofstore_child, null);
                holder = new ViewHolder();
                holder.cardView = convertView.findViewById(R.id.card_view);
                holder.Spinner = convertView.findViewById(R.id.sppiner_cheklist);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TextView txtListChild = convertView.findViewById(R.id.lblListItem);
            txtListChild.setText(childText.getChecklist());
            final ViewHolder finalHolder = holder;

            //set Sppiner Answer
            final ArrayList<BackofStoreGetterSetter> reason_list = db.getReasonBackofStore_Data(childText.getChecklist_id());

            BackofStoreGetterSetter non = new BackofStoreGetterSetter();
            non.setReason("-Select Answer-");
            non.setReasonId(0);
            reason_list.add(0, non);
            holder.Spinner.setAdapter(new ReasonSpinnerAdapter(_context, R.layout.spinner_text_view, reason_list));
            for (int i = 0; i < reason_list.size(); i++) {
                if (reason_list.get(i).getReasonId().toString().equals(childText.getAnswerId().toString())) {
                    holder.Spinner.setSelection(i);
                    break;
                }
            }
            holder.Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (pos != 0) {
                        BackofStoreGetterSetter ans = reason_list.get(pos);
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setAnswerId(ans.getReasonId());
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setAnswer(ans.getReason().toString());

                    } else {
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setAnswerId(0);
                        _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setAnswer("");

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            if (!checkflag) {
                boolean tempflag = false;
                if (_listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getAnswer().equals("")) {
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
            final BackofStoreGetterSetter headerTitle = (BackofStoreGetterSetter) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group_backofstore, null);
            }
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            EditText ed_stock = (EditText) convertView.findViewById(R.id.ed_Stock);

            ed_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    final EditText Caption = (EditText) v;
                    String value1 = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                    if (value1.equals("")) {
                        headerTitle.setStock("");
                    } else {
                        headerTitle.setStock(value1);

                    }

                }
            });
            ed_stock.setText(headerTitle.getStock());
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
        Spinner Spinner;
        CardView cardView;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BackOfStoreActivity.this);
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        BackOfStoreActivity.this.finish();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(BackOfStoreActivity.this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                            BackOfStoreActivity.this.finish();
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


    private void setExpendablelistData() {

        listAdapter = new ExpandableListAdapter(BackOfStoreActivity.this, listDataHeader, listDataChild);
        lvExp_audit.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++)
            lvExp_audit.expandGroup(i);

        lvExp_audit.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                lvExp_audit.invalidate();

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

                lvExp_audit.invalidateViews();
                if (SCROLL_STATE_TOUCH_SCROLL == arg1) {
                    View currentFocus = getCurrentFocus();
                    if (currentFocus != null) {
                        currentFocus.clearFocus();
                    }

                }
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


    public class ReasonSpinnerAdapter extends ArrayAdapter<BackofStoreGetterSetter> {
        List<BackofStoreGetterSetter> list;
        Context context;
        int resourceId;

        public ReasonSpinnerAdapter(Context context, int resourceId, ArrayList<BackofStoreGetterSetter> list) {
            super(context, resourceId, list);
            this.context = context;
            this.list = list;
            this.resourceId = resourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
            BackofStoreGetterSetter cm = list.get(position);
            TextView txt_spinner = (TextView) view.findViewById(R.id.txt_sp_text);
            txt_spinner.setText(list.get(position).getReason());

            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
            BackofStoreGetterSetter cm = list.get(position);
            TextView txt_spinner = (TextView) view.findViewById(R.id.txt_sp_text);
            txt_spinner.setText(cm.getReason());

            return view;
        }

    }

    boolean validateData(HashMap<BackofStoreGetterSetter, List<BackofStoreGetterSetter>> listDataChild2, List<BackofStoreGetterSetter> listDataHeader2) {
        boolean flag = true;
        checkHeaderArray.clear();
        for (int i = 0; i < listDataHeader2.size(); i++) {
            String stock = listDataHeader2.get(i).getStock();
            for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i)).size(); j++) {
                String reason_id = String.valueOf(listDataChild2.get(listDataHeader2.get(i)).get(j).getAnswerId());
                if (stock.equals("")) {
                    checkflag = false;
                    Error_Message = "Please fill all Stock the data";
                    break;
                }
                if (reason_id.equals("0")) {
                    checkflag = false;
                    Error_Message = "Please select Drop Down";
                    break;
                } else {
                    checkflag = true;

                }
            }

            if (checkflag == false) {
                if (!checkHeaderArray.contains(i)) {
                    checkHeaderArray.add(i);
                }
                break;
            }
        }
        listAdapter.notifyDataSetChanged();
        return checkflag;
    }


    private void clearValidateData() {

        for (int i = 0; i < listDataHeader.size(); i++) {
            for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                listDataHeader.get(i).setStock("");
                listDataChild.get(listDataHeader.get(i)).get(j).setAnswer("");
                listDataChild.get(listDataHeader.get(i)).get(j).setAnswerId(0);
            }
        }
        lvExp_audit.clearFocus();
        lvExp_audit.invalidateViews();

    }

}
