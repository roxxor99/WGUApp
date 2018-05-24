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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //not sure if should be String, EditText, or Spinner
    private Spinner mStatusSpinner;
    private long termId;
    private long courseId;
    public EditText courseNameEditText;

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

        //variables for controls
        courseNameEditText = findViewById(R.id.ptCourseDetailCourseName);
        mCourseStartDate = findViewById(R.id.tvCourseDetailStartDate);
        mCourseEndDate = findViewById(R.id.tvCourseDetailEndDate);
        mStatusSpinner = findViewById(R.id.spinnerCourseDetailStatus);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            termId = extras.getLong("termId");
            courseId = extras.getLong("courseId");
            String courseName = extras.getString("courseName");
            String courseStart = extras.getString("courseStart");
            String courseEnd = extras.getString("courseEnd");
//          TODO
            String courseStatus = extras.getString("courseStatus");

            //Assign to proper controls
            courseNameEditText.setText(courseName);
            mCourseStartDate.setText(courseStart);
            mCourseEndDate.setText(courseEnd);
//!!! This is wrong
//          TODO
            mStatusSpinner.setPrompt(courseStatus);
        }

        //Spinner
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.course_detail, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(adapter);
        mStatusSpinner.setOnItemSelectedListener(this);

        //DatePicker
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

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get id of item selected in menu
        int id = item.getItemId();

        if (id == R.id.menuDelete) {
            Toast.makeText(this, "Delete was clicked", Toast.LENGTH_SHORT).show();

            //DBCon deleteCourse
            //place holder for delete action returns to mainlanding
            startActivity(new Intent(this, MainLanding.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //button navigation
    public void defineButtons() {
        findViewById(R.id.btnCourseDetailNotes).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnCourseDetailManageAss).setOnClickListener(buttonClickListener);
        findViewById(R.id.ptCourseDetailManageMen).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnCourseDetailNotes:

                    //If course does not exist-> prompt user to save one before notes can be added
                    if (courseId == 0) {
                        Toast.makeText(getApplicationContext(),
                                "You must save a course before adding notes", Toast.LENGTH_LONG).show();
                    } else {
                        Intent openNotes = new Intent(CourseDetails.this, Notes.class);
                        Bundle extras = new Bundle();
                        extras.putLong("courseId", courseId);
                        openNotes.putExtras(extras);
                        if (extras != null) {
                            extras.putLong("courseId", courseId);

                            startActivity(openNotes);
                        }
                    }

                    break;

                case R.id.btnCourseDetailManageAss:
                    //If course does not exist-> prompt user to save one before assessments can be added
                    if (courseId == 0) {
                        Toast.makeText(getApplicationContext(),
                                "You must save a course before adding assessments", Toast.LENGTH_LONG).show();
                    } else {
                        Intent openAssessment = new Intent(CourseDetails.this, AssessmentList.class);
                        Bundle extras = new Bundle();
                        extras.putLong("courseId", courseId);
                        openAssessment.putExtras(extras);

                        startActivity(openAssessment);
                    }

                    break;

                case R.id.ptCourseDetailManageMen:
                    //If course does not exist-> prompt user to save one before mentors can be added
                    if (courseId == 0) {
                        Toast.makeText(getApplicationContext(),
                                "You must save a course before adding mentors", Toast.LENGTH_LONG).show();
                    } else {
                        Intent openMentors = new Intent(CourseDetails.this, MentorDetails.class);
                        Bundle extras = new Bundle();
                        extras.putLong("courseId", courseId);
                        openMentors.putExtras(extras);
                        if (extras != null) {
                            extras.putLong("courseId", courseId);

                            startActivity(openMentors);
                        }
                    }

                    break;
            }
        }
    };

    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String sSelected = adapterView.getItemAtPosition(position).toString();
    }

    //Spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    public void saveCourse(View view) {
        //Create variables
        String courseName = courseNameEditText.getText().toString();
        String courseStart = mCourseStartDate.getText().toString();
        String courseEnd = mCourseEndDate.getText().toString();
        String courseStatus = mStatusSpinner.getSelectedItem().toString();

        //set variables with data
        final CourseModel course = new CourseModel();
        course.setCourseTermId(termId);
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseStart(courseStart);
        course.setCourseEnd(courseEnd);
        course.setCourseStatus(courseStatus);


        DBCon datasource = new DBCon(this);
        datasource.open();
        Bundle extras = getIntent().getExtras();

        if (courseId == 0) {
            datasource.createCourse(course);
        } else {
            datasource.updateCourse(course);
        }

        datasource.close();
        finish();
    }
}
