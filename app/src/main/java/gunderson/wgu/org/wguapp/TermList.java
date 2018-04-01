package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermList extends AppCompatActivity {

    public Button btnAddTerm;

    // need to add array and adapter info https://www.youtube.com/watch?v=ws_p8LJ4Uq8
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        //calls the method to screen change
        addTerms();


    }


    }

