package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingDAOImpl implements IMeetingDAO {
    @Override
    public Meeting insert(Meeting meeting) throws MeetingDAOException {



        String sql = "INSERT INTO MEETINGS(MEETING_ROOM, MEETING_DATE, TEACHER_ID, STUDENT_ID) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            String meetingRoom = meeting.getMeetingRoom();
            java.sql.Date sqlDate = new java.sql.Date(meeting.getMeetingDate().getTime());

            // Handle teacherId and studentId being null
            Integer teacherId = meeting.getTeacherId() != null ? meeting.getTeacherId() : 0;
            Integer studentId = meeting.getStudentId() != null ? meeting.getStudentId() : 0;

            ps.setString(1, meetingRoom);
            ps.setDate(2, sqlDate);
            ps.setInt(3, teacherId);
            ps.setInt(4, studentId);

            int n = ps.executeUpdate();
            if (n >= 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        meeting.setId(generatedKeys.getInt(1));
                    }
                }
                JOptionPane.showMessageDialog(null, n + " row(s) affected", "Successful Insert", JOptionPane.PLAIN_MESSAGE);
                return meeting;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meeting insert: " + meeting);
        }
    }



//    @Override
//    public Meeting update(Meeting meeting) throws MeetingDAOException {
//        String sql = "UPDATE MEETINGS SET ID = ?, TEACHER_ID = ?, STUDENT_ID = ?, MEETING_ROOM = ?, MEETING_DATE = ? WHERE TEACHER_ID = ? AND STUDENT_ID = ?";
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            ps.setInt(1, meeting.getId());
//            ps.setInt(2, meeting.getTeacherId());
//            ps.setInt(3, meeting.getStudentId());
//            ps.setString(4, meeting.getMeetingRoom());
//            ps.setDate(5, new java.sql.Date(meeting.getMeetingDate().getTime()));
//            //ps.setInt(5, meeting.getTeacherId()); // Assuming TEACHER_ID is the primary key along with STUDENT_ID
//            //.setInt(6, meeting.getStudentId());
//
//            int n = ps.executeUpdate();
//            if (n >= 1) {
//                return meeting;
//            } else {
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new MeetingDAOException("SQL Error in Meeting update: " + meeting);
//        }
//    }
@Override
public Meeting update(Meeting meeting) throws MeetingDAOException {
    String sql = "UPDATE MEETINGS SET MEETING_ROOM = ?, MEETING_DATE = ?, TEACHER_ID = ?, STUDENT_ID = ? WHERE ID = ?";

    try (Connection connection = DBUtil.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, meeting.getMeetingRoom());
        ps.setDate(2, new java.sql.Date(meeting.getMeetingDate().getTime()));
        ps.setInt(3, meeting.getTeacherId());
        ps.setInt(4, meeting.getStudentId());
        ps.setInt(5, meeting.getId());

        int n = ps.executeUpdate();
        if (n >= 1) {
            return meeting;
        } else {
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new MeetingDAOException("SQL Error in Meeting update: " + meeting);
    }
}


    @Override
    public void delete(int id) throws MeetingDAOException {
        String sql = "DELETE FROM MEETINGS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meeting delete with ID: " + id);
        }
    }


    @Override
    public List<Meeting> getByMeetingRoom(String meetingRoom) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE MEETING_ROOM LIKE ?";
        List<Meeting> meetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;

            ps.setString(1, meetingRoom + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Meeting meeting = new Meeting(
                        rs.getInt("ID"),
                        rs.getInt("TEACHER_ID"),
                        rs.getInt("STUDENT_ID"),
                        rs.getString("MEETING_ROOM"),
                        rs.getDate("MEETING_DATE")
                );
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meeting getByMeetingRoom with meetingRoom: " + meetingRoom);
        }

        return meetings;
    }


    @Override
    public Meeting getById(int id) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE ID = ?";
        Meeting meeting = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                meeting = new Meeting(
                        rs.getInt("ID"),
                        rs.getInt("TEACHER_ID"),
                        rs.getInt("STUDENT_ID"),
                        rs.getString("MEETING_ROOM"),
                        rs.getDate("MEETING_DATE")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meeting getById with ID: " + id);
        }

        return meeting;
    }

}
