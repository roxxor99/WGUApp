package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTerms extends AppCompatActivity {
    public Button btnAddTermsAddCourses;

    public void addCourse() {
        btnAddTermsAddCourses = (Button) findViewById(R.id.btnAddTermsAddCourses);
        btnAddTermsAddCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCourse = new Intent(AddTerms.this, CourseDetails.class);
                startActivity(addCourse);
            }
        });
    }

//    Data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);

        addCourse();

    }
}
