package gr.aueb.cf.schoolapp.model;

public class Meeting {
    private Integer id;
    private String meetingRoom;
    private java.sql.Date meetingDate;
    private Integer teacherId;
    private Integer studentId;




    public Meeting(Integer id, Integer teacherId, Integer studentId, String meetingRoom, java.sql.Date meetingDate) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }


    public Integer getId() { return id; }

    public void setId(int generatedId) {this.id = id;}

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

    public java.sql.Date getMeetingDate() {
        return  meetingDate;
    }

    public void setMeetingDate(java.sql.Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", meetingRoom='" + meetingRoom + '\'' +
                ", meetingDate=" + meetingDate +
                '}';
    }
}
