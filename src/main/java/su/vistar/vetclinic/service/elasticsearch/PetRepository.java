package su.vistar.vetclinic.service.elasticsearch;


import su.vistar.vetclinic.entities.PetEL;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Владислав on 16.03.2017.
 */
@Repository
public interface PetRepository extends ElasticsearchRepository<PetEL, String> {
    PetEL save(PetEL article);

    List<PetEL> findByPetName(String name);

    List<PetEL> findById(String id);
}
