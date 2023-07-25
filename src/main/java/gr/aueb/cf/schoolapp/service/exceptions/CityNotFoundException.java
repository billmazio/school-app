package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.City;

public class CityNotFoundException extends Exception{
    private static final long serialVersionUID = 123456L;

    public CityNotFoundException(City city) {
        super("City with id: " + city.getId() + " was not found");
    }

    public CityNotFoundException(String s) {
        super(s);
    }
}
