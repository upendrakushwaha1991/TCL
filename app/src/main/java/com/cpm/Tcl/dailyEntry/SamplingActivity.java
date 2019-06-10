package com.cpm.Tcl.dailyEntry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.getterSetter.SampledGetterSetter;
import com.cpm.Tcl.getterSetter.SamplingChecklist;
import com.cpm.Tcl.utilities.CommonString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SamplingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    CheckBox market_checkbox;
    LinearLayout layout_parentM,rl_content;
    String  visit_date,username,intime;
    private SharedPreferences preferences;
    JourneyPlan jcpGetset;
    MenuMaster menuMaster;
    MaricoDatabase db;
    EditText sample_mobile, sample_name,sample_email;
    String  _pathforcheck, _path, str, image1 = "";
    String answerCd = "", skuSpinValue = "", category_cdSpinValue = "", categorySpinValue = "", toggle_Value = "NO";
    LinearLayout rl_img, rl_feedback;
    NestedScrollView scroll;
    Button btn_add, save_fab;
    Spinner categorysample_spin, skusample_spin;
    EditText sample_feedback_txt;
    ImageView img_sampled;
    RecyclerView sample_list;
    ToggleButton toogle_sampleV;
    MyAdapter adapter;
    private ArrayAdapter<CharSequence> categoryAdapter, skuAdapter;
    ArrayList<SampledGetterSetter> sampleData = new ArrayList<>();
    ArrayList<SampledGetterSetter> skuData = new ArrayList<>();
    ArrayList<SamplingChecklist> samplingChecklistData = new ArrayList<>();
    ArrayList<SampledGetterSetter> insertedDataList = new ArrayList<>();
    boolean sampleaddflag = false;
    RecyclerView listView;
    QuesutionAdapter quesutionAdapter;
    boolean spinnerTouched = false,value = true,multitouch =false;
    int enable_disable = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new MaricoDatabase(getApplicationContext());
        db.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        scroll = (NestedScrollView) findViewById(R.id.scroll);
        btn_add = (Button) findViewById(R.id.btn_add);
        save_fab = (Button) findViewById(R.id.save_fab);
        rl_img = (LinearLayout) findViewById(R.id.rl_img);
        rl_feedback = (LinearLayout) findViewById(R.id.rl_feedback);
        categorysample_spin = (Spinner) findViewById(R.id.categorysample_spin);
        skusample_spin = (Spinner) findViewById(R.id.skusample_spin);
        sample_feedback_txt = (EditText) findViewById(R.id.sample_feedback_txt);
        img_sampled = (ImageView) findViewById(R.id.img_sampled);
        sample_list = (RecyclerView) findViewById(R.id.sample_list);
        toogle_sampleV = (ToggleButton) findViewById(R.id.toogle_sampleV);
        sample_mobile = (EditText) findViewById(R.id.sample_mobile);
        sample_name = (EditText) findViewById(R.id.sample_name);
        sample_email = (EditText) findViewById(R.id.sample_email);
        market_checkbox = (CheckBox) findViewById(R.id.market_checkbox);
        layout_parentM = (LinearLayout) findViewById(R.id.layout_parentM);
        rl_content = (LinearLayout) findViewById(R.id.rl_content);
        listView = (RecyclerView)findViewById(R.id.list_view);
        setTitle("Consumer Connect - " + visit_date);
        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            jcpGetset = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);

        }
        str = CommonString.FILE_PATH;
        btn_add.setOnClickListener(this);
        save_fab.setOnClickListener(this);
        img_sampled.setOnClickListener(this);
        toogle_sampleV.setOnClickListener(this);
        market_checkbox.setOnClickListener(this);
        GETALLDATA();
        setDataToListView();
        if (insertedDataList.size() > 0 && !insertedDataList.get(0).isExists()) {
            market_checkbox.setChecked(false);
            sample_list.setVisibility(View.GONE);

            layout_parentM.setVisibility(View.GONE);
            rl_content.setVisibility(View.GONE);
        } else {
            sample_list.setVisibility(View.VISIBLE);
            layout_parentM.setVisibility(View.VISIBLE);
            rl_content.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.market_checkbox:
                if (market_checkbox.isChecked()) {
                    if (insertedDataList.size() > 0 && !insertedDataList.get(0).isExists()) {
                        insertedDataList.clear();
                        db.removealSamplingData(jcpGetset);
                        save_fab.setText("Save");
                    }
                    sample_list.setVisibility(View.VISIBLE);
                    layout_parentM.setVisibility(View.VISIBLE);
                    rl_content.setVisibility(View.VISIBLE);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SamplingActivity.this);
                    builder.setTitle("Parinaam").setMessage(R.string.messageM);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sample_list.setVisibility(View.GONE);
                            layout_parentM.setVisibility(View.GONE);
                            rl_content.setVisibility(View.GONE);
                            clearingData();
                            db.open();
                            if (insertedDataList.size() > 0) {
                                insertedDataList.clear();
                                db.removealSamplingData(jcpGetset);
                            }
                            SampledGetterSetter marketIntelligenceG = new SampledGetterSetter();
                            marketIntelligenceG.setCategory("");
                            marketIntelligenceG.setCategory_cd("");
                            marketIntelligenceG.setSku("");
                            marketIntelligenceG.setFeedback("");
                            marketIntelligenceG.setMobile("");
                            marketIntelligenceG.setEmail("");
                            marketIntelligenceG.setName("");
                            marketIntelligenceG.setSampled_img("");
                            marketIntelligenceG.setExists(false);

                            insertedDataList.add(marketIntelligenceG);
                            sampleaddflag = true;
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            market_checkbox.setChecked(true);
                        }
                    });
                    builder.show();

                }
                break;

            case R.id.img_sampled:
