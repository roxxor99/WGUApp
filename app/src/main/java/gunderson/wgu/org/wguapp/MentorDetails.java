package gunderson.wgu.org.wguapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;


public class MentorDetails extends AppCompatActivity {
    private long termId;
    private long courseId;

    public EditText ptMentorDetailName;
    public EditText ptMentorDetailPhone;
    public EditText ptMentorDetailEmail;

    public EditText ptMentor2DetailName;
    public EditText ptMentor2DetailPhone;
    public EditText ptMentor2DetailEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_details);

        //Variable for controls
        ptMentorDetailName = findViewById(R.id.ptMentorDetailName);
        ptMentorDetailPhone = findViewById(R.id.ptMentorDetailPhone);
        ptMentorDetailEmail = findViewById(R.id.ptMentorDetailEmail);

        ptMentor2DetailName = findViewById(R.id.ptMentor2DetailName);
        ptMentor2DetailPhone = findViewById(R.id.ptMentor2DetailPhone);
        ptMentor2DetailEmail = findViewById(R.id.ptMentor2DetailEmail);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            courseId = extras.getLong("courseId");
            String mentorName = extras.getString("mentorName");
            String mentorPhone = extras.getString("mentorPhone");
            String mentorEmail = extras.getString("mentorEmail");

            String mentor2Name = extras.getString("mentor2Name");
            String mentor2Phone = extras.getString("mentor2Phone");
            String mentor2Email = extras.getString("mentor2Email");

            //Assign to proper controls
            ptMentorDetailName.setText(mentorName);
            ptMentorDetailPhone.setText(mentorPhone);
            ptMentorDetailEmail.setText(mentorEmail);

            ptMentor2DetailName.setText(mentor2Name);
            ptMentor2DetailPhone.setText(mentor2Phone);
            ptMentor2DetailEmail.setText(mentor2Email);

        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        DBCon datasource = new DBCon(this);
//        datasource.open();
//        List<CourseModel> listValue = datasource.getCourses(termId);
//        datasource.close();
//
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }


    public void saveMentor(View view) {
        //Create variables
        String mentorName = ptMentorDetailName.getText().toString();
        String mentorPhone = ptMentorDetailPhone.getText().toString();
        String mentorEmail = ptMentorDetailEmail.getText().toString();

        String mentor2Name = ptMentor2DetailName.getText().toString();
        String mentor2Phone = ptMentor2DetailPhone.getText().toString();
        String mentor2Email = ptMentor2DetailEmail.getText().toString();


        //set variables with data
        final CourseModel course = new CourseModel();
//        course.setCourseTermId(termId);
        course.setCourseId(courseId);

        course.setCourseMentorOne(mentorName);
        course.setCourseMentorPhoneOne(mentorPhone);
        course.setCourseMentorEmailOne(mentorEmail);

        course.setCourseMentorTwo(mentor2Name);
        course.setCourseMentorPhoneTwo(mentor2Phone);
        course.setCourseMentorEmailTwo(mentor2Email);


        DBCon datasource = new DBCon(this);
        datasource.open();

        //Can be cleaned up... if courseId == 0 then mentors cannot be created to begin with
        //at this point courseId should never == 0
        //we should just have to perform an updateCourse and not worry about createCourse
//        if (courseId == 0) {
//            datasource.createCourse(course);
//        } else {
            datasource.updateCourse(course);
//        }

        datasource.close();
        finish();
    }
}

