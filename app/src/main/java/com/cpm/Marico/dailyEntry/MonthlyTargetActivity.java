package com.cpm.Marico.dailyEntry;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpm.Marico.R;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.getterSetter.PromoterTarget;

import java.util.ArrayList;

public class MonthlyTargetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MonthlyTargetAdapter adapter;
    MaricoDatabase db;
    ArrayList<PromoterTarget> promoterTargets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_target);

        db = new MaricoDatabase(getApplicationContext());
        db.open();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Monthly Target");

        recyclerView = (RecyclerView)findViewById(R.id.sales_view);

        db.open();
        promoterTargets = db.getMonthlyTargetData();

        if(promoterTargets.size() > 0){
            adapter = new MonthlyTargetAdapter(this,promoterTargets);
            recyclerView.setHasFixedSize(true);
            // set a GridLayoutManager with default vertical orientation and 2 number of columns
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);
        }

    }

    private class MonthlyTargetAdapter extends RecyclerView.Adapter<MonthlyTargetAdapter.ViewHolder>{
       Context context;
        ArrayList<PromoterTarget> promoterTargets;

        public MonthlyTargetAdapter(Context context, ArrayList<PromoterTarget> promoterTargets) {
            this.context = context;
            this.promoterTargets = promoterTargets;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_target_report_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
            viewHolder.store_name.setText(promoterTargets.get(pos).getStore_name() + "(ID - " + promoterTargets.get(pos).getStoreId() + ")");
            viewHolder.mtdVolume.setText(promoterTargets.get(pos).getMTDVolumeTarget()+"");
            viewHolder.mtdValue.setText(promoterTargets.get(pos).getMTDValueTarget()+"");
            viewHolder.dailyVolume.setText(promoterTargets.get(pos).getDailyVolumeTarget()+"");
            viewHolder.dailyValue.setText(promoterTargets.get(pos).getDailyValueTarget()+"");
        }

        @Override
        public int getItemCount() {
            return promoterTargets.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mtdVolume,mtdValue,dailyVolume,dailyValue,store_name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mtdVolume = (TextView)itemView.findViewById(R.id.mtd_volume);
                mtdValue = (TextView)itemView.findViewById(R.id.mtd_value);
                dailyVolume = (TextView)itemView.findViewById(R.id.daily_volume);
                dailyValue = (TextView)itemView.findViewById(R.id.daily_value);
                store_name = (TextView)itemView.findViewById(R.id.store_name);
            }
        }
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
