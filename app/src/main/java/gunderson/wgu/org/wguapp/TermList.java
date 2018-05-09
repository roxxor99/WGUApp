package gunderson.wgu.org.wguapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;


public class TermList extends ListActivity {
    public Button btnAddTerm;
//JG
    private DBCon datasource;
    public EditText termNameEditText;
    public EditText termStartEditText;
    public EditText termEndEditText;
//JG

    public void addTerm() {
    //JG
        termNameEditText = (EditText) findViewById(R.id.ptTermDetailsName);
        termStartEditText = (EditText) findViewById(R.id.ptTermDetailsStart);
        termEndEditText = (EditText) findViewById(R.id.ptTermDetailsEnd);
    //JG

        btnAddTerm = (Button) findViewById(R.id.btnAddTerm);
        btnAddTerm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addTerm = new Intent(TermList.this, TermDetails.class);
        //JG
                Bundle extras = addTerm.getExtras();
                extras.putString("TermName", termNameEditText.getText().toString());
                extras.putString("TermStart", termStartEditText.getText().toString());
                extras.putString("TermEnd", termEndEditText.getText().toString());
        //JG
                startActivity(addTerm);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        //calls the method to screen change
        addTerm();

    //JG
        datasource = new DBCon(this);
        datasource.open();
        List<TermModel> values = datasource.getAllTerms();
        ArrayAdapter<TermModel> adapter = new ArrayAdapter<TermModel>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        Intent intent = new  Intent(TermList.this, TermList.class);

        ArrayAdapter<TermModel> adapter = (ArrayAdapter<TermModel>) getListAdapter();
        TermModel term = null;

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}



//SQLite example code
//        //open DataSource
//        datasource = new DBCon(this);
//        datasource.open();
//
//        List<TermModel> values = datasource.getAllTerms();
//        ArrayAdapter<TermModel> adapter = new ArrayAdapter<TermModel>(this, android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);
//    }

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