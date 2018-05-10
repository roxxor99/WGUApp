package gunderson.wgu.org.wguapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TermDetails extends AppCompatActivity {
    public Button btnTermDetailsAddCourse;
    public EditText termNameEditText;

    //DatePicker
    private TextView mTermStartDate;
    private TextView mTermEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;

    private static final String TAG = "TermDetails";

    public void addCourse() {
//?!
//        termNameEditText = (EditText) findViewById(R.id.ptTermDetailsName);
//        mTermStartDate = (TextView) findViewById(R.id.tvTermDetailsStart);
//        mTermEndDate = (TextView) findViewById(R.id.tvTermDetailsEnd);

        btnTermDetailsAddCourse = (Button) findViewById(R.id.btnTermDetailsAddCourse);
        btnTermDetailsAddCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addCourse = new Intent(TermDetails.this, CourseDetails.class);
//                Bundle extras = addCourse.getExtras();
//                extras.putString("TermName",termNameEditText.getText().toString());
//                extras.putString("TermStart",mTermStartDate.getText().toString());
//                extras.putString("TermEnd",mTermEndDate.getText().toString());

                startActivity(addCourse);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        addCourse();

        mTermStartDate = (TextView) findViewById(R.id.tvTermDetailsStart);
        mTermEndDate = (TextView) findViewById(R.id.tvTermDetailsEnd);

        mTermStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            //get today's date
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TermDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTermEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            //get today's date
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TermDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mTermStartDate.setText(date);
            }
        };

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //January = 0 need to add 1 to get correct month
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mTermEndDate.setText(date);
            }
        };
    }

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

    @Override
    public void onBackPressed() {
        termNameEditText = (EditText) findViewById(R.id.ptTermDetailsName);
        mTermStartDate = (TextView) findViewById(R.id.tvTermDetailsStart);
        mTermEndDate = (TextView) findViewById(R.id.tvTermDetailsEnd);
        Intent i = new Intent(this, TermList.class);
        i.putExtra("TermName", termNameEditText.getText().toString());
        i.putExtra("TermStart", mTermStartDate.getText().toString());
        i.putExtra("TermEnd", mTermEndDate.getText().toString());
        startActivity(i);
    }

    //save on back button
//    @Override
//    public void onSaveInstanceState(Bundle extras) {
//        super.onSaveInstanceState(extras);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
////        Bundle extras = onSaveInstanceState.getExtras();
//                extras.putString("TermName",termNameEditText.getText().toString());
//                extras.putString("TermStart",mTermStartDate.getText().toString());
//                extras.putString("TermEnd",mTermEndDate.getText().toString());
//    }

}