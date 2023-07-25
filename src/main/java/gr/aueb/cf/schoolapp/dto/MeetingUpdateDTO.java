package gr.aueb.cf.schoolapp.dto;

import java.util.Date;

public class MeetingUpdateDTO extends BaseDTO {

    private Integer teacherId; // Add the teacherId field to hold the Teacher ID
    private Integer studentId; // Add the studentId field to hold the Student ID
    private String meetingRoom;
    private java.sql.Date meetingDate;

    public MeetingUpdateDTO() {
    }


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(java.sql.Date meetingDate) {
        this.meetingDate = meetingDate;
    }
}