//                _pathforcheck = jcpGetset.getStoreId().toString() + "_SAMPLEDIMG_" + visit_date.replace("/", "") + "_"
//                        + getCurrentTime().replace(":", "") + ".jpg";
//                _path = CommonString.FILE_PATH + _pathforcheck;
//                startCameraActivity();
                break;
            case R.id.btn_add:
                if (market_checkbox.isChecked()) {
                    if (validation()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SamplingActivity.this);
                        builder.setMessage("Are you sure you want to add ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                db.open();
                                                SampledGetterSetter sampledG = new SampledGetterSetter();
                                                sampledG.setCategory(categorySpinValue);
                                                sampledG.setCategory_cd(category_cdSpinValue);
                                                sampledG.setSku(skuSpinValue);
                                                sampledG.setFeedback(sample_feedback_txt.getText().toString().replaceAll("[(!@#$%^&*?)]", ""));
                                                sampledG.setMobile(sample_mobile.getText().toString().replaceAll("[(!@#$%^&*?)]", ""));
                                                sampledG.setName(sample_name.getText().toString().replaceAll("[(!@#$%^&*?)]", ""));
                                                sampledG.setEmail(sample_email.getText().toString());
                                                sampledG.setSampled_img(image1);
                                                sampledG.setExists(true);
                                                sampledG.setSamplingChecklistData(samplingChecklistData);
                                                insertedDataList.add(sampledG);
                                                adapter = new MyAdapter(SamplingActivity.this, insertedDataList);
                                                sample_list.setAdapter(adapter);
                                                sample_list.setLayoutManager(new LinearLayoutManager(SamplingActivity.this));
                                                img_sampled.setImageResource(R.drawable.camera_green);
                                                clearingData();
                                                sample_feedback_txt.setHint("Feedback");
                                                categorysample_spin.setSelection(0);
                                                skusample_spin.setSelection(0);
                                                toogle_sampleV.setChecked(false);
                                                rl_img.setVisibility(View.GONE);
                                                adapter.notifyDataSetChanged();

                                               // rl_feedback.setVisibility(View.VISIBLE);
                                                //  toggle_Value = "NO";
                                                image1 = "";
                                                sampleaddflag = true;
                                                Snackbar.make(btn_add, "Data has been added", Snackbar.LENGTH_SHORT).show();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                break;
            case R.id.save_fab:
                if (insertedDataList.size() > 0) {
                    if (sampleaddflag) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(SamplingActivity.this);
                        builder1.setMessage("Are you sure you want to save data ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                db.open();
                                                db.insertSampledData(jcpGetset, username, insertedDataList);
                                                finish();
                                                sampleaddflag = false;
                                                Snackbar.make(btn_add, "Data has been saved", Snackbar.LENGTH_SHORT).show();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert1 = builder1.create();
                        alert1.show();
                    } else {
                        Snackbar.make(btn_add, "Please add consumer connect data", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    Snackbar.make(btn_add, "Please add consumer connect data", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void clearingData() {
        sample_feedback_txt.setText("");
        sample_mobile.setText("");
        sample_name.setText("");
        sample_email.setText("");
        setDefaultdata();
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
    }

    public void GETALLDATA() {
        db.open();
        //for category
        sampleData = db.getSamplingData(jcpGetset.getStoreId().toString());
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        categoryAdapter.add("-Select-");
        for (int i = 0; i < sampleData.size(); i++) {
            categoryAdapter.add(sampleData.get(i).getSAMPLE());
        }
        categorysample_spin.setAdapter(categoryAdapter);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorysample_spin.setOnItemSelectedListener(this);


        //for sku
        skuData = db.getFeedbackMasterData();
        skuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        skuAdapter.add("-Select-");
        for (int i = 0; i < skuData.size(); i++) {
            skuAdapter.add(skuData.get(i).getFEEDBACK());
        }
        skusample_spin.setAdapter(skuAdapter);
        skuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skusample_spin.setOnItemSelectedListener(this);

        setDefaultdata();
    }

    private void setDefaultdata() {
        db.open();
        samplingChecklistData = db.getSamplingCheckListData();
        quesutionAdapter = new QuesutionAdapter(SamplingActivity.this, samplingChecklistData);
        listView.setAdapter(quesutionAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private class QuesutionAdapter extends RecyclerView.Adapter<QuesutionAdapter.ViewHolder> {

        private Context context;
        private List<SamplingChecklist> data = new ArrayList<>();

        int itemPos =0 ;

        public QuesutionAdapter(Context context, List<SamplingChecklist> data) {
            this.context = context;
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list, parent, false);
            return new QuesutionAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            boolean visibleFlag = false,valFlag = false;
            ArrayList<KeyPairBoolData> selectedSamplingData = new ArrayList<>();
            final SamplingChecklist object = data.get(position);

            holder.question.setText(object.getSamplingChecklist());
            holder.answer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //System.out.println("Real touch felt.");
                    spinnerTouched = true;
                    return false;
                }
            });

            db.open();
            ArrayList<SamplingChecklist> checkListAnswerData =  db.getSamplingChecklistAnswerData(object.getSamplingChecklistId(),0);

            if (object.getSamplingChecklistId() == 2) {
                ArrayList<SamplingChecklist> checkList = db.getSamplingChecklistAnswerData(1,1);

                if(checkList.size() > 0) {
                    enable_disable = checkList.get(0).getEnableDisable();
                }

                if(enable_disable == 1) {
                    for (int j = 0; j < samplingChecklistData.size(); j++) {
                        if (samplingChecklistData.get(j).getSamplingChecklistId() == 1) {
                            if (samplingChecklistData.get(j).getSampling_Correct_Answer_Cd() == 1) {
                                visibleFlag = true;
                            }
                        }
                    }
                }

                if(visibleFlag){
                    holder.layout_view.setVisibility(View.VISIBLE);
                    holder.question_view.setVisibility(View.VISIBLE);
                }else{
                    object.setSelectedSamplingData(new ArrayList<KeyPairBoolData>());
                    holder.layout_view.setVisibility(View.GONE);
                    holder.question_view.setVisibility(View.GONE);
                    selectedSamplingData.clear();
                }
            }else{
                holder.layout_view.setVisibility(View.VISIBLE);
                holder.question_view.setVisibility(View.VISIBLE);
            }

            if(value){
                holder.question_view.setCardBackgroundColor(getResources().getColor(R.color.white));
            }else{
                if(object.getSampling_Correct_Answer_Cd() == 0 && object.getSelectedSamplingData().size() == 0) {
                    valFlag = false;
                }else{
                    valFlag = true;
                }

                if(valFlag){
                    holder.question_view.setCardBackgroundColor(getResources().getColor(R.color.white));
                }else {
                    holder.question_view.setCardBackgroundColor(getResources().getColor(R.color.red));
                }
            }

            if(object.getAnswerType().equalsIgnoreCase("Dropdown")){
                holder.answer.setVisibility(View.VISIBLE);
                holder.multispinner_layout.setVisibility(View.GONE);

                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),checkListAnswerData);
                holder.answer.setAdapter(customAdapter);
                final ArrayList<SamplingChecklist> finalCheckListAnswerData = checkListAnswerData;
                holder.answer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
                        if (spinnerTouched) {
                            if (pos != 0) {
                                itemPos = pos;
                                if(finalCheckListAnswerData.get(pos).getSamplingAnswer().equalsIgnoreCase("Yes")){
                                    object.setSampling_Correct_Answer(finalCheckListAnswerData.get(pos).getSamplingAnswer());
                                    object.setSampling_Correct_Answer_Cd(finalCheckListAnswerData.get(pos).getSamplingAnswerId());
                                    quesutionAdapter.notifyDataSetChanged();
                                } else if(finalCheckListAnswerData.get(pos).getSamplingAnswer().equalsIgnoreCase("No")){
                                    object.setSampling_Correct_Answer(finalCheckListAnswerData.get(pos).getSamplingAnswer());
                                    object.setSampling_Correct_Answer_Cd(finalCheckListAnswerData.get(pos).getSamplingAnswerId());
                                    quesutionAdapter.notifyDataSetChanged();
                                } else {
                                    object.setSampling_Correct_Answer(finalCheckListAnswerData.get(pos).getSamplingAnswer());
                                    object.setSampling_Correct_Answer_Cd(finalCheckListAnswerData.get(pos).getSamplingAnswerId());
                                    quesutionAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                        spinnerTouched = false;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                for(int i=0;i<checkListAnswerData.size();i++){
                    if(object.getSampling_Correct_Answer_Cd() == checkListAnswerData.get(i).getSamplingAnswerId()){
                        holder.answer.setSelection(i);
                    }
                }
            }else{

                db.open();
                checkListAnswerData =  db.getSamplingChecklistAnswerData(object.getSamplingChecklistId(),1);
                holder.answer.setVisibility(View.GONE);
                holder.multispinner_layout.setVisibility(View.VISIBLE);

                List<KeyPairBoolData> listArray0 = new ArrayList<>();

                if(object.getSelectedSamplingData().size() > 0){
                    selectedSamplingData.clear();
                    listArray0.addAll(object.getSelectedSamplingData());
                }else{
                    ///default data
//                    ((TextView)holder.searchSpinner.findViewById(R.id.listTextViewSpinner)).setText("");
                    for (int i = 0; i < checkListAnswerData.size(); i++) {
                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(i + 1);
                        answerCd = getAnswer_idFromName(String.valueOf(checkListAnswerData.get(i).getSamplingAnswer()),checkListAnswerData);
                        h.setName(checkListAnswerData.get(i).getSamplingAnswer());
                        h.setAnswerCd(answerCd);
                        h.setSelected(false);
                        listArray0.add(h);
                        holder.txt_data.setVisibility(View.VISIBLE);
                    }
                }


                holder.searchSpinner.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        multitouch = true;
                        return false;
                    }
                });

                final ArrayList<KeyPairBoolData> finalSelectedSamplingData = selectedSamplingData;
                holder.searchSpinner.setItems(listArray0, -1, new SpinnerListener() {
                    @Override
                    public void onItemsSelected(List<KeyPairBoolData> items) {
                        if (multitouch) {
                            multitouch = false;
                            finalSelectedSamplingData.clear();
                            boolean flag = false;
                            for (int i = 0; i < items.size(); i++) {
                                KeyPairBoolData h1 = new KeyPairBoolData();
                                h1.setAnswerCd(items.get(i).getAnswerCd());
                                h1.setName(items.get(i).getName());
                                h1.setSelected(items.get(i).isSelected());
                                if(items.get(i).isSelected()) {
                                    flag = true;
                                }
                                finalSelectedSamplingData.add(h1);
                                // selectedMerchandiserList.add(items.get(i).getMerCd());
                                Log.i("MultiSpinner", i + " : " + items.get(i).getAnswer() + " : " + items.get(i).isSelected());
                            }
                            if (finalSelectedSamplingData.size() > 0) {
                                boolean selectedData = false;
                                for (int i = 0; i < finalSelectedSamplingData.size(); i++) {
                                    if (finalSelectedSamplingData.get(i).isSelected()) {
                                        selectedData = true;
                                        break;
                                    }
                                }

                                if (selectedData) {
                                    holder.txt_data.setVisibility(View.GONE);
                                } else {
                                    holder.txt_data.setVisibility(View.VISIBLE);
                                }
                            } else {
                                holder.txt_data.setVisibility(View.VISIBLE);
                            }

                            object.setSelectedSamplingData(finalSelectedSamplingData);
                            quesutionAdapter.notifyDataSetChanged();
                        }
                    }

                });
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView question,txt_data;
            Spinner answer;
            CardView question_view;
            LinearLayout multispinner_layout,layout_view;
            MultiSpinnerSearch searchSpinner;

            public ViewHolder(View itemView) {
                super(itemView);
                question = itemView.findViewById(R.id.question);
              //  sub_question = itemView.findViewById(R.id.sub_question);
                answer = itemView.findViewById(R.id.spinner_ans);
                searchSpinner = itemView.findViewById(R.id.searchSpinner);
                question_view = itemView.findViewById(R.id.question_view);
                txt_data = itemView.findViewById(R.id.txt_data);
                multispinner_layout = itemView.findViewById(R.id.multispinner_layout);
                layout_view = itemView.findViewById(R.id.layout_view);

            }
        }
    }


    String getAnswer_idFromName(String name, ArrayList<SamplingChecklist> checkListAnswerData) {
        String answer_cd = "0";
        for (int i = 0; i < checkListAnswerData.size(); i++) {
            if (checkListAnswerData.get(i).getSamplingAnswer().equalsIgnoreCase(name)) {
                answer_cd = String.valueOf(checkListAnswerData.get(i).getSamplingAnswerId());
                break;
            }
        }

        return answer_cd;
    }



    private class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<SamplingChecklist> answerList;
        LayoutInflater inflter;

        public CustomAdapter(Context context, ArrayList<SamplingChecklist> answerList) {
            this.context = context;
            this.answerList = answerList;
            inflter = (LayoutInflater.from(context));
        }

        @Override
        public int getCount() {
            return answerList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.answer_items, null);
            TextView names = (TextView) view.findViewById(R.id.answer);
            names.setText(answerList.get(i).getSamplingAnswer());
            return view;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.categorysample_spin:
                if (position != 0) {
                    category_cdSpinValue = sampleData.get(position - 1).getSAMPLE_CD();
                    categorySpinValue = sampleData.get(position - 1).getSAMPLE();

                }
                break;
            case R.id.skusample_spin:
                if (!category_cdSpinValue.equals("")) {
                    if (position != 0) {

                        skuSpinValue = skuData.get(position - 1).getFEEDBACK();
                    }
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private LayoutInflater inflator;
        Context context;
        ArrayList<SampledGetterSetter> insertedlist_Data;

        MyAdapter(Context context, ArrayList<SampledGetterSetter> insertedlist_Data) {
            inflator = LayoutInflater.from(context);
            this.context = context;
            this.insertedlist_Data = insertedlist_Data;
        }

        @Override
        public int getItemCount() {
            return insertedlist_Data.size();
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflator.inflate(R.layout.secondary_adapter_sample, parent, false);
            MyAdapter.MyViewHolder holder = new MyAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (insertedlist_Data.get(position).getKey_id() == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SamplingActivity.this);
                        builder.setMessage("Are you sure you want to delete the data ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                insertedlist_Data.remove(position);
                                                if (insertedlist_Data.size() > 0) {
                                                    MyAdapter adapter = new MyAdapter(SamplingActivity.this, insertedlist_Data);
                                                    sample_list.setAdapter(adapter);
                                                    adapter.notifyDataSetChanged();
                                                }
                                                notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SamplingActivity.this);
                        builder.setMessage("Are you sure you want to delete the data?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                String listid = insertedlist_Data.get(position).getKey_id();
                                                db.removesampledata(listid);
                                                insertedlist_Data.remove(position);
                                                if (insertedlist_Data.size() > 0) {
                                                    MyAdapter adapter = new MyAdapter(SamplingActivity.this, insertedlist_Data);
                                                    sample_list.setAdapter(adapter);
                                                    adapter.notifyDataSetChanged();
                                                }
                                                notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }


                }
            });

            holder.txt_cat.setText(insertedlist_Data.get(position).getName());
            holder.txt_sku.setText(insertedlist_Data.get(position).getMobile());
          //  holder.txt_samp.setText(insertedlist_Data.get(position).getSampled());
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt_cat, txt_sku, txt_samp;
            ImageView remove;

            public MyViewHolder(View convertView) {
                super(convertView);
                txt_cat = (TextView) convertView.findViewById(R.id.txt_cat);
                txt_sku = (TextView) convertView.findViewById(R.id.txt_sku);
                txt_samp = (TextView) convertView.findViewById(R.id.txt_samp);
                remove = (ImageView) convertView.findViewById(R.id.imgDelRow);
            }
        }
    }

    public void showMessage(String message) {
        Snackbar.make(btn_add, message, Snackbar.LENGTH_SHORT).show();
    }

    public boolean validation() {
        value = true;
        if(sample_name.getText().toString().equalsIgnoreCase("")){
            value = false;
            showMessage("Please enter consumer name.");
        } else if(sample_mobile.getText().toString().equalsIgnoreCase("")){
            value = false;
            showMessage("Please enter mobile number.");
        }else if (sample_mobile.getText().toString().length() < 10) {
            value = false;
            showMessage("Mobile number should be 10 digits only.");
        } else if(!sample_email.getText().toString().equalsIgnoreCase("")){
            if(!isValidEmail(sample_email.getText())) {
                value = false;
                showMessage("Please enter a valid email.");
            }else {
               checkValidationforChecklistdata();
            }
        } else {
            checkValidationforChecklistdata();
        }
        quesutionAdapter.notifyDataSetChanged();
        return value;
    }

    private void checkValidationforChecklistdata() {
        for(int i=0;i<samplingChecklistData.size();i++){
            if(samplingChecklistData.get(i).getAnswerType().equalsIgnoreCase("Dropdown")) {
                if (samplingChecklistData.get(i).getSampling_Correct_Answer_Cd() == 0) {
                    value = false;
                    showMessage("Please select checklist answer");
                    break;
                }
            }else {
                if (samplingChecklistData.get(0).getSamplingChecklistId() == 1  && samplingChecklistData.get(0).getSampling_Correct_Answer_Cd() == 1) {
                    if (samplingChecklistData.get(i).getSelectedSamplingData().size() != 0) {
                        value = false;
                        for (int k = 0; k < samplingChecklistData.get(i).getSelectedSamplingData().size(); k++) {
                            if (samplingChecklistData.get(i).getSelectedSamplingData().get(k).isSelected()) {
                                value = true;
                                break;
                            }
                        }
                        if (!value) {
                            value = false;
                            showMessage("Please select atleast one checklist answer");
                            break;
                        }
                    } else {
                        value = false;
                        showMessage("Please select atleast one checklist answer");
                        break;
                    }
                }
            }
        }
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                                    finish();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                                finish();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

//    protected void startCameraActivity() {
//
//        try {
//            Log.i("MakeMachine", "startCameraActivity()");
//            File file = new File(_path);
//            Uri outputFileUri = Uri.fromFile(file);
//
//            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//            startActivityForResult(intent, 0);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    @SuppressWarnings("deprecation")
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i("MakeMachine", "resultCode: " + resultCode);
//        switch (resultCode) {
//            case 0:
//                Log.i("MakeMachine", "User cancelled");
//                break;
//            case -1:
//                if (_pathforcheck != null && !_pathforcheck.equals("")) {
//                    if (new File(str + _pathforcheck).exists()) {
//                        img_sampled.setImageResource(R.drawable.camera_green);
//                        image1 = _pathforcheck;
//                    }
//                    _pathforcheck = "";
//                }
//                break;
//        }
//
//    }

    public void setDataToListView() {
        try {
            insertedDataList = db.getinsertedsampledData(jcpGetset.getStoreId().toString(), visit_date);
            for(int i=0;i<insertedDataList.size();i++){
                ArrayList <SamplingChecklist> sampleData = db.getInsertedSamplingData(jcpGetset.getStoreId().toString(),insertedDataList.get(i).getKey_id());
                insertedDataList.get(i).setSamplingChecklistData(sampleData);
            }

            if (insertedDataList.size() > 0) {
                save_fab.setText("Update");
                Collections.reverse(insertedDataList);
                adapter = new MyAdapter(this, insertedDataList);
                sample_list.setAdapter(adapter);
                sample_list.setLayoutManager(new LinearLayoutManager(SamplingActivity.this));
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
        }
    }

}
