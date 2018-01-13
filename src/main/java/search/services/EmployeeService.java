package search.services;


import search.entity.Employee;

import java.util.List;


public interface EmployeeService {

	Employee findOne(String employeeId);
	Employee findByFirstName(String firstName);
	 Employee save(Employee employee);
	 boolean exists(String employeeId);
	 List<Employee> findAll();
	 Iterable<Employee> findAll(Iterable<String> employeeIds);
	 long count();
	 void delete(String employeeId);
	 void delete(Employee employee);
	 void deleteAll();

}
