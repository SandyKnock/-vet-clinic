package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Client;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao{

   public Client findById(int id){
       Query query = getSession().createQuery("from Client where clientId =:id").setParameter("id",id);
       return (Client) query.uniqueResult();
   }

    @Override
    public void save(Client client) {
        persist(client);
    }

    @Override
    public void deleteById(int id) {
        Query query = getSession().createQuery("from Client where clientId =:id").setParameter("id",id);
        Client client = (Client)query.uniqueResult();
        delete(client);
    }

    @Override
    public List<Client> findAllClients() {
        Query query = getSession().createQuery("from Client ORDER BY clientId ");
        return (List<Client>)query.list();
    }

}
