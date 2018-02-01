package su.vistar.vetclinic.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Владислав on 01.02.2017.
 */
@Entity
@Table(name="Complaints")
public class Complaints implements Serializable {
    private int complaintsId;
    private Integer clientId;
    private Integer employeeId;
    private Pet petByPetId;

    @Id
    @Column(name = "complaints_id", nullable = false)
    public int getComplaintsId() {
        return complaintsId;
    }

    public void setComplaintsId(int complaintsId) {
        this.complaintsId = complaintsId;
    }

    @Basic
    @Column(name = "client_id", nullable = true)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "employee_id", nullable = true)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complaints that = (Complaints) o;

        if (complaintsId != that.complaintsId) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = complaintsId;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    public Pet getPetByPetId() {
        return petByPetId;
    }

    public void setPetByPetId(Pet petByPetId) {
        this.petByPetId = petByPetId;
    }

}
