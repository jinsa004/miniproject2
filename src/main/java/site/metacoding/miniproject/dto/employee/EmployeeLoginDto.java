package site.metacoding.miniproject.dto.employee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeLoginDto {
    private String employeeUsername;
    private String employeePassword;
    private boolean remember;
}
