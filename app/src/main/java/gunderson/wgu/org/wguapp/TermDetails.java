package gunderson.wgu.org.wguapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class TermDetails extends AppCompatActivity {
    public Button btnTermDetailsAdd;

    public void addCourse() {
        btnTermDetailsAdd = (Button) findViewById(R.id.btnTermDetailsAdd);
        btnTermDetailsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCourse = new Intent(TermDetails.this, CourseDetails.class);
                startActivity(addCourse);
            }
        });
    }

    //    Data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        addCourse();

    }
}