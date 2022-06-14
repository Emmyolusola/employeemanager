package tech.emmyolusola.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.emmyolusola.employeemanager.exception.EmployeeNotAvailable;
import tech.emmyolusola.employeemanager.exception.EmployeeNotFoundException;
import tech.emmyolusola.employeemanager.exception.EmployeeWithEmailPresent;
import tech.emmyolusola.employeemanager.model.Employee;
import tech.emmyolusola.employeemanager.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addNewEmployee(Employee employee) {

        Optional<Employee> employeeEmail = employeeRepo.findEmployeeByEmail(employee.getEmail());
        if (employeeEmail.isPresent()){
            throw new EmployeeWithEmailPresent("There is an Employee with this email");
        }
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeWithId(Long employeeId) {
        boolean exists = employeeRepo.existsById(employeeId);
        if (!exists) {
            throw new EmployeeNotFoundException("Employee with id: " + employeeId + " is not found");
        }
        return employeeRepo.findById(employeeId);
    }

    public Employee updateEmployee(Long employeeId, Employee employee) {
        employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " is not found"));
        return employeeRepo.saveAndFlush(employee);
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepo.existsById(employeeId);
        if (!exists){
            throw new EmployeeNotAvailable("Employee with id: " + employeeId + " is not available");
        }
        employeeRepo.deleteById(employeeId);
    }

    public void deleteAllEmployees() {
        employeeRepo.deleteAll();
    }

}
