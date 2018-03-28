package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermList extends AppCompatActivity {

    Button btnAddTerm;
    Button btnDeleteTerm;
    // need to add array and adapter info https://www.youtube.com/watch?v=ws_p8LJ4Uq8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        btnAddTerm = (Button) findViewById(R.id.btnAddTerm);
        btnDeleteTerm = (Button) findViewById(R.id.btnDeleteTerm);


    }

    public void addTerms() {
        btnAddTerm = (Button) findViewById(R.id.btnAddTerm);
        btnAddTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTerm = new Intent(TermList.this, AddTerms.class);
                startActivity(addTerm);
            }
        });
    }
    //Needs updated still
//    public void deleteTerms() {
//        btnDeleteTerm = (Button) findViewById(R.id.btnDeleteTerm);
//        btnDeleteTerm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent deleteTerm = new Intent(landing.this, AddTerms.class);
//                startActivity(deleteTerm);
//            }
//        });
//    }
}