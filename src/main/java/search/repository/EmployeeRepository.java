package search.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import search.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findByFirstName(String firstName);
}
