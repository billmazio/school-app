package gr.aueb.cf.schoolapp.dao.exceptions;

import java.sql.SQLException;

public class SpecialityDAOException extends Exception{
    private static final long serialVersionUID = 123456L;

    public SpecialityDAOException(String s) {
        super(s);
    }


    public SpecialityDAOException(String s, SpecialityDAOException e) {
    }
}
