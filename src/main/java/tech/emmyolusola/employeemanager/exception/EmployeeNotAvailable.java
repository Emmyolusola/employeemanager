package tech.emmyolusola.employeemanager.exception;

public class EmployeeNotAvailable extends RuntimeException {

    public EmployeeNotAvailable(String errorMessage) {
        super(errorMessage);
    }
}
