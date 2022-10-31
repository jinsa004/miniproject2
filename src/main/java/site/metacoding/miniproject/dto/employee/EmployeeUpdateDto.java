package site.metacoding.miniproject.dto.employee;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeUpdateDto {
    private String employeeName;
    private String employeeBirth;
    private String employeeSex;
    private String employeeUsername;
    private String employeePassword;
    private String employeeEmail;
    private String employeeLocation;
    private String employeeTel;
    private List<Integer> jobIds;
}
