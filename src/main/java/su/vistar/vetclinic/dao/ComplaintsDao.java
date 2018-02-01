package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Complaints;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface ComplaintsDao {
    Complaints findById(int id);

    void save(Complaints complaints);

    void deleteById(int id);

    List<Complaints> findAllComplaints();

}
