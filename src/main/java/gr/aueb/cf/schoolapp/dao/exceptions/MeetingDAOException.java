package gr.aueb.cf.schoolapp.dao.exceptions;

import java.sql.SQLException;

public class MeetingDAOException extends Exception{
    private static final long serialVersionUID = 123456L;

    public MeetingDAOException(String s) {
        super(s);
    }

    public MeetingDAOException(String s, SQLException e) {
    }

    public MeetingDAOException(String s, Exception e) {
    }
}
