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


public class TermList extends ListActivity {
    public Button btnAddTerm;

    public void configAddTerm() {
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


    public void onClick(View view) {
        ArrayAdapter<TermModel> adapter = (ArrayAdapter<TermModel>) getListAdapter();
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
