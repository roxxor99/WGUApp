package gunderson.wgu.org.wguapp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import javax.sql.DataSource;


public class TermList extends ListActivity {
    public Button btnAddTerm;
    private DBCon datasource;

    DatabaseHelper mDatabaseHelper;

    public EditText termNameEditText;
    public TextView termStartTextView;
    public TextView termEndTextView;


    public void addTerm() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){

        String termName = extras.getString("TermName");
        String termStart = extras.getString("TermStart");
        String termEnd = extras.getString("TermEnd");

        TermModel term = new TermModel();
        term.setTermName(termName);
        term.setTermStart(termStart);
        term.setTermEnd(termEnd);

        datasource = new DBCon(this);
        datasource.open();
        datasource.createTerm(term);
        datasource.close();
        }

        btnAddTerm = findViewById(R.id.btnAddTerm);
        btnAddTerm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addTerm = new Intent(TermList.this, TermDetails.class);
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

        datasource = new DBCon(this);
        datasource.open();
        List<TermModel> listValue = datasource.getAllTerms();
        datasource.close();

        ArrayAdapter<TermModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listValue);
        setListAdapter(adapter);
    }

//        //onItemClickListener: get info from item in ListView and populate TermDetails to edit.
////        lv = (ListView) findViewById(listView);
//        ListView lv = getListView();
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TermList.this, TermDetails.class);
//                TermModel term = (TermModel) parent.getItemAtPosition(position);
//                intent.putExtra(DBCon.CONTENT_ITEM_TYPE, term.getTermId());
//                startActivityForResult(intent, EDITOR_REQUEST_CODE);
//            }
//        });
//    }


    public void onClick(View view) {
//        Intent intent = new Intent(TermList.this, TermDetails.class);
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
//
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