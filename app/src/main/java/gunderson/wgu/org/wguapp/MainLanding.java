package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);

        defineButtons();
    }

    public void defineButtons(){
        findViewById(R.id.btnTerms).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnCourses).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnMentors).setOnClickListener(buttonClickListener);
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
                case R.id.btnCourses:
                    Intent openCourses = new Intent(MainLanding.this, CourseList.class);
                    startActivity(openCourses);
                    break;
//                case R.id.btnMentors:
//                    Intent openMentors = new Intent(MainLanding.this, MentorList.class);
//                    startActivity(openMentors);
//                    break;


            }
        }
    };

}
