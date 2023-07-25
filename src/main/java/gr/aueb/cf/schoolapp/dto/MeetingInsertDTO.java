package gr.aueb.cf.schoolapp.dto;

import java.sql.Date;

public class MeetingInsertDTO {
    private Integer teacherId;
    private Integer studentId;
    private String meetingRoom;
    private Date meetingDate;

    public MeetingInsertDTO() {}


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

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }


    public void setId(int id) {
    }
}

