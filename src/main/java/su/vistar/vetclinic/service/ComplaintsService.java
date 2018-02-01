package su.vistar.vetclinic.service;

import su.vistar.vetclinic.entities.Complaints;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface ComplaintsService {
    Complaints findById(int id);

    void saveComplaints(Complaints complaints);

    void updateComplaints(Complaints complaints);

    void deleteComplaintsById(int id);

    List<Complaints> findAllComplaints();

}
