package gunderson.wgu.org.wguapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssessmentModel {
    private long assessmentId;
    private long courseId;
    private String assessmentName;
    private String assessmentType;
    private String assessmentGoalDate;
    private int assessmentNotification;
    private SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");

//    public AssessmentModel(long assessmentId, long courseId, String assessmentName, String assessmentType, String assessmentGoalDate,
//                           int assessmentNotification) {
//        this.assessmentId = assessmentId;
//        this.courseId = courseId;
//        this.assessmentName = assessmentName;
//        this.assessmentType = assessmentType;
//        this.assessmentGoalDate = assessmentGoalDate;
//        this.assessmentNotification = assessmentNotification;
//    }

    public AssessmentModel(){
    }

    //AssessmentListView display
    @Override
    public String toString() {
        return "Assessment Name: " + assessmentName + "\n Goal: " + assessmentGoalDate + "\n Type: " + assessmentType;
    }

    //Getters
    public long getAssessmentId() {
        return assessmentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getAssessmentName() {
        return this.assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public String getAssessmentGoalDate() {
        return assessmentGoalDate;
    }

    public int getAssessmentNotification() {
        return assessmentNotification;
    }


    //Setters
    public void setAssessmentId(long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public void setCourseId(long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public void setAssessmentGoalDate(String assessmentGoalDate) {
        this.assessmentGoalDate = assessmentGoalDate;
    }

    public void setAssessmentNotification(int assessmentNotification) {
        this.assessmentNotification = assessmentNotification;
    }

    public boolean isValid() {
        if (assessmentName.isEmpty() || assessmentType.isEmpty() || assessmentGoalDate.isEmpty()) {
            return false;
        }
        try {
            //convert start and end pattern into the supported format
            dtf.parse(assessmentGoalDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Display the assessment name and type
    //use in the Assessment ListView
    public String assessmentRange() {
        return this.assessmentName + " (" + this.assessmentType + ")";
    }
}