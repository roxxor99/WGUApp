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
    //Just in case might not need
    public static final String TERM_ACTIVE = "termActive";
    public static final String[] TERMS_COLUMNS = {TERM_TABLE_ID, TERM_NAME, TERM_START, TERM_END,
            TERM_ACTIVE};

    // Set the name and type with addition of other attributes such as
    // auto increment and the proper key associations.
    private static final String CREATE_TERMS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TERMS + " (" +
                    TERM_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE, " +
                    TERM_ACTIVE + " INTEGER " +
                    ")";

    // Table: Courses
    public static final String TABLE_COURSES = "courses";
    public static final String COURSE_TABLE_ID = "courseId";
    public static final String COURSE_TERM_ID = "courseTermId";
    public static final String COURSE_NAME = "courseName";
    public static final String COURSE_DESCRIPTION = "courseDescription";
    public static final String COURSE_START = "courseStart";
    public static final String COURSE_END = "courseEnd";
    public static final String COURSE_STATUS = "courseStatus";
    private static final String COURSE_MENTOR = "courseMentor";
    private static final String COURSE_MENTOR_PHONE = "courseMentorPhone";
    private static final String COURSE_MENTOR_EMAIL = "courseMentorEmail";
    private static final String COURSE_NOTIFICATION = "courseNotification";
    private static final String[] COURSES_COLUMNS = {COURSE_TABLE_ID, COURSE_TERM_ID, COURSE_NAME,
            COURSE_DESCRIPTION, COURSE_START, COURSE_END, COURSE_STATUS, COURSE_MENTOR, COURSE_MENTOR_PHONE, COURSE_MENTOR_EMAIL, COURSE_NOTIFICATION};

    private static final String CREATE_COURSES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSES + " (" +
                    COURSE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_DESCRIPTION + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR + " TEXT, " +
                    COURSE_MENTOR_PHONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    COURSE_NOTIFICATION + " INTEGER, " +
                    "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TABLE_TERMS + "(" + TERM_TABLE_ID + ")" +
                    ")";

    // Table: Assessment
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENT_TABLE_ID = "assessmentId";
    public static final String ASSESSMENT_COURSE_ID = "assessmentCourseId";
    public static final String ASSESSMENT_CODE = "assessmentCode";
    public static final String ASSESSMENT_NAME = "assessmentName";
    public static final String ASSESSMENT_DESCRIPTION = "assessmentDescription";
    public static final String ASSESSMENT_DATETIME = "assessmentDatetime";
    public static final String ASSESSMENT_NOTIFICATION = "assessmentNotification";
    public static final String[] ASSESSMENTS_COLUMNS = {ASSESSMENT_TABLE_ID, ASSESSMENT_COURSE_ID,
            ASSESSMENT_CODE, ASSESSMENT_NAME, ASSESSMENT_DESCRIPTION, ASSESSMENT_DATETIME,
            ASSESSMENT_NOTIFICATION};

    private static final String CREATE_ASSESSMENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENT_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_NAME + " TEXT, " +
                    ASSESSMENT_DESCRIPTION + " TEXT, " +
                    ASSESSMENT_CODE + " TEXT, " +
                    ASSESSMENT_DATETIME + " TEXT, " +
                    ASSESSMENT_NOTIFICATION + " INTEGER, " +
                    "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" +
                    ")";

    // Table: Notes
    public static final String TABLE_NOTES = "notes";
    public static final String NOTES_TABLE_ID = "id";
    public static final String NOTES_COURSE_ID = "noteCourseId";
    public static final String NOTES_TEXT = "noteText";
    public static final String[] NOTES_COLUMNS = {NOTES_TABLE_ID, NOTES_COURSE_ID, NOTES_TEXT};

    private static final String CREATE_NOTES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + " (" +
                    NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTES_COURSE_ID + " INTEGER, " +
                    NOTES_TEXT + " TEXT, " +
                    "FOREIGN KEY(" + NOTES_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSE_TABLE_ID + ")" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TERMS_TABLE);
        db.execSQL(CREATE_COURSES_TABLE);
        db.execSQL(CREATE_ASSESSMENTS_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

        onCreate(db);
    }
}

