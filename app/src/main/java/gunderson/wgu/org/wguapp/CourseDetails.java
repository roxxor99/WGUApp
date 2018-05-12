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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private DBCon datasource;
    Spinner detailSpinner;
    ArrayAdapter<CharSequence> adapter;

    //DatePicker
    private TextView mCourseStartDate;
    private TextView mCourseEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;

    private static final String TAG = "CourseDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        defineButtons();

        //Spinner
        detailSpinner = findViewById(R.id.spinnerCourseDetailStatus);
        adapter = ArrayAdapter.createFromResource(this, R.array.course_detail, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailSpinner.setAdapter(adapter);
        detailSpinner.setOnItemSelectedListener(this);


        mCourseStartDate = findViewById(R.id.tvCourseDetailStartDate);
        mCourseEndDate = findViewById(R.id.tvCourseDetailEndDate);

        mCourseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            //get today's date
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CourseDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mCourseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CourseDetails.this,
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
                mCourseStartDate.setText(date);
            }
        };

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //January = 0 need to add 1 to get correct month
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mCourseEndDate.setText(date);
            }
        };
    }

    //Appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Appbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get id of item selected in menu
        int id = item.getItemId();

        if (id == R.id.menuDelete) {
            Toast.makeText(this, "Delete was clicked", Toast.LENGTH_SHORT).show();
//place holder for delete action returns to mainlanding
            startActivity(new Intent(this, MainLanding.class));
        }
        return super.onOptionsItemSelected(item);
    }
    //button navigation
    public void defineButtons(){
        findViewById(R.id.btnCourseDetailNotes).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnCourseDetailManageAss).setOnClickListener(buttonClickListener);
        findViewById(R.id.ptCourseDetailManageMen).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnCourseDetailDeleteCourse).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnCourseDetailNotes:
                    Intent openNotes = new Intent(CourseDetails.this, Notes.class);
                    startActivity(openNotes);
                    break;
                case R.id.btnCourseDetailManageAss:
                    Intent openAssessment = new Intent(CourseDetails.this, AssessmentDetails.class);
                    startActivity(openAssessment);
                    break;
                case R.id.ptCourseDetailManageMen:
                    Intent openMentors = new Intent(CourseDetails.this, MentorDetails.class);
                    startActivity(openMentors);
                    break;
//                case R.id.btnCourseDetailDeleteCourse:
//                    Intent deleteCourse = new Intent(CourseDetails.this, MentorList.class);
//                    startActivity(deleteCourse);
//                    break;
            }
        }
    };

    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String sSelected = adapterView.getItemAtPosition(position).toString();
//        Toast.makeText(this, sSelected, Toast.LENGTH_SHORT).show();
    }
    //Spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
