package su.vistar.vetclinic.service;

import su.vistar.vetclinic.dao.*;
import su.vistar.vetclinic.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao dao;

    @Override
    public Client findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveClient(Client client) {
        dao.save(client);
    }

    @Override
    public void updateClient(Client client) {
        Client entity = dao.findById(client.getClientId());
        if(entity != null) {
            entity.setClientId(client.getClientId());
        }
    }

    @Override
    public void deleteClientById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return dao.findAllClients();
    }
}
