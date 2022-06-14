package tech.emmyolusola.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.emmyolusola.employeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findEmployeeById(Long id);
}
