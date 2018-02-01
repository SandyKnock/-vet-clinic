package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Client;

import java.util.List;


/**
 * Created by Владислав on 01.02.2017.
 */
public interface ClientDao{

    Client findById(int id);

    void save(Client client);

    void deleteById(int id);

    List<Client> findAllClients();

}
