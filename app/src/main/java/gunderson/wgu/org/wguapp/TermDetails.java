package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TermDetails extends AppCompatActivity {
    public Button btnTermDetailsAddCourse;
    public EditText termNameEditText;
    public EditText termStartEditText;
    public EditText termEndEditText;


    public void addCourse() {
//!!!!!!!!Should this be down on onCreate
        termNameEditText = (EditText) findViewById(R.id.ptTermDetailsName);
        termStartEditText = (EditText) findViewById(R.id.ptTermDetailsStart);
        termEndEditText = (EditText) findViewById(R.id.ptTermDetailsEnd);

        btnTermDetailsAddCourse = (Button) findViewById(R.id.btnTermDetailsAddCourse);
        btnTermDetailsAddCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //TODO:
                //only enabled adding courses if TermId is present
                Intent addCourse = new Intent(TermDetails.this, CourseDetails.class);
                Bundle extras = addCourse.getExtras();
                extras.putString("TermName",termNameEditText.getText().toString());
                extras.putString("TermStart",termStartEditText.getText().toString());
                extras.putString("TermEnd",termEndEditText.getText().toString());

                startActivity(addCourse);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        addCourse();

//        //open DataSource
//        datasource = new DBCon(this);
//        datasource.open();
//
//        List<TermModel> values = datasource.getAllTerms();
//        ArrayAdapter<TermModel> adapter = new ArrayAdapter<TermModel>(this, android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);
    }

//    public void onClick(View view) {
//        Intent intent = new  Intent(TermDetails.this, TermList.class);
//
//        ArrayAdapter<TermModel> adapter = (ArrayAdapter<TermModel>) getListAdapter();
//        TermModel term = null;
//        switch (view.getId()) {
////            case R.id.add:
////                String[] comments = new String[]{"Happy", "Sad", "Mad"};
////                int nextInt = new Random().nextInt(3);
////                // save the new comment to the database
////                term = datasource.createTerm(term[nextInt]);
////                adapter.add(term);
////                break;
//
//
//            case R.id.menuDelete:
//                if (getListAdapter().getCount() > 0) {
//                    term = (TermModel) getListAdapter().getItem(0);
//                    datasource.deleteTerm(term);
//                    adapter.remove(term);
//                }
//                break;
//        }
//        adapter.notifyDataSetChanged();
//    }

//    @Override
//    protected void onResume() {
//        datasource.open();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        datasource.close();
//        super.onPause();
//    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //get id of item selected in menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //TODO:
        //need an exception on delete that checks for associated courseId and denys delete if present.
        if (id == R.id.menuDelete) {
            Toast.makeText(this, "Delete was clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainLanding.class));
        }
        return super.onOptionsItemSelected(item);
    }
}