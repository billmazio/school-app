package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;

public class StudentNotFoundException extends Exception {
    private static final long serialVersionUID = 123456L;


    public StudentNotFoundException(Student student) {
        super("Student with id: " + student.getId() + " was not found");


    }
    public StudentNotFoundException(String s)  {
        super(s);
    }
}


