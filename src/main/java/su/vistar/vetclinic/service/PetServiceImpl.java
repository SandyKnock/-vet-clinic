package su.vistar.vetclinic.service;

import su.vistar.vetclinic.dao.PetDao;
import su.vistar.vetclinic.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Service("petService")
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao dao;

    public Pet findById(int id) {
        return dao.findById(id);
    }

    public Pet findByNickName(String nickName) {
        Pet pet = dao.findByNickName(nickName);
        return pet;
    }

    public void savePet(Pet pet) {
        dao.save(pet);
    }

    public void updatePet(Pet pet) {
        Pet entity = dao.findById(pet.getPetId());
        if(entity != null) {
            entity.setNickName(pet.getNickName());
            entity.setFullHistory(pet.getFullHistory());
            entity.setHistory(pet.getHistory());
            entity.setKindOfAnimal(pet.getKindOfAnimal());
            entity.setNumberOfComplaints(pet.getNumberOfComplaints());
            entity.setDataLastComplaints(pet.getDataLastComplaints());
        }
    }

    public void deletePetByNickName(String nickName) {
        dao.deleteByNickName(nickName);
    }

    public List<Pet> findAllPets() {
        return dao.findAllPets();
    }

    public boolean isPetNickNameUnique(Integer id, String sso) {
        Pet pet = findByNickName(sso);
        return ( pet == null || ((id != null) && (pet.getPetId() == id)));
    }

    @Override
    public List<Pet> petByClientId(int client_id) {
        return dao.petByClientId(client_id);
    }

    @Override
    public List<String> getAllName() {
        return dao.getAllName();
    }

}
