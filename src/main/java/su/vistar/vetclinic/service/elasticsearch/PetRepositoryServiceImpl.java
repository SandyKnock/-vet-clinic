package su.vistar.vetclinic.service.elasticsearch;

import su.vistar.vetclinic.entities.PetEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Владислав on 16.03.2017.
 */
@Service
public class PetRepositoryServiceImpl{

    @Autowired
    private PetRepository petRepository;

    public List<PetEL> getByName(String name){
        return petRepository.findByPetName(name);
    }

    public void addPetEL(PetEL petEL){
        petRepository.save(petEL);
    }

    public List<PetEL> getById(String id){
        return petRepository.findById(id);
    }

}
