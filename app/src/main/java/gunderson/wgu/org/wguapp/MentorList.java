package gunderson.wgu.org.wguapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MentorList extends AppCompatActivity {

    public Button btnAddMen;

    // need to add array and adapter info https://www.youtube.com/watch?v=ws_p8LJ4Uq8
    public void addMentors() {
        btnAddMen = (Button) findViewById(R.id.btnAddMen);
        btnAddMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMentor = new Intent(MentorList.this, MentorDetails.class);
                startActivity(addMentor);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);

        addMentors();
    }
}
