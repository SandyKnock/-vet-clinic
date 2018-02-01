package su.vistar.vetclinic.service;

import su.vistar.vetclinic.entities.Client;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface ClientService {
    Client findById(int id);

    void saveClient(Client client);

    void updateClient(Client client);

    void deleteClientById(int id);

    List<Client> findAllClients();

}
