package search.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.entity.Employee;
import search.repository.EmployeeRepository;
import search.services.EmployeeService;


import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee findOne(String employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.findOne(employeeId);
	}

	@Override
	public Employee findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public boolean exists(String employeeId) {
		return employeeRepository.exists(employeeId);
	}

	@Override
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Iterable<Employee> findAll(Iterable<String> employeeIds) {
		return employeeRepository.findAll();
	}

	@Override
	public long count() {
		return employeeRepository.count();
	}

	@Override
	public void delete(String employeeId) {
      employeeRepository.delete(employeeId);
	}

	@Override
	public void delete(Employee employee) {
      employeeRepository.delete(employee);
	}

	@Override
	public void deleteAll() {
		employeeRepository.deleteAll();
	}

}
