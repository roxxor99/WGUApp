package gunderson.wgu.org.wguapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseModel {
    private long courseId;
    private long termId;
    private String courseName;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String courseMentorOne;
    private String courseMentorTwo;
    private String courseMentorPhoneOne;
    private String courseMentorPhoneTwo;
    private String courseMentorEmailOne;
    private String courseMentorEmailTwo;
    private int courseNotificationStart;
    private int courseNotificationEnd;
    private String courseNotesTitle;
    private String courseNotesText;
    private SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");

//    public CourseModel(long courseId, long termId, String courseName, String courseStart, String courseEnd,
//                  String courseStatus, String courseMentorOne, String courseMentorTwo, String courseMentorPhoneOne,
//                  String courseMentorPhoneTwo, String courseMentorEmailOne, String courseMentorEmailTwo,
//                  int courseNotificationStart, int courseNotificationEnd, String courseNotesTitle,
//                  String courseNotesText) {
//
//        this.courseId = courseId;
//        this.termId = termId;
//        this.courseName = courseName;
//        this.courseStart = courseStart;
//        this.courseEnd = courseEnd;
//        this.courseStatus = courseStatus;
//        this.courseMentorOne = courseMentorOne;
//        this.courseMentorTwo = courseMentorTwo;
//        this.courseMentorPhoneOne = courseMentorPhoneOne;
//        this.courseMentorPhoneTwo = courseMentorPhoneTwo;
//        this.courseMentorEmailOne = courseMentorEmailOne;
//        this.courseMentorEmailTwo = courseMentorEmailTwo;
//        this.courseNotificationStart = courseNotificationStart;
//        this.courseNotificationEnd = courseNotificationEnd;
//        this.courseNotesTitle = courseNotesTitle;
//        this.courseNotesText = courseNotesText;
//    }

    public CourseModel() {
    }
    //CourseListView display
    @Override
    public String toString() {
        return "Course Name: " + courseName + "\n Start: " + courseStart + "\n End: " + courseEnd;
    }

    public long getCourseId() {
        return courseId;
    }

    public long getCourseTermId() {
        return termId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public String getCourseMentorOne() {
        return courseMentorOne;
    }

    public String getCourseMentorTwo() {
        return courseMentorTwo;
    }

    public String getCourseMentorEmailOne() {
        return courseMentorEmailOne;
    }

    public String getCourseMentorEmailTwo() {
        return courseMentorEmailTwo;
    }

    public String getCourseMentorPhoneOne() {
        return courseMentorPhoneOne;
    }

    public String getCourseMentorPhoneTwo() {
        return courseMentorPhoneTwo;
    }

    public String getCourseNotesText() {
        return courseNotesText;
    }

    public String getCourseNotesTitle() {
        return courseNotesTitle;
    }

    public int getCourseNotificationEnd() {
        return courseNotificationEnd;
    }

    public int getCourseNotificationStart() {
        return courseNotificationStart;
    }

    public String getDates() {
        return courseStart + " to " + courseEnd;
    }

    //Setters
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setCourseTermId(long termId) {
        this.termId = termId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setCourseMentorOne(String courseMentorOne) {
        this.courseMentorOne = courseMentorOne;
    }

    public void setCourseMentorTwo(String courseMentorTwo) {
        this.courseMentorTwo = courseMentorTwo;
    }

    public void setCourseMentorEmailOne(String courseMentorEmailOne) {
        this.courseMentorEmailOne = courseMentorEmailOne;
    }

    public void setCourseMentorEmailTwo(String courseMentorEmailTwo) {
        this.courseMentorEmailTwo = courseMentorEmailTwo;
    }

    public void setCourseMentorPhoneOne(String courseMentorPhoneOne) {
        this.courseMentorPhoneOne = courseMentorPhoneOne;
    }

    public void setCourseMentorPhoneTwo(String courseMentorPhoneTwo) {
        this.courseMentorPhoneTwo = courseMentorPhoneTwo;
    }

    public void setCourseNotesText(String courseNotesText) {
        this.courseNotesText = courseNotesText;
    }

    public void setCourseNotesTitle(String courseNotesTitle) {
        this.courseNotesTitle = courseNotesTitle;
    }

    public void setCourseNotificationEnd(int courseNotificationEnd) {
        this.courseNotificationEnd = courseNotificationEnd;
    }

    public void setCourseNotificationStart(int courseNotificationStart) {
        this.courseNotificationStart = courseNotificationStart;
    }

    //Don't need... Should only need: Exception check on if a term has an appointment before deleting of a term is allowed.
    //Does not check mentor, assessment or note
    //Ensure core Course data is not null
    public boolean isValid() {
        if (courseName.isEmpty() || courseStart.isEmpty() || courseEnd.isEmpty() || courseStatus.isEmpty()) {
            return false;
        }
        try {
            //convert start and end pattern into the supported format
            Date startDate = dtf.parse(courseStart);
            Date endDate = dtf.parse(courseEnd);
            if (!startDate.before(endDate)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Display the course name and date range
    //use in the Course ListView
    public String courseRange() {
        return courseName + " (" + getDates() + ")";
    }
}
