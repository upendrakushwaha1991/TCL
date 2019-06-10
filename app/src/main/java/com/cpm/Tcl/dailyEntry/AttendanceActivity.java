package com.cpm.Tcl.dailyEntry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.utilities.CommonString;

public class AttendanceActivity extends AppCompatActivity {
    Button attendance,Performance;
    String  visit_date,username,intime;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    MaricoDatabase db;
    private RecyclerView lv_attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_attendance = (RecyclerView) findViewById(R.id.lv_attendance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new MaricoDatabase(getApplicationContext());
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        intime = preferences.getString(CommonString.KEY_STORE_IN_TIME, "");
        setTitle("Attendance - " + visit_date);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }
       // attendance_list = db.getAttendanceReportData();
       /* if (attendance_list.size() > 0) {
            attendanceAdapter = new AttendanceAdapter(getApplicationContext(), attendance_list);
            lv_attendance.setAdapter(attendanceAdapter);
            lv_attendance.setLayoutManager(new LinearLayoutManager(this));
            linearLayout.setVisibility(View.VISIBLE);
            no_data_lay.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.GONE);
            no_data_lay.setVisibility(View.VISIBLE);
        }*/

    }
  /*  public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
        private LayoutInflater inflator;
        List<AttendanceReport> data = Collections.emptyList();

        public AttendanceAdapter(Context context, List<AttendanceReport> data) {
            inflator = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public AttendanceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = inflator.inflate(R.layout.attendance_list, parent, false);
            AttendanceAdapter.MyViewHolder holder = new AttendanceAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final AttendanceAdapter.MyViewHolder viewHolder, final int position) {

            final AttendanceReport current = data.get(position);

            viewHolder.tvroute.setText(String.valueOf(current.getVisitDate()));
            viewHolder.tvpss.setText(String.valueOf(current.getStatus()));
            viewHolder.tvin.setText(String.valueOf(current.getInTime()));
            viewHolder.tvout.setText(String.valueOf(current.getOutTime()));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvroute, tvpss, tvin, tvout;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvroute = (TextView) itemView.findViewById(R.id.tvroute);
                tvpss = (TextView) itemView.findViewById(R.id.tvpss);
                tvin = (TextView) itemView.findViewById(R.id.tvin);
                tvout = (TextView) itemView.findViewById(R.id.tvout);
            }
        }
    }*/
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
