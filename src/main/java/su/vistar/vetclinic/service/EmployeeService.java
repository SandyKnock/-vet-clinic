package su.vistar.vetclinic.service;

import su.vistar.vetclinic.entities.Employee;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public interface EmployeeService {
    Employee findById(int id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(int id);

    List<Employee> findAllEmployees();

}
