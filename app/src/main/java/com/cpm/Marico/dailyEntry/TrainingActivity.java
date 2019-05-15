package com.cpm.Marico.dailyEntry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cpm.Marico.R;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.utilities.CommonString;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {
    Button livon, mediker, Reviee, nihar, saffolo;
    String visit_date;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        setTitle("Training - " + visit_date);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }
        livon = (Button) findViewById(R.id.livon);
        mediker = (Button) findViewById(R.id.mediker);
        Reviee = (Button) findViewById(R.id.Reviee);
        nihar = (Button) findViewById(R.id.nihar);
        saffolo = (Button) findViewById(R.id.saffolo);

        livon.setOnClickListener(this);
        mediker.setOnClickListener(this);
        Reviee.setOnClickListener(this);
        nihar.setOnClickListener(this);
        saffolo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.livon:
                Intent startDownload = new Intent(this, LivonActivity.class);
                startActivity(startDownload);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.mediker:

                break;
            case R.id.Reviee:

                break;
            case R.id.nihar:

                break;
            case R.id.saffolo:

                break;
        }

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
