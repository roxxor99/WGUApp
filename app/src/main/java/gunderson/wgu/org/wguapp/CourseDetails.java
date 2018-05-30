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
    private Spinner mStatusSpinner;
    private long termId;
    private long courseId;
    public EditText courseNameEditText;

    public EditText ptMentorDetailName;
    public EditText ptMentorDetailPhone;
    public EditText ptMentorDetailEmail;
    public EditText ptMentor2DetailName;
    public EditText ptMentor2DetailPhone;
    public EditText ptMentor2DetailEmail;
//    String notesName;
//    String notesBody;

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

        ptMentorDetailName = findViewById(R.id.ptMentorDetailName);
        ptMentorDetailPhone = findViewById(R.id.ptMentorDetailPhone);
        ptMentorDetailEmail = findViewById(R.id.ptMentorDetailEmail);
        ptMentor2DetailName = findViewById(R.id.ptMentor2DetailName);
        ptMentor2DetailPhone = findViewById(R.id.ptMentor2DetailPhone);
        ptMentor2DetailEmail = findViewById(R.id.ptMentor2DetailEmail);


        //Spinner
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.course_detail, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusSpinner.setAdapter(adapter);
        mStatusSpinner.setOnItemSelectedListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            termId = extras.getLong("termId");
            courseId = extras.getLong("courseId");
            String courseName = extras.getString("courseName");
            String courseStart = extras.getString("courseStart");
            String courseEnd = extras.getString("courseEnd");
            String courseStatus = extras.getString("courseStatus");

            String mentorName = extras.getString("mentorName");
            String mentorPhone = extras.getString("mentorPhone");
            String mentorEmail = extras.getString("mentorEmail");
            String mentor2Name = extras.getString("mentor2Name");
            String mentor2Phone = extras.getString("mentor2Phone");
            String mentor2Email = extras.getString("mentor2Email");

//            String notesName = extras.getString("notesName");
//            String notesBody = extras.getString("notesBody");

            //Assign to proper controls
            courseNameEditText.setText(courseName);
            mCourseStartDate.setText(courseStart);
            mCourseEndDate.setText(courseEnd);

            ptMentorDetailName.setText(mentorName);
            ptMentorDetailPhone.setText(mentorPhone);
            ptMentorDetailEmail.setText(mentorEmail);
            ptMentor2DetailName.setText(mentor2Name);
            ptMentor2DetailPhone.setText(mentor2Phone);
            ptMentor2DetailEmail.setText(mentor2Email);

            int statusPosition = adapter.getPosition(courseStatus);
            mStatusSpinner.setSelection(statusPosition);
        }

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
            DBCon datasource = new DBCon(this);
            datasource.open();
            datasource.deleteCourse(courseId);
            datasource.close();
            finish();

            Toast.makeText(this, "Course was deleted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //button navigation
    public void defineButtons() {
        findViewById(R.id.btnCourseDetailNotes).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnCourseDetailManageAss).setOnClickListener(buttonClickListener);
//        findViewById(R.id.ptCourseDetailManageMen).setOnClickListener(buttonClickListener);
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
//                        extras.putString("notesTitle", notesName);
//                        extras.putString("notesBody", notesBody);
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

//                case R.id.ptCourseDetailManageMen:
//                    //If course does not exist-> prompt user to save one before mentors can be added
//                    if (courseId == 0) {
//                        Toast.makeText(getApplicationContext(),
//                                "You must save a course before adding mentors", Toast.LENGTH_LONG).show();
//                    } else {
//                        Intent openMentors = new Intent(CourseDetails.this, MentorDetails.class);
//                        Bundle extras = new Bundle();
//                        extras.putLong("courseId", courseId);
//                        openMentors.putExtras(extras);
//                        if (extras != null) {
//                            extras.putLong("courseId", courseId);
//
//                            startActivity(openMentors);
//                        }
//                    }
//
//                    break;
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

        String mentorName = ptMentorDetailName.getText().toString();
        String mentorPhone = ptMentorDetailPhone.getText().toString();
        String mentorEmail = ptMentorDetailEmail.getText().toString();
        String mentor2Name = ptMentor2DetailName.getText().toString();
        String mentor2Phone = ptMentor2DetailPhone.getText().toString();
        String mentor2Email = ptMentor2DetailEmail.getText().toString();

        //set variables with data
        final CourseModel course = new CourseModel();
        course.setCourseTermId(termId);
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseStart(courseStart);
        course.setCourseEnd(courseEnd);
        course.setCourseStatus(courseStatus);

        course.setCourseMentorOne(mentorName);
        course.setCourseMentorPhoneOne(mentorPhone);
        course.setCourseMentorEmailOne(mentorEmail);
        course.setCourseMentorTwo(mentor2Name);
        course.setCourseMentorPhoneTwo(mentor2Phone);
        course.setCourseMentorEmailTwo(mentor2Email);


        DBCon datasource = new DBCon(this);
        datasource.open();

        if (courseId == 0) {
            datasource.createCourse(course);
        } else {
            datasource.updateCourse(course);
        }

        datasource.close();
        finish();
    }
}
