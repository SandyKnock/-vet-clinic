package su.vistar.vetclinic.service;

import su.vistar.vetclinic.entities.Pet;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface PetService {
    Pet findById(int id);

    Pet findByNickName(String nickName);

    void savePet(Pet pet);

    void updatePet(Pet pet);

    void deletePetByNickName(String nickName);

    List<Pet> findAllPets();

    boolean isPetNickNameUnique(Integer id, String nickName);

    List<Pet> petByClientId(int client_id);

    List<String> getAllName();

}
