package tech.emmyolusola.employeemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.emmyolusola.employeemanager.model.Employee;
import tech.emmyolusola.employeemanager.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{employeeId}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable("employeeId") Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeWithId(employeeId), HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employeePost = employeeService.addNewEmployee(employee);
        return new ResponseEntity<>(employeePost, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long employeeId,
                                                   @RequestBody Employee employee) {
        Employee employeeUpdate = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(employeeUpdate, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete_all")
    public ResponseEntity<?> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
