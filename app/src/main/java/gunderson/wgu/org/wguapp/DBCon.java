package gunderson.wgu.org.wguapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import junit.framework.TestResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//Datasource
public class DBCon {
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    //Term Columns:
    private static final String[] termsColumns = {
            DatabaseHelper.TERM_TABLE_ID,
            DatabaseHelper.TERM_NAME,
            DatabaseHelper.TERM_START,
            DatabaseHelper.TERM_END};

    //Course Columns:
    private static final String[] courseColumns = {
            DatabaseHelper.COURSE_TABLE_ID,
            DatabaseHelper.COURSE_TERM_ID,
            DatabaseHelper.COURSE_NAME,
            DatabaseHelper.COURSE_START,
            DatabaseHelper.COURSE_END,
            DatabaseHelper.COURSE_STATUS,
            DatabaseHelper.COURSE_MENTOR_ONE,
            DatabaseHelper.COURSE_MENTOR_TWO,
            DatabaseHelper.COURSE_MENTOR_PHONE_ONE,
            DatabaseHelper.COURSE_MENTOR_PHONE_TWO,
            DatabaseHelper.COURSE_MENTOR_EMAIL_ONE,
            DatabaseHelper.COURSE_MENTOR_EMAIL_TWO,
            DatabaseHelper.COURSE_NOTIFICATION_START,
            DatabaseHelper.COURSE_NOTIFICATION_END,
            DatabaseHelper.COURSE_NOTES_TITLE,
            DatabaseHelper.COURSE_NOTES_TEXT};

    //Assessment Columns:
    private static final String[] assessmentColumns = {
            DatabaseHelper.ASSESSMENT_TABLE_ID,
            DatabaseHelper.ASSESSMENT_COURSE_ID,
            DatabaseHelper.ASSESSMENT_NAME,
            DatabaseHelper.ASSESSMENT_TYPE,
            DatabaseHelper.ASSESSMENT_NOTIFICATION,
            DatabaseHelper.ASSESSMENT_GOAL_DATE};

