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
//    private DBCon datasource;

//    DatabaseHelper mDatabaseHelper;

//    public EditText termNameEditText;
//    public TextView termStartTextView;
//    public TextView termEndTextView;


    public void configAddTerm() {
//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
//
//        String termName = extras.getString("TermName");
//        String termStart = extras.getString("TermStart");
//        String termEnd = extras.getString("TermEnd");
//
//        TermModel term = new TermModel();
//        term.setTermName(termName);
//        term.setTermStart(termStart);
//        term.setTermEnd(termEnd);
//
//        datasource = new DBCon(this);
//        datasource.open();
//        datasource.createTerm(term);
//        datasource.close();
//        }

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
        configAddTerm();

        //Send selected list item to TermDetails
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                TermModel term = (TermModel) parent.getItemAtPosition(position);
                //get info
                intent.putExtra("termId", term.getTermId());
                intent.putExtra("termName", term.getTermName());
                intent.putExtra("termStart", term.getTermStart());
                intent.putExtra("termEnd", term.getTermEnd());

                startActivity(intent);
            }
        });
    }

//        datasource = new DBCon(this);
//        datasource.open();
//        List<TermModel> listValue = datasource.getAllTerms();
//        datasource.close();
//
//        ArrayAdapter<TermModel> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, listValue);
//        setListAdapter(adapter);
//    }

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
//        TermModel term = null;

        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        DBCon datasource = new DBCon(this);
        datasource.open();
        List<TermModel> listValue = datasource.getAllTerms();
        datasource.close();
        ArrayAdapter<TermModel> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listValue);
        setListAdapter(adapter);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
