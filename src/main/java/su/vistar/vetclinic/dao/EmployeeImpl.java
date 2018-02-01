package su.vistar.vetclinic.dao;

import su.vistar.vetclinic.entities.Employee;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Владислав on 01.02.2017.
 */
@Repository("employeeDao")
public class EmployeeImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {
    @Override
    public Employee findById(int id) {
        Query query = getSession().createQuery("from Employee where employeeId =:id").setParameter("id",id);
        Employee employee =(Employee) query.uniqueResult();
        return employee;
    }

    @Override
    public void save(Employee employee) {
        persist(employee);
    }

    @Override
    public void deleteById(int id) {
        Query query = getSession().createQuery("from Employee where employeeId =:id").setParameter("id",id);
        Employee employee = (Employee)query.uniqueResult();
        delete(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        Query query = getSession().createQuery("from Employee ORDER BY employeeId ");
        List<Employee> employees = (List<Employee>)query.list();
        return employees;
    }

}
