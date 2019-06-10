package com.cpm.Tcl.dailyEntry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.getterSetter.PerformancePage;
import com.cpm.Tcl.utilities.CommonString;

public class PerformanceWebview extends AppCompatActivity {

    WebView webview_livon;
    ImageView img_main;
    String visit_date;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    PerformancePage performancePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_webview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webview_livon = findViewById(R.id.webview_livon);
        img_main = findViewById(R.id.img_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        setTitle("Performance - " + visit_date);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }

        MaricoDatabase database = new MaricoDatabase(getApplicationContext());
        database.open();
        performancePage =  database.getPerformancePage();

       /* webview_livon.setWebViewClient(new MyWebViewClient());
        webview_livon.getSettings().setJavaScriptEnabled(true);*/

        webview_livon.getSettings().setJavaScriptEnabled(true);
        webview_livon.loadUrl(performancePage.getPerformancePage());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
