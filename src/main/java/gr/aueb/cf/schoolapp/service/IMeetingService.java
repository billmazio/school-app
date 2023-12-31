package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.exceptions.MeetingNotFoundException;

import java.util.List;

public interface IMeetingService {


    Meeting insertMeeting(MeetingInsertDTO dto) throws MeetingDAOException;


    Meeting updateMeeting(MeetingUpdateDTO dto) throws MeetingNotFoundException, MeetingDAOException;

    void deleteMeeting(int id) throws MeetingNotFoundException, MeetingDAOException;


    List<Meeting> getMeetingsByMeetingRoom(String meetingRoom) throws MeetingDAOException;


    Meeting getMeetingById(int id) throws MeetingNotFoundException, MeetingDAOException;
}
