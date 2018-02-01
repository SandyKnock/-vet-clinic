package su.vistar.vetclinic.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Created by Владислав on 16.03.2017.
 */
@Document(indexName = "my_pet", type = "petel")
public class PetEL {

    @Id
    private String id;

    private String petName;

    @Field(type = FieldType.Nested)
    private List<Pet> pets;

    @Field(type = FieldType.Nested)
    private List<String> AllNickName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public List<su.vistar.vetclinic.entities.Pet> getPets() {
        return pets;
    }

    public void setPets(List<su.vistar.vetclinic.entities.Pet> pets) {
        this.pets = pets;
    }

    public List<String> getAllNickName() {
        return AllNickName;
    }

    public void setAllNickName(List<String> allNickName) {
        AllNickName = allNickName;
    }

}
