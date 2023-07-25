package gr.aueb.cf.schoolapp.dao.exceptions;

import java.sql.SQLException;

public class CityDAOException extends Exception{
    private static final long serialVersionUID = 123456L;

    public CityDAOException(String s) {
        super(s);
    }

    public CityDAOException(String sqlErrorInCityGetByCityName, SQLException e) {
    }
}
