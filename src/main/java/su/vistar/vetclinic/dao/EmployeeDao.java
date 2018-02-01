package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Employee;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface EmployeeDao {
    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);

    List<Employee> findAllEmployees();

}
