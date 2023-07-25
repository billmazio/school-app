package gr.aueb.cf.schoolapp.dto;

public class TeacherInsertDTO {
    private String firstname;
    private String lastname;
    private Integer ssn;
    private Integer specialityId; // Speciality ID reference

    // Getters and setters for the fields


    public TeacherInsertDTO() {
    }



    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }



}
