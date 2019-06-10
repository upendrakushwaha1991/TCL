package com.cpm.Tcl.dailyEntry;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.VisiColoersGetterSetter;
import com.cpm.Tcl.utilities.AlertandMessages;
import com.cpm.Tcl.utilities.CommonString;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class VisiCoolerActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp_present, sp_working, sp_location, sp_purity, sp_planogaram;
    private ImageView image_closeup, image_long_shot;
    FloatingActionButton fab;
    String[] string_present = {"Select", "YES", "NO"};
    String string_present_cd, spinner_profile_store_format_sppiner, string_working_cd, string_location_cd, string_purity_cd, string_planogram_cd;
    String visit_date, username, _pathforcheck, _pathforcheck2, _path, str, image1 = "", image2 = "";
    MaricoDatabase db;
    Context context;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    VisiColoersGetterSetter visiColoersGetterSetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visi_cooler);
        getId();


    }

    private void getId() {

        sp_present = (Spinner) findViewById(R.id.sp_present);
        sp_working = (Spinner) findViewById(R.id.sp_working);
        sp_location = (Spinner) findViewById(R.id.sp_location);
        sp_purity = (Spinner) findViewById(R.id.sp_purity);
        sp_planogaram = (Spinner) findViewById(R.id.sp_planogaram);
        image_closeup = (ImageView) findViewById(R.id.image_closeup);
        image_long_shot = (ImageView) findViewById(R.id.image_long_shot);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        db = new MaricoDatabase(context);
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);

        }

        fab.setOnClickListener(this);
        image_closeup.setOnClickListener(this);
        image_long_shot.setOnClickListener(this);
        visiColoersGetterSetter = new VisiColoersGetterSetter();
        setSppinerData();
        setInsertData();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                if (validation()) {
                   /* visiColoersGetterSetter.setImage_close_up(image1);
                    visiColoersGetterSetter.setImage_long_shot(image2);*/
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setMessage("Do you want to save Data?").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    db.open();
                                    long val = db.insertVisiCoolerData(jcpGetset, visiColoersGetterSetter);
                                    dialog.dismiss();
                                    if (val > 0) {
                                        AlertandMessages.showToastMsg(VisiCoolerActivity.this, "Data has been saved.");
                                        finish();
                                    } else {
                                        AlertandMessages.showToastMsg(VisiCoolerActivity.this, "Data not saved try again.");
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
                    alert.show();

                }

                break;
            case R.id.image_closeup:

                _pathforcheck = "_CLOSEUPIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck;
                startCameraActivity();
                break;
            case R.id.image_long_shot:
                _pathforcheck2 = "_LONGSHOTIMG_" + "" + username + visit_date.replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString.FILE_PATH + _pathforcheck2;
                startCameraActivity();

                break;
        }
    }

    private void setSppinerData() {

        ArrayAdapter present = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        present.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_present.setAdapter(present);


        ArrayAdapter working = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        working.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_working.setAdapter(working);

        ArrayAdapter location = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_location.setAdapter(location);

        ArrayAdapter purity = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        purity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_purity.setAdapter(purity);


        ArrayAdapter planogaram = new ArrayAdapter(this, android.R.layout.simple_spinner_item, string_present);
        planogaram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_planogaram.setAdapter(planogaram);


        sp_present.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_present_cd = String.valueOf(parent.getSelectedItemPosition());
                    // visiColoersGetterSetter.setPurity_cd(spinner_profile_store_format_sppiner);
                    visiColoersGetterSetter.setPresent_name(string_present_cd);
                } else {
                    visiColoersGetterSetter.setPresent_name("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_working.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_working_cd = String.valueOf(parent.getSelectedItemPosition());

                    visiColoersGetterSetter.setWorking_name(string_working_cd);
                } else {
                    visiColoersGetterSetter.setWorking_name("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_location_cd = String.valueOf(parent.getSelectedItemPosition());

                    visiColoersGetterSetter.setLocation(string_location_cd);
                } else {
                    visiColoersGetterSetter.setLocation("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_purity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_purity_cd = String.valueOf(parent.getSelectedItemPosition());
                    visiColoersGetterSetter.setPurity_name(string_purity_cd);
                } else {
                    visiColoersGetterSetter.setPurity_name("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_planogaram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    string_planogram_cd = String.valueOf(parent.getSelectedItemPosition());
                    visiColoersGetterSetter.setPlanogram_name(string_planogram_cd);
                } else {
                    visiColoersGetterSetter.setPlanogram_name("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    protected void startCameraActivity() {

        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
    }

    public boolean validation() {

        boolean value = true;
        if (sp_present.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Present");
        } else if (sp_working.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Working");
        } else if (sp_location.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Hot Zone Location");
        } else if (sp_purity.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Purity");
        } else if (sp_planogaram.getSelectedItemPosition() == 0) {
            value = false;
            showMessage("Please Select Planogram");
        } /*else if (image1.equals("")) {
            value = false;
            showMessage("Please Capture Photo Close Up");
        } */
        else if (visiColoersGetterSetter.getImage_long_shot().equalsIgnoreCase("")) {
            value = false;
            showMessage("Please Capture Photo Close Up");
        } else if (visiColoersGetterSetter.getImage_long_shot().equalsIgnoreCase("")) {
            value = false;
            showMessage("Please Capture Photo Long Shot");
        } else {
            value = true;
        }
        return value;
    }

    public void showMessage(String message) {

        Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show();

    }

    private void setInsertData() {
        db.open();
        visiColoersGetterSetter = db.getVisicoolerData(jcpGetset);
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

        if (!visiColoersGetterSetter.getWorking_name().equalsIgnoreCase("")) {
            if (visiColoersGetterSetter.getWorking_name().equalsIgnoreCase("1")) {
                sp_working.setSelection(1);
            } else {
                sp_working.setSelection(2);
            }
        } else {
            sp_working.setSelection(-1);
        }

        if (!visiColoersGetterSetter.getLocation().equalsIgnoreCase("")) {
            if (visiColoersGetterSetter.getLocation().equalsIgnoreCase("1")) {
                sp_location.setSelection(1);
            } else {
                sp_location.setSelection(2);
            }
        } else {
            sp_location.setSelection(-1);
        }
        if (!visiColoersGetterSetter.getPurity_name().equalsIgnoreCase("")) {
            if (visiColoersGetterSetter.getPurity_name().equalsIgnoreCase("1")) {
                sp_purity.setSelection(1);
            } else {
                sp_purity.setSelection(2);
            }
        } else {
            sp_purity.setSelection(-1);
        }

        if (!visiColoersGetterSetter.getPlanogram_name().equalsIgnoreCase("")) {
            if (visiColoersGetterSetter.getPlanogram_name().equalsIgnoreCase("1")) {
                sp_planogaram.setSelection(1);
            } else {
                sp_planogaram.setSelection(2);
            }
        } else {
            sp_planogaram.setSelection(-1);
        }

    }
}
