package site.metacoding.miniproject.dto.employee;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.employee.Employee;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class EmployeeJoinDto {
  private String employeeId;
  private String employeeName;
  private String employeeBirth;
  private String employeeSex;
  private String employeeUsername;
  private String employeePassword;
  private String employeeEmail;
  private String employeeLocation;
  private String employeeTel;
  private List<Integer> jobIds;

  public Employee toEntity(EmployeeJoinDto employeeJoinDto) {
    Employee employee = new Employee(employeeJoinDto);
    return employee;
  }
}
