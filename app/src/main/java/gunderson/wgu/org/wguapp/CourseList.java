package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseList extends AppCompatActivity {
    public Button btnAddCourse;

    public void addCourse() {
        btnAddCourse = (Button) findViewById(R.id.btnAddCourse);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCourse = new Intent(CourseList.this, CourseDetails.class);
                startActivity(addCourse);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        addCourse();

    }
}
