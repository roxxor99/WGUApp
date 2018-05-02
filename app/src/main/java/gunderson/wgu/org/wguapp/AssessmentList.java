package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AssessmentList extends AppCompatActivity {

    public Button btnAddAssessment;
//    public Button btnAddMen;

    // need to add array and adapter info https://www.youtube.com/watch?v=ws_p8LJ4Uq8
    public void addAssessment() {
        btnAddAssessment = (Button) findViewById(R.id.btnAddAssessment);
        btnAddAssessment.setOnClickListener(new View.OnClickListener() {
//        btnAddMen = (Button) findViewById(R.id.btnAddMen);
//        btnAddMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAssessment = new Intent(AssessmentList.this, AssessmentDetails.class);
                startActivity(addAssessment);
//                Intent addMentor = new Intent(MentorList.this, MentorDetails.class);
//                startActivity(addMentor);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
//        setContentView(R.layout.activity_mentor_list);
        addAssessment();
//        addMentors();
    }
}

