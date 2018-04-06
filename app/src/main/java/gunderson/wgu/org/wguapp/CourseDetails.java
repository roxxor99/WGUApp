package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class CourseDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

//        defineButtons();
    }

    public void defineButtons(){
//        findViewById(R.id.btnCourseDetailNotes).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnCourseDetailManageAss).setOnClickListener(buttonClickListener);
//        findViewById(R.id.ptCourseDetailManageMen).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnCourseDetailDeleteCourse).setOnClickListener(buttonClickListener);
    }

//    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.btnCourseDetailNotes:
//                    Intent openNotes = new Intent(CourseDetails.this, Notes.class);
//                    startActivity(openNotes);
//                    break;
//                case R.id.btnCourseDetailManageAss:
//                    Intent openAssessment = new Intent(CourseDetails.this, AssessmentList.class);
//                    startActivity(openAssessment);
//                    break;
//                case R.id.ptCourseDetailManageMen:
//                    Intent openMentors = new Intent(CourseDetails.this, MentorList.class);
//                    startActivity(openMentors);
//                    break;
//                case R.id.btnCourseDetailDeleteCourse:
//                    Intent deleteCourse = new Intent(CourseDetails.this, MentorList.class);
//                    startActivity(deleteCourse);
//                    break;
//
//
//            }
//        }
//    };

}
