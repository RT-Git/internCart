package search.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import search.dto.EmployeeDTO;
import search.entity.Employee;
import search.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getOne/{employeeId}")
    public ResponseEntity<?> getOne(@PathVariable("employeeId") String employeeId){
        Employee employee = employeeService.findOne(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if(employee == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(employee, employeeDTO);
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Employee> employee= employeeService.findAll();
        ArrayList<EmployeeDTO> empDTOList= new ArrayList<>();
        for (Employee emp: employee) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(emp, employeeDTO);
            empDTOList.add(employeeDTO);
        }
        return new ResponseEntity<>(empDTOList, HttpStatus.OK);
    }
  @RequestMapping(method= RequestMethod.GET, value="/isEmployeeExists/{employeeId}")
  public ResponseEntity<?> isEmployeeExists(@PathVariable("employeeId") String employeeId){
	    return  new ResponseEntity<>(employeeService.exists(employeeId), HttpStatus.OK);
  }

    @RequestMapping(method= RequestMethod.GET, value="/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") String employeeId){
	     employeeService.delete(employeeId);
        return  new ResponseEntity<>("Succesfully Deleted", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    public ResponseEntity<?> count(){

        return new ResponseEntity<>(employeeService.count(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{firstName}")
    public ResponseEntity<?> getEmployee(@PathVariable("firstName") String firstName){
        Employee employee = employeeService.findByFirstName(firstName);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if(employee == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(employee, employeeDTO);
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }
}
