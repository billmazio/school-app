package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Speciality;
import gr.aueb.cf.schoolapp.model.Teacher;

public class SpecialityNotFoundException extends Exception {
    private static final long serialVersionUID = 123456L;


    public SpecialityNotFoundException(Speciality speciality) {
        super("Speciality with id: " + speciality.getId() + " was not found");


    }
    public SpecialityNotFoundException(String s)  {
        super(s);
    }
}
