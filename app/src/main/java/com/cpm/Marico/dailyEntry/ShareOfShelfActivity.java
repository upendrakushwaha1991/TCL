package com.cpm.Marico.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
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
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.ShareOfShelfGetterSetter;
import com.cpm.Marico.utilities.CommonFunctions;
import com.cpm.Marico.utilities.CommonString;
import com.crashlytics.android.Crashlytics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ShareOfShelfActivity extends AppCompatActivity implements View.OnClickListener {
    boolean validate = true, flagcoldroom = false, flagmccain = false, flagstoredf = false;
    List<Integer> checkValidHeaderArray = new ArrayList<Integer>();
    List<Integer> checkValidChildArray = new ArrayList<Integer>();
    List<Integer> checkHeaderArray = new ArrayList<Integer>();
    boolean checkflag = true;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    FloatingActionButton btnSave;
    List<ShareOfShelfGetterSetter> listDataHeader;
    HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>> listDataChild;
    private SharedPreferences preferences;
    ArrayList<ShareOfShelfGetterSetter> brandData;
    ArrayList<ShareOfShelfGetterSetter> skuData;
    MaricoDatabase db;
    String visit_date, username, intime;
    String Error_Message;
    Snackbar snackbar;
    boolean cam_flag = true;
    boolean header_flag = false;
    String _pathforcheck, _path, str, img2 = "";
    static int grp_position = -1;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    Bitmap bmp, dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_of_shelf);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        str = CommonString.FILE_PATH;
        db = new MaricoDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        intime = preferences.getString(CommonString.KEY_STORE_IN_TIME, "");

        setTitle("Share of Shelf- " + visit_date);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
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

    //Preparing the list data
    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>>();
        brandData = db.getHeaderSosInsertData(jcpGetset);
        if (!(brandData.size() > 0)) {
            brandData = db.getmappingShareOfShelfData();
        }
        if (brandData.size() > 0) {
            // Adding child data
            for (int i = 0; i < brandData.size(); i++) {

                listDataHeader.add(brandData.get(i));
                skuData = db.getSosFromInsertDatabase(jcpGetset, brandData.get(i).getSub_category_cd());
                if (!(skuData.size() > 0)) {
                    skuData = db.getShareofShelfBrandData(brandData.get(i).getSub_category_cd());
                } else {
                    btnSave.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
                }

                List<ShareOfShelfGetterSetter> skulist = new ArrayList<ShareOfShelfGetterSetter>();
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

        if (id == R.id.btnSave) {
            expListView.clearFocus();
            expListView.invalidateViews();
            if (snackbar == null || !snackbar.isShown()) {
                if (validateData(listDataChild, listDataHeader)) {
                    db.open();
                    db.InsertCategoryShareofSelflistData(jcpGetset.getStoreId().toString(), visit_date, listDataChild, listDataHeader);
                  //  Snackbar.make(expListView, "Data has been saved", Snackbar.LENGTH_LONG).show();
                    finish();
                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                } else {
                    Snackbar.make(expListView, Error_Message, Snackbar.LENGTH_LONG).show();
                }
            }

        }
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context _context;
        private List<ShareOfShelfGetterSetter> _listDataHeader;
        private HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>> _listDataChild;

        public ExpandableListAdapter(Context context, List<ShareOfShelfGetterSetter> listDataHeader,
                                     HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>> listChildData) {
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

            final ShareOfShelfGetterSetter childText = (ShareOfShelfGetterSetter) getChild(groupPosition, childPosition);
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item_share_of_shelf, null);
                holder = new ViewHolder();
                holder.cardView = (CardView) convertView.findViewById(R.id.card_view);

                holder.etOpening_Stock = (EditText) convertView.findViewById(R.id.etOpening_Stock);
                holder.etOpening_Stock_Facingg = (EditText) convertView.findViewById(R.id.etOpening_Stock_Facingg);
                holder.ed_openingFacing = (EditText) convertView.findViewById(R.id.etOpening_Stock_Facing);

                holder.txt_date = (TextView) convertView.findViewById(R.id.txt_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
            final ViewHolder finalHolder = holder;
            txtListChild.setText(childText.getBrand());

            holder.etOpening_Stock_Facingg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (!hasFocus) {
                        final EditText Caption = (EditText) v;
                        String value1 = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                        if (value1.equals("")) {
                            _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setFacing("");
                        } else {
                            _listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).setFacing(value1);

                        }
                    }
                }

            });

            holder.etOpening_Stock_Facingg.setText(_listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getFacing());

            if (!checkflag) {
                boolean tempflag = false;

                if (holder.etOpening_Stock_Facingg.getText().toString().equals("")) {
                    holder.etOpening_Stock_Facingg.setHintTextColor(getResources().getColor(R.color.red));
                    holder.etOpening_Stock_Facingg.setHint("Empty");
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
                        if (flagmccain) {
                            holder.etOpening_Stock_Facingg.setTextColor(getResources().getColor(R.color.red));
                            tempflag = true;
                        } else {
                            holder.etOpening_Stock_Facingg.setTextColor(getResources().getColor(R.color.black));
                        }

                        if (!flagcoldroom && !flagmccain && !flagstoredf) {
                            holder.etOpening_Stock_Facingg.setTextColor(getResources().getColor(R.color.black));
                            holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                            tempflag = false;
                        } else {
                            holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                            tempflag = true;
                        }
                    } else {
                        holder.etOpening_Stock_Facingg.setTextColor(getResources().getColor(R.color.black));
                        holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                    }

                } else {
                    holder.etOpening_Stock_Facingg.setTextColor(getResources().getColor(R.color.black));
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }
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
            final ShareOfShelfGetterSetter headerTitle = (ShareOfShelfGetterSetter) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group_share_of_shelf, null);
            }
            CardView card_view = (CardView) convertView.findViewById(R.id.card_view);
            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expListView.isGroupExpanded(groupPosition)) {
                        expListView.collapseGroup(groupPosition);
                    } else {
                        expListView.expandGroup(groupPosition);
                    }
                }
            });
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            // EditText catfacing = (EditText) convertView.findViewById(R.id.catfacing);
            ImageView imgcamcat1 = (ImageView) convertView.findViewById(R.id.img_cam_cat1);
           /* catfacing.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        final EditText Caption = (EditText) v;
                        String value = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                        if (!value.equals("")) {
                            headerTitle.setCat_facing(value);
                        } else {
                            headerTitle.setCat_facing("");
                        }
                    }

                }
            });
*/
            imgcamcat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grp_position = groupPosition;
                    _pathforcheck = jcpGetset.getStoreId()+ "_" +
                            headerTitle.getCategory_cd() + "_SHARE_OF_SHELF_IMAGE_" + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                    _path = CommonString.FILE_PATH + _pathforcheck;
                    CommonFunctions.startAnncaCameraActivity(ShareOfShelfActivity.this, _path, null, false);
                    getCurrentFocus().clearFocus();
                }
            });

            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.getSub_category());

            if (!img2.equalsIgnoreCase("")) {
                if (groupPosition == grp_position) {
                    headerTitle.setImg_cat_facing(img2);
                    img2 = "";
                }
            }

            if (headerTitle.getImg_cat_facing() != null && !headerTitle.getImg_cat_facing().equals("")) {
                imgcamcat1.setBackgroundResource(R.mipmap.camera_green);
            } else {
                imgcamcat1.setBackgroundResource(R.mipmap.camera_orange);
            }
            //catfacing.setText(headerTitle.getCat_facing());

            if (!checkflag || !cam_flag) {
                if (checkHeaderArray.contains(groupPosition)) {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
                }
            } else {
                lblListHeader.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryLight));
            }

            if (header_flag) {
                if (checkHeaderArray.contains(groupPosition)) {
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
        EditText ed_openingFacing, etOpening_Stock, etOpening_Stock_Facingg;
        CardView cardView;
        TextView txt_date;
    }

    boolean validateData(HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>> listDataChild2, List<ShareOfShelfGetterSetter> listDataHeader2) {
        boolean flag = true;
        checkHeaderArray.clear();
        for (int i = 0; i < listDataHeader2.size(); i++) {
            // String catfacing = listDataHeader2.get(i).getCat_facing();
            String img_cat_facing = listDataHeader2.get(i).getImg_cat_facing();
            for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i)).size(); j++) {

                String facing = listDataChild2.get(listDataHeader2.get(i)).get(j).getFacing();

                if (img_cat_facing.equals("")) {
                    checkflag = false;
                    flag = false;
                    Error_Message = "Please click image";
                    break;
                }


               /* if (catfacing.equals("")) {
                    checkflag = false;
                    flag = false;
                    Error_Message = "Please fill all Sub category facing the data";
                    break;
                }*/
                if (facing.equalsIgnoreCase("")) {
                    checkflag = false;
                    flag = false;
                    Error_Message = "Please fill all the data";
                    break;
                } else {
                    checkflag = true;
                    flag = true;
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


    public boolean condition2() {
        boolean result = true;
        header_flag = false;
        checkHeaderArray.clear();
        for (int i = 0; i < listDataHeader.size(); i++) {
            List<ShareOfShelfGetterSetter> list = listDataChild.get(listDataHeader.get(i));
            if (list.size() > 0) {
                int facingSum = 0;
                for (int j = 0; j < list.size(); j++) {
                    ShareOfShelfGetterSetter stockgetset = list.get(j);
                    if (!stockgetset.getFacing().equalsIgnoreCase("")) {
                        facingSum = facingSum + Integer.parseInt(stockgetset.getFacing());
                    }
                }
                if (!listDataHeader.get(i).getCat_facing().equalsIgnoreCase("") && facingSum > Integer.parseInt(listDataHeader.get(i).getCat_facing())) {
                    result = false;
                    header_flag = true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShareOfShelfActivity.this);
                    final int finalI = i;
                    builder.setMessage("Sum of facing cannot be greater than Sub category facing")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    listDataHeader.get(finalI).setCat_facing("");
                                    listAdapter.notifyDataSetChanged();
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

                } else {
                    result = true;
                }
            } else {
                result = true;
            }

            if (result == false) {
                if (!checkHeaderArray.contains(i)) {
                    checkHeaderArray.add(i);
                }
                break;
            }
        }
        listAdapter.notifyDataSetChanged();
        return result;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ShareOfShelfActivity.this);
            builder.setTitle("Parinam");
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShareOfShelfActivity.this);
        builder.setTitle("Parinam");
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());

        return cdate;

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void startCameraActivity(int position) {
        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         Log.i("MakeMachine", "resultCode: " + resultCode);


         switch (requestCode) {
             case 0:
                 if (resultCode == -1) {
                     if (_pathforcheck != null && !_pathforcheck.equals("")) {
                         if (new File(str + _pathforcheck).exists()) {

                             img1 = _pathforcheck;
                             expListView.invalidateViews();
                             _pathforcheck = "";
                         }
                     }
                 } else {
                     Log.i("MakeMachine", "User cancelled");
                     _pathforcheck = "";
                 }
                 break;

             case 1:
                 if (resultCode == -1) {
                     if (_pathforcheck != null && !_pathforcheck.equals("")) {
                         if (new File(str + _pathforcheck).exists()) {

                             if (_pathforcheck.contains("ImgTwo")) {
                                 img3 = _pathforcheck;
                             } else {
                                 img2 = _pathforcheck;
                             }

                             expListView.invalidateViews();
                             _pathforcheck = "";
                         }
                     }
                 } else {
                     Log.i("MakeMachine", "User cancelled");
                     _pathforcheck = "";
                 }
                 break;
         }
         super.onActivityResult(requestCode, resultCode, data);
     }*/
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
                        img2 = _pathforcheck;
                        expListView.invalidateViews();
                        _pathforcheck = "";

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


}
