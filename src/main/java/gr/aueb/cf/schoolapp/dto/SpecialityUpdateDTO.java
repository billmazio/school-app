package gr.aueb.cf.schoolapp.dto;

public class SpecialityUpdateDTO extends BaseDTO {
    private String speciality;

    public SpecialityUpdateDTO() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
