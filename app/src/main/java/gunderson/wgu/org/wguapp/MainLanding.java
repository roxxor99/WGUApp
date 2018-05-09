package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class MainLanding extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
        defineButtons();

//        //owl image actionbar overwrites other actionbars
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.owl);

    }

    public void defineButtons(){
        findViewById(R.id.btnTerms).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnCourses).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnAssessment).setOnClickListener(buttonClickListener);
//        findViewById(R.id.btnMentors).setOnClickListener(buttonClickListener);
    }
        //This defines the actions for the buttons.
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnTerms:
                    Intent openTerms = new Intent(MainLanding.this, TermList.class);
                    startActivity(openTerms);
                    break;
//                case R.id.btnCourses:
//                    Intent openCourses = new Intent(MainLanding.this, CourseList.class);
//                    startActivity(openCourses);
//                    break;
//                case R.id.btnAssessment:
//                    Intent openAssessments = new Intent(MainLanding.this, AssessmentList.class);
//                    startActivity(openAssessments);
//                    break;
//                case R.id.btnMentors:
//                    Intent openMentors = new Intent(MainLanding.this, MentorList.class);
//                    startActivity(openMentors);
//                    break;

            }
        }
    };

}
