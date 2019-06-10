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
import com.cpm.Tcl.getterSetter.PromoterSkuwiseSale;

import java.util.ArrayList;

public class SkuWiseSaleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SkuWiseSaleAdapter adapter;
    MaricoDatabase db;
    ArrayList<PromoterSkuwiseSale> promoterSkuwiseSales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku_wise_sale);

        db = new MaricoDatabase(getApplicationContext());
        db.open();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Sku Wise Sale Target");

        recyclerView = (RecyclerView)findViewById(R.id.sales_view);

        db.open();
        promoterSkuwiseSales = db.getSkuWiseSaleTarget();

        if(promoterSkuwiseSales.size() > 0){
            adapter = new SkuWiseSaleAdapter(this,promoterSkuwiseSales);
            recyclerView.setHasFixedSize(true);
            // set a GridLayoutManager with default vertical orientation and 2 number of columns
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);
        }

    }

    private class SkuWiseSaleAdapter extends RecyclerView.Adapter<SkuWiseSaleAdapter.ViewHolder>{
        Context context;
        ArrayList<PromoterSkuwiseSale> list;

        public SkuWiseSaleAdapter(Context context, ArrayList<PromoterSkuwiseSale> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skuwise_sale_target_report_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
            viewHolder.store_name.setText(list.get(pos).getStore_name() + "(ID - " + list.get(pos).getStoreId() + ")");
            viewHolder.sku.setText(list.get(pos).getSku());
            viewHolder.saleValue.setText(list.get(pos).getValueSale()+"");
            viewHolder.saleVolume.setText(list.get(pos).getVolumeSale()+"");

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView sku,saleVolume,saleValue,store_name;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                sku = (TextView)itemView.findViewById(R.id.sku);
                saleVolume = (TextView)itemView.findViewById(R.id.sale_volume);
                saleValue = (TextView)itemView.findViewById(R.id.sale_value);
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
