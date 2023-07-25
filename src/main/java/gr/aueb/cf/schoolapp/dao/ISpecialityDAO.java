package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Speciality;

import java.util.List;
import java.util.Optional;

public interface ISpecialityDAO {
  //  List<Speciality> getBySpeciality(String speciality) throws SpecialityDAOException;
    Speciality getById(int id) throws SpecialityDAOException;
    Speciality insert(Speciality speciality) throws SpecialityDAOException;
    Speciality update(Speciality speciality) throws SpecialityDAOException;
    void delete(int id) throws SpecialityDAOException;
    Optional<Speciality> getBySpeciality(String specialityTitle) throws SpecialityDAOException;
  List<Speciality> getAllSpecialities() throws SpecialityDAOException;
}
