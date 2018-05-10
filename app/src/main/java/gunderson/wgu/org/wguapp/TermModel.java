package gunderson.wgu.org.wguapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TermModel {
    private long termId;
    private String termName;
    private String termStart;
    private String termEnd;
    private SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");

//        public TermModel(long termId, String termName, String termStart, String termEnd) {
//        this.termId = termId;
//        this.termName = termName;
//        this.termStart = termStart;
//        this.termEnd = termEnd;
//    }

    public TermModel(){

    }

    //example shows this is needed for the ArrayAdapter in the ListView
//    @Override
//    public String toString() {
//        return comment;
//    }

    //Getters
    public long getTermId() {
        return termId;
    }

    public String getTermName() {
        return termName;
    }

    public String getTermStart() {
        return termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public String getDates() {
        return termStart + " to " + termEnd;
    }


    //Setters
    public void setTermId(long termId) {
        this.termId = termId;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public void setTermStart(String  termStart) {
        this.termStart = termStart;
    }

    public void setTermEnd(String  termEnd) {
        this.termEnd = termEnd;
    }


    //Ensure Term data is not null
    public boolean isValid() {
        if (termName.isEmpty() || termStart.isEmpty() || termEnd.isEmpty()) {
            return false;
        }
        try {
            //convert start and end pattern into the supported format
            Date startDate = dtf.parse(termStart);
            Date endDate = dtf.parse(termEnd);
            //Check if end is before start
            if (!startDate.before(endDate)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Display the term name and date range
    //use in the Term ListView
    public String termRange() {
        return termName + " (" + getDates() + ")";
    }
}
