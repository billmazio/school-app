package gr.aueb.cf.schoolapp.model;

public class Teacher {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer ssn;
    private Integer specialityId;

    public Teacher() {}

    public Teacher(Integer id, String firstname, String lastname, Integer ssn,  Integer specialityId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.specialityId = specialityId;
    }


    public Teacher(int id, String firstname) {
    }


    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "lastname='" + lastname + '\'' +
                '}';
    }
}
