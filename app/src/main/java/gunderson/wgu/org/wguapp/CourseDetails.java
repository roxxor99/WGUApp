package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner detailSpinner;
    ArrayAdapter<CharSequence> adapter;


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
        Toast.makeText(this, sSelected, Toast.LENGTH_SHORT).show();
    }
    //Spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
