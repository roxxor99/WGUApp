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

public class AssessmentList extends ListActivity {
    public Button btnAddAssessment;


    public void configAddAssessment() {
        btnAddAssessment = findViewById(R.id.btnAddAssessment);
        btnAddAssessment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addAssessment = new Intent(AssessmentList.this, AssessmentDetails.class);
                startActivity(addAssessment);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        configAddAssessment();

        //Send selected list item to CourseDetails
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AssessmentList.this, AssessmentDetails.class);
                AssessmentModel assessment = (AssessmentModel) parent.getItemAtPosition(position);
                //get info
                //Do I need to get courseId to associate assessments with courses?
//                intent.putExtra("courseId", assessment.getCourseId());
                intent.putExtra("assessmentId", assessment.getAssessmentId());
                intent.putExtra("assessmentName", assessment.getAssessmentName());
                intent.putExtra("assessmentGoalDate", assessment.getAssessmentGoalDate());
                intent.putExtra("assessmentType", assessment.getAssessmentType());

                startActivity(intent);
            }
        });
    }


    public void onClick(View view) {
        ArrayAdapter<AssessmentModel> adapter = (ArrayAdapter<AssessmentModel>) getListAdapter();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        DBCon datasource = new DBCon(this);
        datasource.open();
        List<AssessmentModel> listValue = datasource.getAllAssessments();
        datasource.close();
        ArrayAdapter<AssessmentModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listValue);
        setListAdapter(adapter);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
