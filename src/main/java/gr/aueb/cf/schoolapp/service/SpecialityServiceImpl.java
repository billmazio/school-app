package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapp.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapp.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolapp.model.Speciality;
import gr.aueb.cf.schoolapp.service.exceptions.SpecialityNotFoundException;

import java.util.List;
import java.util.Optional;

public class SpecialityServiceImpl implements ISpecialityService {
    private ISpecialityDAO specialityDAO;

    public SpecialityServiceImpl(ISpecialityDAO specialityDAO) {
        this.specialityDAO = specialityDAO;
    }

    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO dto) throws SpecialityDAOException {
        if (dto == null) return null;
        Speciality speciality;
        try {
            speciality = map(dto);
            return specialityDAO.insert(speciality);
        } catch (SpecialityDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO dto) throws SpecialityDAOException, SpecialityNotFoundException {
        if (dto == null) return null;
        Speciality speciality;
        try {
            speciality = map(dto);

            if (specialityDAO.getById(speciality.getId()) == null) {
                throw new SpecialityNotFoundException(speciality);
            }
            return specialityDAO.update(speciality);
        } catch (SpecialityDAOException | SpecialityNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteSpeciality(int id) throws SpecialityDAOException, SpecialityNotFoundException {
        Speciality speciality;
        try {
            speciality = specialityDAO.getById(id);

            if (speciality == null) {
                throw new SpecialityNotFoundException("Speciality with id: " + id + " was not found");
            }
            specialityDAO.delete(id);
        } catch (SpecialityDAOException | SpecialityNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

//    @Override
//    public List<Speciality> getSpecialitiesByTitle(String speciality) throws SpecialityDAOException {
//        List<Speciality> specialities;
//        try {
//            specialities = specialityDAO.getBySpeciality(speciality);
//            return specialities;
//        } catch (SpecialityDAOException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Override
    public Optional<Speciality> getSpecialityByTitle(String specialityTitle) throws SpecialityDAOException {
        Optional<Speciality> speciality;
        try {
            speciality = specialityDAO.getBySpeciality(specialityTitle);
        } catch (SpecialityDAOException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("Error occurred while fetching speciality by title", e);
        }
        return speciality;
    }

    @Override
    public Speciality getSpecialityById(int id) throws SpecialityDAOException, SpecialityNotFoundException {
        Speciality speciality;
        try {
            speciality = specialityDAO.getById(id);
            if (speciality == null) {
                throw new SpecialityNotFoundException("Search Error: Speciality with id:" + id + " was not found");
            }
            return speciality;
        } catch (SpecialityDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        List<Speciality> specialities = null;
        try {
            specialities = specialityDAO.getAllSpecialities();
        } catch (SpecialityDAOException e) {
            e.printStackTrace();
            // handle exception
        }
        return specialities;
    }


    private Speciality map(SpecialityInsertDTO dto) {
        return new Speciality(null, dto.getSpeciality());
    }

    private Speciality map(SpecialityUpdateDTO dto) {
        return new Speciality(dto.getId(), dto.getSpeciality());
    }
}
