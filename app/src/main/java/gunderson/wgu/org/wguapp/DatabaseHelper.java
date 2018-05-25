package gunderson.wgu.org.wguapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database name and version
    private static final String DATABASE_NAME = "SchoolInfo.db";
    private static final int DATABASE_VERSION = 1;

    //Variables for each table and its associated columns.
    // Table: Terms
    public static final String TABLE_TERMS = "terms";
    public static final String TERM_TABLE_ID = "termId";
    public static final String TERM_NAME = "termName";
    public static final String TERM_START = "termStart";
    public static final String TERM_END = "termEnd";
//    public static final String[] TERMS_COLUMNS = {TERM_TABLE_ID, TERM_NAME, TERM_START, TERM_END};

    // Set the name and type with addition of other attributes such as
    // auto increment and the proper key associations.
    private static final String CREATE_TERMS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TERMS + " (" +
                    TERM_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE " +
                    ")";

    // Table: Courses
    public static final String TABLE_COURSES = "courses";
    public static final String COURSE_TABLE_ID = "courseId";
    public static final String COURSE_TERM_ID = "courseTermId";
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_START = "courseStart";
    public static final String COURSE_END = "courseEnd";
    public static final String COURSE_STATUS = "courseStatus";
    public static final String COURSE_MENTOR_ONE = "courseMentorOne";
    public static final String COURSE_MENTOR_TWO = "courseMentorTwo";
    public static final String COURSE_MENTOR_PHONE_ONE = "courseMentorPhoneOne";
    public static final String COURSE_MENTOR_PHONE_TWO = "courseMentorPhoneTwo";
    public static final String COURSE_MENTOR_EMAIL_ONE = "courseMentorEmailOne";
    public static final String COURSE_MENTOR_EMAIL_TWO = "courseMentorEmailTwo";
    public static final String COURSE_NOTIFICATION_START = "courseNotificationStart";
    public static final String COURSE_NOTIFICATION_END = "courseNotificationEnd";
    public static final String COURSE_NOTES_TITLE = "courseNotesTitle";
    public static final String COURSE_NOTES_TEXT = "courseNotesText";
//    public static final String[] COURSES_COLUMNS = {COURSE_TABLE_ID, COURSE_TERM_ID, COURSE_NAME,
//            COURSE_DESCRIPTION, COURSE_START, COURSE_END, COURSE_STATUS, COURSE_MENTOR, COURSE_MENTOR_PHONE, COURSE_MENTOR_EMAIL, COURSE_NOTIFICATION};

    private static final String CREATE_COURSES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSES + " (" +
                    COURSE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR_ONE + " TEXT, " +
                    COURSE_MENTOR_TWO + " TEXT, " +
                    COURSE_MENTOR_PHONE_ONE + " TEXT, " +
                    COURSE_MENTOR_PHONE_TWO + " TEXT, " +
                    COURSE_MENTOR_EMAIL_ONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL_TWO + " TEXT, " +
                    COURSE_NOTIFICATION_START + " INTEGER, " +
                    COURSE_NOTIFICATION_END + " INTEGER, " +
                    COURSE_NOTES_TITLE + " TEXT, " +
                    COURSE_NOTES_TEXT + " TEXT, " +

                    "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TABLE_TERMS + "(" + TERM_TABLE_ID + ")" +
                    "ON DELETE RESTRICT)";

    // Table: Assessment
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENT_TABLE_ID = "assessmentId";
    public static final String ASSESSMENT_COURSE_ID = "assessmentCourseId";
    public static final String ASSESSMENT_NAME = "assessmentName";
    public static final String ASSESSMENT_TYPE = "assessmentType";
    public static final String ASSESSMENT_GOAL_DATE = "assessmentGoalDate";
    public static final String ASSESSMENT_NOTIFICATION = "assessmentNotification";
//    public static final String[] ASSESSMENTS_COLUMNS = {ASSESSMENT_TABLE_ID, ASSESSMENT_COURSE_ID,
//            ASSESSMENT_TYPE, ASSESSMENT_NAME, ASSESSMENT_GOAL_DATE,
//            ASSESSMENT_NOTIFICATION};

    private static final String CREATE_ASSESSMENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENT_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    ASSESSMENT_GOAL_DATE + " TEXT, " +
                    ASSESSMENT_NOTIFICATION + " INTEGER, " +
                    "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" +
                    ")";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TERMS_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
        db.execSQL(CREATE_ASSESSMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        onCreate(db);
    }
}

