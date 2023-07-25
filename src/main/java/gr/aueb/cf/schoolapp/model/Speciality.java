package gr.aueb.cf.schoolapp.model;

public class Speciality {
    private Integer id;
    private String speciality;

    public Speciality() {
    }

    public Speciality(Integer id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
