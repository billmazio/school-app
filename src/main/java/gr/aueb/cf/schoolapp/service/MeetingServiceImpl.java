package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IMeetingDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.exceptions.MeetingNotFoundException;

import java.sql.Date;
import java.util.List;

public class MeetingServiceImpl implements IMeetingService {
    private IMeetingDAO meetingDAO;

    public MeetingServiceImpl(IMeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }


    @Override
    public Meeting insertMeeting(MeetingInsertDTO dto) throws MeetingDAOException {
        if (dto == null) return null;
        Meeting meeting;
        try {
            meeting = map(dto);
            return meetingDAO.insert(meeting);
        } catch (MeetingDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }



    public Meeting updateMeeting(MeetingUpdateDTO dto) throws MeetingNotFoundException, MeetingDAOException {
        if (dto == null) return null;
        Meeting meeting;
        try {
            meeting = map(dto);

            if (meetingDAO.getById(meeting.getId()) == null) {
                throw new MeetingNotFoundException(meeting);
            }

            return meetingDAO.update(meeting);
        } catch (MeetingDAOException | MeetingNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }



    @Override
    public void deleteMeeting(int id) throws MeetingDAOException, MeetingNotFoundException {
        Meeting meeting;
        try {
            meeting = meetingDAO.getById(id);

            if (meeting == null) {
                throw new MeetingNotFoundException("Meeting with ID: " + id + " was not found");
            }

            meetingDAO.delete(id);
        } catch (MeetingDAOException | MeetingNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public List<Meeting> getMeetingsByMeetingRoom(String meetingRoom) throws MeetingDAOException {
        List<Meeting> meetings;
        try {
            meetings = meetingDAO.getByMeetingRoom(meetingRoom);
            return meetings;
        } catch (MeetingDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }



    @Override
    public Meeting getMeetingById(int id) throws MeetingDAOException, MeetingNotFoundException {
        Meeting meeting;
        try {
            meeting = meetingDAO.getById(id);
            if (meeting == null) {
                throw new MeetingNotFoundException("Search Error: Meeting with ID: " + id + " was not found");
            }
            return meeting;
        } catch (MeetingDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Meeting map(MeetingInsertDTO dto) {
        return new Meeting(null, dto.getTeacherId(), dto.getStudentId(), dto.getMeetingRoom(), dto.getMeetingDate());
    }

    private Meeting map(MeetingUpdateDTO dto) {
        return new Meeting(dto.getId(), dto.getTeacherId(), dto.getStudentId(), dto.getMeetingRoom(), (Date) dto.getMeetingDate());
    }


}
