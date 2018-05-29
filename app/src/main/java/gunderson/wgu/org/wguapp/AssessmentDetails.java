package gunderson.wgu.org.wguapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AssessmentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mTypeSpinner;
    private long courseId;
    private long assessmentId;
    public EditText assessmentNameEditText;
    Calendar cal;

    //DatePicker
    private TextView mGoalDate;
    private DatePickerDialog.OnDateSetListener mGoalDateSetListener;

    private static final String TAG = "AssessmentDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        //variables for controls
        assessmentNameEditText = findViewById(R.id.ptAssessmentDetailName);
        mGoalDate = findViewById(R.id.tvAssessmentDetailGoalDate);
        mTypeSpinner = findViewById(R.id.spinnerAssessmentDetailType);


        //Spinner
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.assessment_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(adapter);
        mTypeSpinner.setOnItemSelectedListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            courseId = extras.getLong("courseId");
            assessmentId = extras.getLong("assessmentId");
            String assessmentName = extras.getString("assessmentName");
            String assessmentGoal = extras.getString("assessmentGoalDate");
            String assessmentType = extras.getString("assessmentType");

            //Assign to proper controls
            assessmentNameEditText.setText(assessmentName);
            mGoalDate.setText(assessmentGoal);

            int typePosition = adapter.getPosition(assessmentType);
            mTypeSpinner.setSelection(typePosition);
        }

        //DatePicker
        mGoalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            //get today's date
            public void onClick(View view) {
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AssessmentDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mGoalDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mGoalDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mGoalDate.setText(date);
            }
        };
    }

    //    public void setAlert(View view) {
    public void setAlert(Calendar cal) {
//        AlarmManager mAlarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent mIntent = new Intent(this, MyReceiver.class);
//        PendingIntent notifyIntent = PendingIntent.getBroadcast(this, 1, mIntent, 0);
//
//        //initiate a Switch
//        Switch switchGoalAlert = findViewById(R.id.ptAssessmentDetailGoalAlert);
//        // check current state of a Switch (true or false).
//        Boolean switchState = switchGoalAlert.isChecked();
//
//        if (switchState == false) {
//            return;
//        } else {
////            if (switchState != null) {
//                assert mAlarm != null;
//                mAlarm.set(AlarmManager.RTC_WAKEUP, cal.get(Calendar.MILLISECOND), notifyIntent);
////            }else{
////                return;
////            }
//        }

        AlarmManager mAlarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent mIntent = new Intent(this, MyReceiver.class);
        PendingIntent notifyIntent = PendingIntent.getBroadcast(this, 1, mIntent, 0);

        mAlarm.set(AlarmManager.RTC_WAKEUP, cal.get(Calendar.MILLISECOND), notifyIntent);
//        mAlarm.setExact(AlarmManager.RTC_WAKEUP, cal.get(Calendar.MILLISECOND), notifyIntent);


//        String today = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
////        LocalDate now = new LocalDate;
//
//        //Build the start notification
//        startNotification.setSmallIcon(R.drawable.owl);
//        startNotification.setTicker("WGU Assessment Start");
////        startNotification.setWhen(System.currentTimeMillis());
////        startNotification.setWhen(DBCon..);
//        startNotification.setContentTitle("Assessment Start Alert");
//        startNotification.setContentText("You have a new Assessment starting");
//
//        //when clicked return to AssessmentDetails
//        Intent intent = new Intent(this, AssessmentDetails.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        startNotification.setContentIntent(pendingIntent);
//
//        //Builds notification and sends to device
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(12345, startNotification.build());
    }


    //Appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get id of item selected in menu
        int id = item.getItemId();

        if (id == R.id.menuDelete) {
            DBCon datasource = new DBCon(this);
            datasource.open();
            datasource.deleteAssessment(assessmentId);
            datasource.close();
            finish();

            Toast.makeText(this, "Assessment was deleted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String sSelected = adapterView.getItemAtPosition(position).toString();
    }

    //Spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void saveAssessment(View view) {
        setAlert(cal);
        //Create variables
        String assessmentName = assessmentNameEditText.getText().toString();
        String assessmentGoal = mGoalDate.getText().toString();
        String assessmentType = mTypeSpinner.getSelectedItem().toString();

        //set variables with data
        final AssessmentModel assessment = new AssessmentModel();
        assessment.setCourseId(courseId);
        assessment.setAssessmentId(assessmentId);
        assessment.setAssessmentName(assessmentName);
        assessment.setAssessmentGoalDate(assessmentGoal);
        assessment.setAssessmentType(assessmentType);


        DBCon datasource = new DBCon(this);
        datasource.open();
//        Bundle extras = getIntent().getExtras();

        if (assessmentId == 0) {
            datasource.createAssessment(assessment);
        } else {
            datasource.updateAssessment(assessment);
        }

        datasource.close();
        finish();
    }
}
