package su.vistar.vetclinic.service;

import su.vistar.vetclinic.dao.EmployeeDao;
import su.vistar.vetclinic.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @Override
    public Employee findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        dao.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee entity = dao.findById(employee.getEmployeeId());
        if(entity != null) {
            entity.setEmployeeId(employee.getEmployeeId());
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

}
