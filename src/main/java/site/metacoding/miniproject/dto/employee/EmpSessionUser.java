package site.metacoding.miniproject.dto.employee;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.employee.Employee;

@Setter
@Getter
public class EmpSessionUser {
    private Integer employeeId;
    private String employeeUsername;

    public EmpSessionUser(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeUsername = employee.getEmployeeUsername();
    }

    public Employee toEntity() {
        return Employee.builder()
                .employeeId(employeeId).employeeUsername(employeeUsername).build();
    }
}
