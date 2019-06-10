package com.cpm.Tcl.dailyEntry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.PromoterTDPSaleTarget;

import java.util.ArrayList;

public class TDPSaleTargetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TDPSaleTargetAdapter adapter;
    MaricoDatabase db;
    ArrayList<PromoterTDPSaleTarget> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdpsale_target);


        db = new MaricoDatabase(getApplicationContext());
        db.open();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("TDP Sale Target");

        recyclerView = (RecyclerView)findViewById(R.id.sales_view);

        db.open();
        list = db.getTDPSaleTargetData();

        if(list.size() > 0){
            adapter = new TDPSaleTargetAdapter(this,list);
            recyclerView.setHasFixedSize(true);
            // set a GridLayoutManager with default vertical orientation and 2 number of columns
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);
        }

    }

    private class TDPSaleTargetAdapter extends RecyclerView.Adapter<TDPSaleTargetAdapter.ViewHolder>{

        private Context context;
        ArrayList<PromoterTDPSaleTarget> list;

        public TDPSaleTargetAdapter(Context context, ArrayList<PromoterTDPSaleTarget> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public TDPSaleTargetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tdp_sale_target_report_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TDPSaleTargetAdapter.ViewHolder viewHolder, int pos) {
            viewHolder.store_name.setText(list.get(pos).getStore_name() + "(ID - " + list.get(pos).getStoreId() + ")");
            viewHolder.timePeroid.setText(list.get(pos).getTimePeriod());
            viewHolder.volumeSale.setText(list.get(pos).getVolumeSale()+"");
            viewHolder.volumeTarget.setText(list.get(pos).getVolumeTarget()+"");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView timePeroid,volumeTarget,volumeSale,store_name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                timePeroid = (TextView)itemView.findViewById(R.id.time_period);
                volumeTarget = (TextView)itemView.findViewById(R.id.volume_target);
                volumeSale = (TextView)itemView.findViewById(R.id.volume_sale);
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
