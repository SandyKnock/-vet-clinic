package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Pet;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Repository("petDao")
public class PetImpl extends AbstractDao<Integer, Pet> implements PetDao {
    @Override
    public Pet findById(int id) {
        Query query = getSession().createQuery("from Pet where petId =:id").setParameter("id",id);
        Pet pet =(Pet) query.uniqueResult();
        return pet;
    }

    @Override
    public Pet findByNickName(String nickName) {
        Query query = getSession().createQuery("from Pet where nickName =:nickName").setParameter("nickName",nickName);
        Pet pet =(Pet) query.uniqueResult();
        return pet;
    }

    @Override
    public void save(Pet pet) {
        persist(pet);
    }

    @Override
    public void deleteByNickName(String nickName) {
        Query query = getSession().createQuery("from Pet where nickName =:nickName").setParameter("nickName",nickName);
        Pet pet = (Pet)query.uniqueResult();
        delete(pet);
    }

    @Override
    public List<Pet> findAllPets() {
        Query query = getSession().createQuery("from Pet ORDER BY nickName ");
        List<Pet> pet = (List<Pet>)query.list();
        return pet;
    }

    @Override
    public List<Pet> petByClientId(int client_id) {
        Query query = getSession().createQuery("from Pet where client.clientId =:client_id ").setParameter("client_id",client_id);
        List<Pet> pet = (List<Pet>)query.list();
        return pet;
    }

    @Override
    public List<String> getAllName() {
        Query query = getSession().createQuery("from Pet");
        List<Pet> pet = (List<Pet>)query.list();
        List<String> petName = new ArrayList<>();
        for (int i = 0; i < pet.size() ; i++) {
            petName.add(i, pet.get(i).getNickName());
        }
        return petName;
    }

}
