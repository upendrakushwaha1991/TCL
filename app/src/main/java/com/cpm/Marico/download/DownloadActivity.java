package com.cpm.Marico.download;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.upload.Retrofit_method.UploadImageWithRetrofit;
import com.cpm.Marico.utilities.CommonString;

import org.json.JSONObject;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {
    MaricoDatabase db;
    String userId, date, rightname;
    private SharedPreferences preferences = null;
    Context context;
    int downloadindex = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        context = this;
        db = new MaricoDatabase(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getString(CommonString.KEY_USERNAME, null);
        date = preferences.getString(CommonString.KEY_DATE, "");
        rightname = preferences.getString(CommonString.KEY_RIGHTNAME, "");
        downloadindex = preferences.getInt(CommonString.KEY_DOWNLOAD_INDEX, 0);
        getSupportActionBar().setTitle("Download - " + date);
        UploadDataTask();
    }

    public void UploadDataTask() {
        try {
            ArrayList<String> keysList = new ArrayList<>();
            ArrayList<String> jsonList = new ArrayList<>();
            ArrayList<String> KeyNames = new ArrayList<>();
            KeyNames.clear();
            keysList.clear();
            keysList.add("Table_Structure");
            keysList.add("Journey_Plan");
            keysList.add("Non_Working_Reason");
            keysList.add("Menu_Master");
            keysList.add("Mapping_Menu");
          //  keysList.add("Mapping_Posm");
           // keysList.add("Posm_Master");
           // keysList.add("Mapping_Visicooler");
            keysList.add("Non_Execution_Reason");
            keysList.add("Brand_Master");
            keysList.add("Mapping_Stock");
            keysList.add("Display_Master");
            keysList.add("Sku_Master");
            keysList.add("Sub_Category_Master");
            keysList.add("Category_Master");
            keysList.add("Mapping_Sampling");
            keysList.add("mapping_Share_Of_Shelf");
            keysList.add("Mapping_Paid_Visibility");
            keysList.add("Mapping_Promotion");
            keysList.add("Feedback_Master");
            keysList.add("Sampling_Checklist");
            keysList.add("Performance");
            keysList.add("Mapping_Tester_Stock");

            if (keysList.size() > 0) {
                for (int i = 0; i < keysList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Downloadtype", keysList.get(i));
                    jsonObject.put("Username", userId);
                    jsonList.add(jsonObject.toString());
                    KeyNames.add(keysList.get(i));
                }

                if (jsonList.size() > 0) {
                    ProgressDialog pd = new ProgressDialog(context);
                    pd.setCancelable(false);
                    pd.setMessage("Downloading Data" + "(" + "/" + ")");
                    pd.show();
                    UploadImageWithRetrofit downloadData = new UploadImageWithRetrofit(context, db, pd, CommonString.TAG_FROM_CURRENT);
                    downloadData.listSize = jsonList.size();
                    downloadData.downloadDataUniversalWithoutWait(jsonList, KeyNames, downloadindex, CommonString.DOWNLOAD_ALL_SERVICE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
