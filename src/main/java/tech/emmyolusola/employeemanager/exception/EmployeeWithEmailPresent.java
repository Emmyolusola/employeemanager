package tech.emmyolusola.employeemanager.exception;

public class EmployeeWithEmailPresent extends RuntimeException {

    public EmployeeWithEmailPresent(String errorMessage) {
        super(errorMessage);
    }
}
