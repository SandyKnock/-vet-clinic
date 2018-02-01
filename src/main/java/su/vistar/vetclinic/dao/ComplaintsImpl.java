package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Complaints;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Repository("complaintsDao")
public class ComplaintsImpl extends AbstractDao<Integer, Complaints> implements ComplaintsDao {

    public Complaints findById(int id){
        Query query = getSession().createQuery("from Complaints where complaintsId =:id").setParameter("id",id);
        Complaints complaints =(Complaints) query.uniqueResult();
        return complaints;
    }


    @Override
    public void save(Complaints complaints) {
        persist(complaints);
    }

    @Override
    public void deleteById(int id) {
        Query query = getSession().createQuery("from Complaints where complaintsId =:id").setParameter("id",id);
        Complaints complaints = (Complaints)query.uniqueResult();
        delete(complaints);
    }

    @Override
    public List<Complaints> findAllComplaints() {
        Query query = getSession().createQuery("from Complaints ORDER BY complaintsId ");
        List<Complaints> complaints = (List<Complaints>)query.list();
        return complaints;
    }

}
