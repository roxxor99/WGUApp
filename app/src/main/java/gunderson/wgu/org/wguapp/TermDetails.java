package gunderson.wgu.org.wguapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TermDetails extends AppCompatActivity {
    //    private DBCon datasource;
    public Button btnTermDetailsSave;
    public Button btnTermDetailsAddCourse;
    public EditText termNameEditText;
    private long termId;

    //DatePicker
    private TextView mTermStartDate;
    private TextView mTermEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;

    private static final String TAG = "TermDetails";

    public void addCourse() {

//        termNameEditText = (EditText) findViewById(R.id.ptTermDetailsName);
//        mTermStartDate = (TextView) findViewById(R.id.tvTermDetailsStart);
//        mTermEndDate = (TextView) findViewById(R.id.tvTermDetailsEnd);

        btnTermDetailsAddCourse = findViewById(R.id.btnTermDetailsAddCourse);
        btnTermDetailsAddCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addCourse = new Intent(TermDetails.this, CourseDetails.class);
//                Bundle extras = addCourse.getExtras();
//                extras.putString("TermName",termNameEditText.getText().toString());
//                extras.putString("TermStart",mTermStartDate.getText().toString());
//                extras.putString("TermEnd",mTermEndDate.getText().toString());

                startActivity(addCourse);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        addCourse();

        //variables for the controls
        termNameEditText = findViewById(R.id.ptTermDetailsName);
        mTermStartDate = findViewById(R.id.tvTermDetailsStart);
        mTermEndDate = findViewById(R.id.tvTermDetailsEnd);

//        //if else to determine if termId is set (new/modify)
//        //Add new
//        if(termId == -1) {
//
//        }
//
//        //Modify existing
//        else{
//        }

//        if (savedInstanceState != null) {
            //Breaking the bundle into control information
            termId = savedInstanceState.getLong("termId");
            String termName = savedInstanceState.getString("termName");
            String termStart = savedInstanceState.getString("termStart");
            String termEnd = savedInstanceState.getString("termEnd");

            //Assign to proper controls
            termNameEditText.setText(termName);
            mTermStartDate.setText(termStart);
            mTermEndDate.setText(termEnd);


            TermModel term = new TermModel();
            term.setTermName(termName);
            term.setTermStart(termStart);
            term.setTermEnd(termEnd);

            DBCon datasource = new DBCon(this);
            datasource.open();
            datasource.updateTerm(term);
            datasource.close();

//        }


        //example from TermList
//                Bundle savedInstanceState = getIntent().getExtras();
//        if(savedInstanceState != null){
//
//        termName = savedInstanceState.getString("TermName");
//        termStart = savedInstanceState.getString("TermStart");
//        termEnd = savedInstanceState.getString("TermEnd");
//
//        TermModel term = new TermModel();
//        term.setTermName(termName);
//        term.setTermStart(termStart);
//        term.setTermEnd(termEnd);
//
//        DBCon datasource = new DBCon(this);
//        datasource.open();
//        datasource.updateTerm(term);
//        datasource.close();


        mTermStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            //get today's date
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TermDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTermEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TermDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mTermStartDate.setText(date);
            }
        };

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //January = 0 need to add 1 to get correct month
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mTermEndDate.setText(date);
            }
        };
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //get id of item selected in menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //TODO:
        //need an exception on delete that checks for associated courseId and denys delete if present.
        //delete term
        if (id == R.id.menuDelete) {
            Toast.makeText(this, "Delete was clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainLanding.class));
        }

        if (id == R.id.btnTermDetailsSave) {

        }

        return super.onOptionsItemSelected(item);
    }

    public void saveTerm(View view) {

        String termName = termNameEditText.getText().toString();
        String termStart = mTermStartDate.getText().toString();
        String termEnd = mTermEndDate.getText().toString();

        final TermModel term = new TermModel();
        term.setTermName(termName);
        term.setTermStart(termStart);
        term.setTermEnd(termEnd);

        DBCon datasource = new DBCon(this);
        datasource.open();
        datasource.createTerm(term);
        datasource.close();

        finish();
    }
}


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        //Term Model method to check if null values and dtf/start before end
//        //isValid;
//
//        termNameEditText = findViewById(R.id.ptTermDetailsName);
//        mTermStartDate = findViewById(R.id.tvTermDetailsStart);
//        mTermEndDate = findViewById(R.id.tvTermDetailsEnd);
//
//        Intent i = new Intent(this, TermList.class);
//        //need if statement to check for empty data
//        i.putExtra("TermName", termNameEditText.getText().toString());
//        i.putExtra("TermStart", mTermStartDate.getText().toString());
//        i.putExtra("TermEnd", mTermEndDate.getText().toString());
//        startActivity(i);
//    }


//    public void saveTerm() {
//        termNameEditText = findViewById(R.id.ptTermDetailsName);
//        mTermStartDate = findViewById(R.id.tvTermDetailsStart);
//        mTermEndDate = findViewById(R.id.tvTermDetailsEnd);
//
//        String termName = termNameEditText.getText().toString();
//        String termStart = mTermStartDate.getText().toString();
//        String termEnd = mTermEndDate.getText().toString();
//
//        final TermModel term = new TermModel();
//        term.setTermName(termName);
//        term.setTermStart(termStart);
//        term.setTermStart(termEnd);
//
//        btnTermDetailsSave = findViewById(R.id.btnTermDetailsSave);
//        btnTermDetailsSave.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent saveTerm = new Intent(TermDetails.this, TermList.class);
//                datasource = new DBCon(this);
//                datasource.open();
//                datasource.createTerm(term);
//                datasource.close();
//                startActivity(saveTerm);
//            }
//        });
//    }
//}
