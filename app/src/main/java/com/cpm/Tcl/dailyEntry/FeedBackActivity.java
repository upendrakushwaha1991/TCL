package com.cpm.Tcl.dailyEntry;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Tcl.R;
import com.cpm.Tcl.database.MaricoDatabase;
import com.cpm.Tcl.getterSetter.ChecklistAnswer;
import com.cpm.Tcl.getterSetter.ChecklistMaster;
import com.cpm.Tcl.getterSetter.JourneyPlan;
import com.cpm.Tcl.getterSetter.MappingMenuChecklist;
import com.cpm.Tcl.getterSetter.MenuMaster;
import com.cpm.Tcl.utilities.AlertandMessages;
import com.cpm.Tcl.utilities.CommonString;

import java.util.ArrayList;
import java.util.List;

public class FeedBackActivity extends AppCompatActivity {

    JourneyPlan journeyPlan;
    MenuMaster menuMaster;
    String store_id,menu_id,visit_date="",user_name,Error_Message="";
    private Context context;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    private FloatingActionButton fab;
    MaricoDatabase db;
    QuesutionAdapter  quesutionAdapter;
    boolean spinnerTouched = false;

    boolean flag = true;
    ChecklistMaster checklistQuestionObj;
    ArrayList<MappingMenuChecklist>  menuChecklist = new ArrayList<>();
    ArrayList<ChecklistMaster> checklistQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null && getIntent().getSerializableExtra(CommonString.KEY_MENU_ID) != null) {
            journeyPlan = (JourneyPlan) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            menuMaster  = (MenuMaster) getIntent().getSerializableExtra(CommonString.KEY_MENU_ID);
            store_id = String.valueOf(journeyPlan.getStoreId());
            menu_id  = String.valueOf(menuMaster.getMenuId());
        }
        declaration();
        loadQuestion();
    }

    private void loadQuestion() {
        db.open();
        checklistQuestions = db.getSavedFeedBackData(store_id,menu_id,visit_date);
        if(checklistQuestions.size() > 0) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.edit_txt));
            for (int i = 0; i < checklistQuestions.size(); i++) {
                checklistQuestions.get(i).setChecklistAnswers(db.getCheckListQuestionsAnswer(checklistQuestions.get(i).getChecklistId()));
            }
        }else{
            db.open();
            menuChecklist = db.getCheckListId(menu_id);
            if (menuChecklist.size() > 0) {
                for (int i = 0; i < menuChecklist.size(); i++) {
                    checklistQuestionObj = db.getCheckListQuestions(menuChecklist.get(i).getChecklistId());
                    checklistQuestions.add(checklistQuestionObj);
                }
            }

            if (checklistQuestions.size() > 0) {
                for (int i = 0; i < checklistQuestions.size(); i++) {
                    checklistQuestions.get(i).setChecklistAnswers(db.getCheckListQuestionsAnswer(checklistQuestions.get(i).getChecklistId()));
                }
            } else {
                AlertandMessages.showToastMsg(this, "Data not found for feedback");
            }
        }

        quesutionAdapter = new QuesutionAdapter(context, checklistQuestions);
        recycler_view.setAdapter(quesutionAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void declaration() {

        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.feedback_btn);
        recycler_view = findViewById(R.id.feedback_view);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        db = new MaricoDatabase(context);
        db.open();

        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        user_name  = preferences.getString(CommonString.KEY_USERNAME, "");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FeedBack" + " - " + visit_date);
        //getSupportActionBar().setSubtitle(journeyPlan.getStoreName() + " - " + store_id);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDataFiled(checklistQuestions)) {

                    db.open();
                    long i =  db.insertFeedBackQuestionsData(visit_date,checklistQuestions,store_id,menu_id);
                    if(i>0){
                        AlertandMessages.showToastMsg(context,"Data saved successfully");
                        finish();
                    }else{
                        AlertandMessages.showToastMsg(context, "Data not saved");
                    }

//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setCancelable(false);
//                    builder.setMessage(R.string.title_activity_save_data).setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    db.open();
//                                    long i =  db.insertFeedBackQuestionsData(visit_date,checklistQuestions,store_id,menu_id);
//                                    if(i>0){
//                                        AlertandMessages.showToastMsg(context,"Data saved successfully");
//                                        finish();
//                                    }else{
//                                        AlertandMessages.showToastMsg(context, "Data not saved");
//                                    }
//                                    dialog.dismiss();
//                                }
//                            })
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();
                } else {
                    AlertandMessages.showToastMsg(context, Error_Message);
                }
            }
        });
    }

    private boolean checkDataFiled(ArrayList<ChecklistMaster> checklistQuestions) {
        flag = true;
        for (int i = 0; i < checklistQuestions.size(); i++) {
            if (checklistQuestions.get(i).getCorrectAnswer_Id().equalsIgnoreCase("0")) {
                flag = false;
                Error_Message = getString(R.string.answer_error);
                break;
            }
        }
        quesutionAdapter.notifyDataSetChanged();
        return flag;
    }


    private class QuesutionAdapter extends RecyclerView.Adapter<QuesutionAdapter.ViewHolder> {

        private Context context;
        int itemPos = 0;
        List<ChecklistMaster> data = new ArrayList<>();

        public QuesutionAdapter(Context context, List<ChecklistMaster> data) {
            this.context = context;
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final ChecklistMaster object = data.get(position);

            holder.question.setText(object.getChecklist());

            holder.answer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    System.out.println("Real touch felt.");
                    spinnerTouched = true;
                    return false;
                }
            });


            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), object.getChecklistAnswers());
            holder.answer.setAdapter(customAdapter);
            holder.answer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
                    if (spinnerTouched) {
                        if (pos != 0) {
                            itemPos = pos;
                            checklistQuestions.get(position).setCorrectAnswer_Id(String.valueOf(object.getChecklistAnswers().get(itemPos).getAnswerId()));
                            quesutionAdapter.notifyDataSetChanged();
                        } else {
                            object.setCorrectAnswer_Id("0");
                        }
                    }
                    spinnerTouched = false;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            if (!data.get(position).getCorrectAnswer_Id().equals("0"))
            {
                for (int j = 0; j < object.getChecklistAnswers().size(); j++) {
                    // compare correct answer cd with answer cd of question
                    if (Integer.parseInt(data.get(position).getCorrectAnswer_Id()) == object.getChecklistAnswers().get(j).getAnswerId()) {
                        holder.answer.setSelection(j);
                    }
                }
            }

            if (!flag)
            {
                boolean checkFlag = true;
                if(checklistQuestions.get(position).getCorrectAnswer_Id().equalsIgnoreCase("0")) {
                    checkFlag = false;
                }
                if (!checkFlag) {
                    holder. ll_view.setBackgroundColor(getResources().getColor(R.color.red));
                } else {
                    holder.ll_view.setBackgroundColor(getResources().getColor(R.color.white));
                }
            } else {
                holder.ll_view.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView question;
            Spinner answer;
            CardView question_view;
            LinearLayout ll_view;

            public ViewHolder(View itemView) {
                super(itemView);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.spinner_ans);
                question_view = itemView.findViewById(R.id.question_view);
                ll_view = itemView.findViewById(R.id.ll_view);
            }
        }
    }


    private class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<ChecklistAnswer> answerList;
        LayoutInflater inflter;

        public CustomAdapter(Context context, ArrayList<ChecklistAnswer> answerList) {
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
            names.setText(answerList.get(i).getAnswer());
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertandMessages(FeedBackActivity.this, null, null, null).backpressedAlert();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            new AlertandMessages(FeedBackActivity.this, null, null, null).backpressedAlert();

        }

        return super.onOptionsItemSelected(item);
    }

}
