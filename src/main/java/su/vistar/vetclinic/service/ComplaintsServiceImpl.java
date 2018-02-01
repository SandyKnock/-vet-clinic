package su.vistar.vetclinic.service;

import su.vistar.vetclinic.dao.ComplaintsDao;
import su.vistar.vetclinic.entities.Complaints;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public class ComplaintsServiceImpl implements ComplaintsService {

    @Autowired
    ComplaintsDao dao;

    @Override
    public Complaints findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveComplaints(Complaints complaints) {
        dao.save(complaints);
    }

    @Override
    public void updateComplaints(Complaints complaints) {
        Complaints entity = dao.findById(complaints.getEmployeeId());
        if(entity != null) {
            entity.setEmployeeId(complaints.getEmployeeId());
            entity.setComplaintsId(complaints.getComplaintsId());
            entity.setPetByPetId(complaints.getPetByPetId());
            entity.setClientId(complaints.getClientId());
        }
    }

    @Override
    public void deleteComplaintsById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Complaints> findAllComplaints() {
        return dao.findAllComplaints();
    }

}
