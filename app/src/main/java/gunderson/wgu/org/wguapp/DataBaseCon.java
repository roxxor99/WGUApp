package gunderson.wgu.org.wguapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseCon extends SQLiteOpenHelper {
    //Database id's -> Java
    private static final String DATABASE_NAME = "SchoolInfo.db";
    private static final int DATABASE_VERSION = 1;

    //Columns by Table
    // Terms
    private static final String TABLE_TERMS = "terms";
    private static final String TERMS_TABLE_ID = "id";
    private static final String TERM_NAME = "termName";
    private static final String TERM_START = "termStart";
    private static final String TERM_END = "termEnd";
    private static final String TERM_ACTIVE = "termActive";
    private static final String TERM_CREATED = "termCreated";
    private static final String[] TERMS_COLUMNS = {TERMS_TABLE_ID, TERM_NAME, TERM_START, TERM_END, TERM_ACTIVE, TERM_CREATED};


    // Courses
    private static final String TABLE_COURSES = "courses";
    private static final String COURSES_TABLE_ID = "id";
    private static final String COURSE_TERM_ID = "courseTermId";
    private static final String COURSE_NAME = "courseName";
    private static final String COURSE_DESCRIPTION = "courseDescription";
    private static final String COURSE_START = "courseStart";
    private static final String COURSE_END = "courseEnd";
    private static final String COURSE_STATUS = "courseStatus";
//    private static final String COURSE_MENTOR = "courseMentor";
//    private static final String COURSE_MENTOR_PHONE = "courseMentorPhone";
//    private static final String COURSE_MENTOR_EMAIL = "courseMentorEmail";
    private static final String COURSE_NOTIFICATIONS = "courseNotifications";
    private static final String COURSE_CREATED = "courseCreated";
    private static final String[] COURSES_COLUMNS = {COURSES_TABLE_ID, COURSE_TERM_ID, COURSE_NAME, COURSE_DESCRIPTION,
            COURSE_START, COURSE_END, COURSE_STATUS, COURSE_NOTIFICATIONS, COURSE_CREATED};

    // Mentors
    private static final String TABLE_MENTORS = "mentors";
    private static final String MENTOR_TABLE_ID = "id";
    private static final String MENTOR_COURSE_ID = "mentorName";
    private static final String MENTOR_NAME = "mentorName";
    private static final String MENTOR_PHONE = "mentorPhone";
    private static final String MENTOR_EMAIL = "mentorEmail";
    private static final String MENTOR_CREATED = "mentorCreated";
    private static final String[] MENTORS_COLUMNS = {MENTOR_TABLE_ID, MENTOR_COURSE_ID, MENTOR_COURSE_ID, MENTOR_NAME,
            MENTOR_PHONE, MENTOR_EMAIL, MENTOR_CREATED};

    // Assessment
    private static final String TABLE_ASSESSMENTS = "assessments";
    private static final String ASSESSMENTS_TABLE_ID = "id";
    private static final String ASSESSMENT_COURSE_ID = "assessmentCourseId";
    private static final String ASSESSMENT_CODE = "assessmentCode";
    private static final String ASSESSMENT_NAME = "assessmentName";
    private static final String ASSESSMENT_DESCRIPTION = "assessmentDescription";
    private static final String ASSESSMENT_DATETIME = "assessmentDatetime";
    private static final String ASSESSMENT_NOTIFICATIONS = "assessmentNotifications";
    private static final String ASSESSMENT_CREATED = "assessmentCreated";
    private static final String[] ASSESSMENTS_COLUMNS = {ASSESSMENTS_TABLE_ID, ASSESSMENT_COURSE_ID, ASSESSMENT_CODE,
            ASSESSMENT_NAME, ASSESSMENT_DESCRIPTION, ASSESSMENT_DATETIME, ASSESSMENT_NOTIFICATIONS, ASSESSMENT_CREATED};

    // Notes
    private static final String TABLE_NOTES = "notes";
    private static final String NOTES_TABLE_ID = "id";
    private static final String NOTES_COURSE_ID = "noteCourseId";
    private static final String NOTES_TEXT = "noteText";
    //public static final String COURSE_NOTE_IMAGE_URI = "courseNoteUri";
    //public static final String COURSE_NOTE_CREATED = "courseNoteCreated";
//    public static final String[] COURSE_NOTES_COLUMNS = {NOTES_TABLE_ID, NOTES_COURSE_ID, NOTES_TEXT,
//            NOTES_IMAGE_URI, NOTE_CREATED};
    private static final String[] NOTES_COLUMNS = {NOTES_TABLE_ID, NOTES_COURSE_ID, NOTES_TEXT};


//    // Assessment Notes table
//    public static final String TABLE_ASSESSMENT_NOTES = "assessmentNotes";
//    public static final String ASSESSMENT_NOTES_TABLE_ID = "id";
//    public static final String ASSESSMENT_NOTE_ASSESSMENT_ID = "assessmentNoteAssessmentId";
//    public static final String ASSESSMENT_NOTE_TEXT = "assessmentNoteText";
//    public static final String ASSESSMENT_NOTE_IMAGE_URI = "assessmentNoteUri";
//    public static final String ASSESSMENT_NOTE_CREATED = "assessmentNoteCreated";
//    public static final String[] ASSESSMENT_NOTES_COLUMNS = {ASSESSMENT_NOTES_TABLE_ID, ASSESSMENT_NOTE_ASSESSMENT_ID,
//            ASSESSMENT_NOTE_TEXT, ASSESSMENT_NOTE_IMAGE_URI, ASSESSMENT_NOTE_CREATED};

//    // Images table
//    public static final String TABLE_IMAGES = "images";
//    public static final String IMAGES_TABLE_ID = "_id";
//    public static final String IMAGE_PARENT_URI = "imageUri";
//    public static final String IMAGE_TIMESTAMP = "imageTimestamp";
//    public static final String IMAGE_CREATED = "imageCreated";
//    public static final String[] IMAGES_COLUMNS = {IMAGES_TABLE_ID, IMAGE_PARENT_URI, IMAGE_TIMESTAMP,
//            IMAGE_CREATED};




    // The database will be built by creating the tables layed out below...
    // SQL commands to set the name and type with addition of other attributes such as
    // auto increment and the key associations.
    // Terms
    private static final String CREATE_TERMS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TERMS + " (" +
                    TERMS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE, " +
                    TERM_ACTIVE + " INTEGER, " +
                    TERM_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";
    // Courses
    private static final String CREATE_COURSES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSES + " (" +
                    COURSES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_DESCRIPTION + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
//                    COURSE_MENTOR + " TEXT, " +
//                    COURSE_MENTOR_PHONE + " TEXT, " +
//                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    COURSE_NOTIFICATIONS + " INTEGER, " +
                    COURSE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TABLE_TERMS + "(" + TERMS_TABLE_ID + ")" +
                    ")";

    // Mentors
    private static final String CREATE_MENTORS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MENTORS + " (" +
                    MENTOR_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MENTOR_COURSE_ID + " INTEGER, " +
                    MENTOR_NAME + " TEXT, " +
                    MENTOR_PHONE + " TEXT, " +
                    MENTOR_EMAIL + " TEXT, " +
                    MENTOR_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + MENTOR_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSES_TABLE_ID + ")" +
                    ")";

    // Assessments
    private static final String CREATE_ASSESSMENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENTS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_DESCRIPTION + " TEXT, " +
                    ASSESSMENT_CODE + " TEXT, " +
                    ASSESSMENT_DATETIME + " TEXT, " +
                    ASSESSMENT_NOTIFICATIONS + " INTEGER, " +
                    ASSESSMENT_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSES_TABLE_ID + ")" +
                    ")";

    // Notes
    private static final String CREATE_NOTES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + " (" +
                    NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTES_COURSE_ID + " INTEGER, " +
                    NOTES_TEXT + " TEXT, " +
//                    NOTES_IMAGE_URI + " TEXT, " +
//                    NOTES_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(" + NOTES_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSES_TABLE_ID + ")" +
                    ")";

//    // Assessment Notes table
//    private static final String ASSESSMENT_NOTES_TABLE_CREATE =
//            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENT_NOTES + " (" +
//                    ASSESSMENT_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    ASSESSMENT_NOTE_ASSESSMENT_ID + " INTEGER, " +
//                    ASSESSMENT_NOTE_TEXT + " TEXT, " +
//                    ASSESSMENT_NOTE_IMAGE_URI + " TEXT, " +
//                    ASSESSMENT_NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
//                    "FOREIGN KEY(" + ASSESSMENT_NOTE_ASSESSMENT_ID + ") REFERENCES " + TABLE_ASSESSMENTS + "(" + ASSESSMENTS_TABLE_ID + ")" +
//                    ")";
//    // Images table
//    private static final String IMAGES_TABLE_CREATE =
//            "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + " (" +
//                    IMAGES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    IMAGE_PARENT_URI + " TEXT, " +
//                    IMAGE_TIMESTAMP + " INTEGER, " +
//                    IMAGE_CREATED + " TEXT default CURRENT_TIMESTAMP" +
//                    ")";
    public DataBaseCon(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TERMS_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
        db.execSQL(CREATE_MENTORS_TABLE);
        db.execSQL(CREATE_ASSESSMENTS_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
//        db.execSQL(ASSESSMENT_NOTES_TABLE_CREATE);
//        db.execSQL(IMAGES_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENT_NOTES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);


        onCreate(db);
    }
}