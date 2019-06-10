package com.cpm.Tcl.dailyEntry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cpm.Tcl.R;
import com.cpm.Tcl.adapter.ItemValueAdapter;
import com.cpm.Tcl.getterSetter.NavGetterSetter;

import java.util.ArrayList;
import java.util.List;

public class PerformanceReportActivity extends AppCompatActivity {


    private RecyclerView  recyclerView;
    private ItemValueAdapter adapter;

    final String report_name[] = {
            "Monthly Target",
            "TDP Achievement",
            "Sku Wise Sale"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Performance Report");

        recyclerView = findViewById(R.id.performance_view);
        adapter = new ItemValueAdapter(this, getdata());
        recyclerView.setHasFixedSize(true);
        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(adapter);
    }

    private List<NavGetterSetter> getdata() {
        List<NavGetterSetter> data = new ArrayList<>();
        for (int i = 0; i < report_name.length; i++) {
            NavGetterSetter recData = new NavGetterSetter();
            recData.setReport_name(report_name[i]);
            data.add(recData);
        }
        return data;
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
