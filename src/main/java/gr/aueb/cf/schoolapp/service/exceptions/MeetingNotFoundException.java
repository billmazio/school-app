package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Meeting;

public class MeetingNotFoundException extends Exception{
    private static final long serialVersionUID = 123456L;

    public MeetingNotFoundException(Meeting meeting) {
        super("Meeting with id: " + meeting + " was not found");
    }

    public MeetingNotFoundException(String s) {
        super(s);
    }
}
