package gunderson.wgu.org.wguapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class CourseList extends ListActivity {
    public Button btnAddCourse;


    public void configAddCourse() {
        btnAddCourse = findViewById(R.id.btnAddCourse);
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
        configAddCourse();

        //Send selected list item to CourseDetails
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CourseList.this, CourseDetails.class);
                CourseModel course = (CourseModel) parent.getItemAtPosition(position);
                //get info
                //Do I need to get termId to associate courses with terms?
//                intent.putExtra("termId", course.getCourseTermId());
                intent.putExtra("courseId", course.getCourseId());
                intent.putExtra("courseName", course.getCourseName());
                intent.putExtra("courseStart", course.getCourseStart());
                intent.putExtra("courseEnd", course.getCourseEnd());
                intent.putExtra("courseStatus", course.getCourseStatus());

                startActivity(intent);
            }
        });
    }


    public void onClick(View view) {
        ArrayAdapter<CourseModel> adapter = (ArrayAdapter<CourseModel>) getListAdapter();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        DBCon datasource = new DBCon(this);
        datasource.open();
        List<CourseModel> listValue = datasource.getAllCourses();
        datasource.close();
        ArrayAdapter<CourseModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listValue);
        setListAdapter(adapter);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}