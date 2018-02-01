package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Pet;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface PetDao {
    Pet findById(int id);

    Pet findByNickName(String nickName);

    void save(Pet pet);

    void deleteByNickName(String sso);

    List<Pet> findAllPets();

    List<Pet> petByClientId(int client_id);

    List<String> getAllName();

}