    public DBCon(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public TermModel createTerm(TermModel term) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TERM_NAME, term.getTermName());
        values.put(DatabaseHelper.TERM_START, term.getTermStart());
        values.put(DatabaseHelper.TERM_END, term.getTermEnd());
        long insertId = db.insert(DatabaseHelper.TABLE_TERMS, null, values);
        term.setTermId(insertId);
        return term;
    }


    //Create Methods
    public CourseModel createCourse(CourseModel course) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COURSE_NAME, course.getCourseName());
        values.put(DatabaseHelper.COURSE_TERM_ID, course.getCourseTermId());
        values.put(DatabaseHelper.COURSE_START, course.getCourseStart());
        values.put(DatabaseHelper.COURSE_END, course.getCourseEnd());
        values.put(DatabaseHelper.COURSE_STATUS, course.getCourseStatus());
        values.put(DatabaseHelper.COURSE_MENTOR_ONE, course.getCourseMentorOne());
        values.put(DatabaseHelper.COURSE_MENTOR_TWO, course.getCourseMentorTwo());
        values.put(DatabaseHelper.COURSE_MENTOR_PHONE_ONE, course.getCourseMentorPhoneOne());
        values.put(DatabaseHelper.COURSE_MENTOR_PHONE_TWO, course.getCourseMentorPhoneTwo());
        values.put(DatabaseHelper.COURSE_MENTOR_EMAIL_ONE, course.getCourseMentorEmailOne());
        values.put(DatabaseHelper.COURSE_MENTOR_EMAIL_TWO, course.getCourseMentorEmailTwo());
        values.put(DatabaseHelper.COURSE_NOTIFICATION_START, course.getCourseNotificationStart());
        values.put(DatabaseHelper.COURSE_NOTIFICATION_END, course.getCourseNotificationEnd());
        values.put(DatabaseHelper.COURSE_NOTES_TITLE, course.getCourseNotesTitle());
        values.put(DatabaseHelper.COURSE_NOTES_TEXT, course.getCourseNotesText());

        long insertId = db.insert(DatabaseHelper.TABLE_COURSES, null, values);
        course.setCourseId(insertId);
        return course;
    }

    public AssessmentModel createAssessment(AssessmentModel assessment) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ASSESSMENT_NAME, assessment.getAssessmentName());
        values.put(DatabaseHelper.ASSESSMENT_TYPE, assessment.getAssessmentType());
        values.put(DatabaseHelper.ASSESSMENT_NOTIFICATION, assessment.getAssessmentNotification());
        values.put(DatabaseHelper.ASSESSMENT_GOAL_DATE, assessment.getAssessmentGoalDate());
        long insertId = db.insert(DatabaseHelper.TABLE_ASSESSMENTS, null, values);
        assessment.setAssessmentId(insertId);
        return assessment;
    }


    //Update Methods
    public void updateTerm(TermModel term) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TERM_TABLE_ID, term.getTermId());
        values.put(DatabaseHelper.TERM_NAME, term.getTermName());
        values.put(DatabaseHelper.TERM_START, term.getTermStart());
        values.put(DatabaseHelper.TERM_END, term.getTermEnd());
        db.update(DatabaseHelper.TABLE_TERMS, values, DatabaseHelper.TERM_TABLE_ID + "=" + term.getTermId(), null);
    }

    public void updateCourse(CourseModel course) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COURSE_NAME, course.getCourseName());
        values.put(DatabaseHelper.COURSE_START, course.getCourseStart());
        values.put(DatabaseHelper.COURSE_END, course.getCourseEnd());
        values.put(DatabaseHelper.COURSE_STATUS, course.getCourseStatus());
        values.put(DatabaseHelper.COURSE_MENTOR_ONE, course.getCourseMentorOne());
        values.put(DatabaseHelper.COURSE_MENTOR_TWO, course.getCourseMentorTwo());
        values.put(DatabaseHelper.COURSE_MENTOR_PHONE_ONE, course.getCourseMentorPhoneOne());
        values.put(DatabaseHelper.COURSE_MENTOR_PHONE_TWO, course.getCourseMentorPhoneTwo());
        values.put(DatabaseHelper.COURSE_MENTOR_EMAIL_ONE, course.getCourseMentorEmailOne());
        values.put(DatabaseHelper.COURSE_MENTOR_EMAIL_TWO, course.getCourseMentorEmailTwo());
        values.put(DatabaseHelper.COURSE_NOTIFICATION_START, course.getCourseNotificationStart());
        values.put(DatabaseHelper.COURSE_NOTIFICATION_END, course.getCourseNotificationEnd());
        values.put(DatabaseHelper.COURSE_NOTES_TITLE, course.getCourseNotesTitle());
        values.put(DatabaseHelper.COURSE_NOTES_TEXT, course.getCourseNotesText());
        db.update(DatabaseHelper.TABLE_COURSES, values, DatabaseHelper.COURSE_TABLE_ID + "=" + course.getCourseId(), null);
    }

    public void updateAssessment(AssessmentModel assessment) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ASSESSMENT_NAME, assessment.getAssessmentName());
        values.put(DatabaseHelper.ASSESSMENT_TYPE, assessment.getAssessmentType());
        values.put(DatabaseHelper.ASSESSMENT_NOTIFICATION, assessment.getAssessmentNotification());
        values.put(DatabaseHelper.ASSESSMENT_GOAL_DATE, assessment.getAssessmentGoalDate());
        db.update(DatabaseHelper.TABLE_ASSESSMENTS, values, DatabaseHelper.ASSESSMENT_TABLE_ID + "=" + assessment.getAssessmentId(), null);
    }


    //Delete Methods
    public void deleteTerm(long id) {
        System.out.println("Term with id: " + id + " deleted");
        db.delete(DatabaseHelper.TABLE_TERMS, DatabaseHelper.TERM_TABLE_ID
                + " = " + id, null);
    }

    public void deleteCourse(CourseModel course) {
        long id = course.getCourseId();
        System.out.println("Course with id: " + id + " deleted");
        db.delete(DatabaseHelper.TABLE_COURSES, DatabaseHelper.COURSE_TABLE_ID
                + " = " + id, null);
    }


    public void deleteAssessment(AssessmentModel assessment) {
        long id = assessment.getAssessmentId();
        System.out.println("Assessment with id: " + id + " deleted");
        db.delete(DatabaseHelper.TABLE_ASSESSMENTS, DatabaseHelper.ASSESSMENT_TABLE_ID
                + " = " + id, null);
    }

    //Get All data from each Table(Terms, Courses, Assessments)
    public List<TermModel> getAllTerms() {
        List<TermModel> termList = new ArrayList<TermModel>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_TERMS,
                termsColumns, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                TermModel term = new TermModel();
                term.setTermId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TERM_TABLE_ID)));
                term.setTermName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_NAME)));
                term.setTermStart(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_START)));
                term.setTermEnd(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_END)));
                termList.add(term);
            }
        }
        return termList;
    }

    public List<CourseModel> getCourses(long termId) {
        List<CourseModel> courseList = new ArrayList<CourseModel>();
        String[] selectionArgs = {Long.toString(termId)};
        Cursor cursor = db.query(DatabaseHelper.TABLE_COURSES,
                courseColumns, DatabaseHelper.COURSE_TERM_ID + " = ?", selectionArgs, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                CourseModel course = new CourseModel();
                course.setCourseId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COURSE_TABLE_ID)));
                course.setCourseTermId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COURSE_TERM_ID)));
                course.setCourseName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NAME)));
                course.setCourseStart(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_START)));
                course.setCourseEnd(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_END)));
                course.setCourseStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_STATUS)));
                course.setCourseMentorOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_ONE)));
                course.setCourseMentorTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_TWO)));
                course.setCourseMentorPhoneOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_PHONE_ONE)));
                course.setCourseMentorPhoneTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_PHONE_TWO)));
                course.setCourseMentorEmailOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_EMAIL_ONE)));
                course.setCourseMentorEmailTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_EMAIL_TWO)));
                course.setCourseNotificationStart(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTIFICATION_START)));
                course.setCourseNotificationEnd(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTIFICATION_END)));
                course.setCourseNotesTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTES_TITLE)));
                course.setCourseNotesText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTES_TEXT)));
                courseList.add(course);
            }
        }
        return courseList;
    }

    public List<AssessmentModel> getAllAssessments() {
        List<AssessmentModel> assessmentList = new ArrayList<AssessmentModel>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_ASSESSMENTS,
                assessmentColumns, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                AssessmentModel assessment = new AssessmentModel();
//                assessment.setCourseId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COURSE_TABLE_ID)));

                assessment.setAssessmentId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_TABLE_ID)));
                assessment.setAssessmentName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_NAME)));
                assessment.setAssessmentGoalDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_GOAL_DATE)));
                assessmentList.add(assessment);
            }
        }
        return assessmentList;
    }


    //Cursor
    private TermModel cursorToTerm(Cursor cursor) {
        TermModel term = new TermModel();
        term.setTermId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TERM_TABLE_ID)));
        term.setTermName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_NAME)));
        term.setTermStart(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_START)));
        term.setTermEnd(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TERM_END)));
        return term;
    }

    private CourseModel cursorToCourse(Cursor cursor) {
        CourseModel course = new CourseModel();
        course.setCourseTermId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COURSE_TERM_ID)));
        course.setCourseId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COURSE_TABLE_ID)));
        course.setCourseName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NAME)));
        course.setCourseStart(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_START)));
        course.setCourseEnd(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_END)));
        course.setCourseStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_STATUS)));
        course.setCourseMentorOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_ONE)));
        course.setCourseMentorTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_TWO)));
        course.setCourseMentorPhoneOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_PHONE_ONE)));
        course.setCourseMentorPhoneTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_PHONE_TWO)));
        course.setCourseMentorEmailOne(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_EMAIL_ONE)));
        course.setCourseMentorEmailTwo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_MENTOR_EMAIL_TWO)));
        course.setCourseNotificationStart(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTIFICATION_START)));
        course.setCourseNotificationEnd(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTIFICATION_END)));
        course.setCourseNotesTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTES_TITLE)));
        course.setCourseNotesText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COURSE_NOTES_TEXT)));
        return course;
    }

    private AssessmentModel cursorToAssessment(Cursor cursor) {
        AssessmentModel assessment = new AssessmentModel();
        assessment.setCourseId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_COURSE_ID)));
        assessment.setAssessmentId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_TABLE_ID)));
        assessment.setAssessmentName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_NAME)));
        assessment.setAssessmentGoalDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_GOAL_DATE)));
        assessment.setAssessmentType(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_TYPE)));
        assessment.setAssessmentNotification(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ASSESSMENT_NOTIFICATION)));
        return assessment;
    }
}