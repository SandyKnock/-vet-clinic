package su.vistar.vetclinic.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Владислав on 01.02.2017.
 */
@Entity
@Table(name="PET")
public class Pet implements Serializable {
    private int petId;
    private String history;
    private String fullHistory;
    private Integer numberOfComplaints;
    private Date dataLastComplaints;
    private String nickName;
    private String kindOfAnimal;
    private Client client;
    private Employee employee;

    @Id
    @Column(name = "pet_id", nullable = false)
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "history", nullable = true, length = 1000)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Basic
    @Column(name = "full_history", nullable = true, length = 10000)
    public String getFullHistory() {
        return fullHistory;
    }

    public void setFullHistory(String fullHistory) {
        this.fullHistory = fullHistory;
    }

    @Basic
    @Column(name = "number_of_complaints", nullable = true)
    public Integer getNumberOfComplaints() {
        return numberOfComplaints;
    }

    public void setNumberOfComplaints(Integer numberOfComplaints) {
        this.numberOfComplaints = numberOfComplaints;
    }

    @Basic
    @Column(name = "data_last_complaints", nullable = true)
    public Date getDataLastComplaints() {
        return dataLastComplaints;
    }

    public void setDataLastComplaints(Date dataLastComplaints) {
        this.dataLastComplaints = dataLastComplaints;
    }

    @Basic
    @Column(name = "nick_name", nullable = true, length = 45)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "kind_of_animal", nullable = true, length = 45)
    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    public void setKindOfAnimal(String kindOfAnimal) {
        this.kindOfAnimal = kindOfAnimal;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (petId != pet.petId) return false;
        if (history != null ? !history.equals(pet.history) : pet.history != null) return false;
        if (fullHistory != null ? !fullHistory.equals(pet.fullHistory) : pet.fullHistory != null) return false;
        if (numberOfComplaints != null ? !numberOfComplaints.equals(pet.numberOfComplaints) : pet.numberOfComplaints != null)
            return false;
        if (dataLastComplaints != null ? !dataLastComplaints.equals(pet.dataLastComplaints) : pet.dataLastComplaints != null)
            return false;
        if (nickName != null ? !nickName.equals(pet.nickName) : pet.nickName != null) return false;
        if (kindOfAnimal != null ? !kindOfAnimal.equals(pet.kindOfAnimal) : pet.kindOfAnimal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = petId;
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (fullHistory != null ? fullHistory.hashCode() : 0);
        result = 31 * result + (numberOfComplaints != null ? numberOfComplaints.hashCode() : 0);
        result = 31 * result + (dataLastComplaints != null ? dataLastComplaints.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (kindOfAnimal != null ? kindOfAnimal.hashCode() : 0);
        return result;
    }

}
