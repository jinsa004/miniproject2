package site.metacoding.miniproject.domain.employee;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpUpdateReqDto;

@NoArgsConstructor
@Setter
@Getter
public class Employee implements Serializable {
	private Integer employeeId;
	private String employeeName;
	private String employeeBirth;
	private String employeeSex;
	private String employeeUsername;
	private String employeePassword;
	private String employeeEmail;
	private String employeeLocation;
	private String employeeTel;

	@Builder
	public Employee(Integer employeeId, String employeeName, String employeeBirth, String employeeSex,
			String employeeUsername, String employeePassword, String employeeEmail, String employeeLocation,
			String employeeTel) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeBirth = employeeBirth;
		this.employeeSex = employeeSex;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.employeeEmail = employeeEmail;
		this.employeeLocation = employeeLocation;
		this.employeeTel = employeeTel;
	}

	public void update(EmpUpdateReqDto empUpdateDto) {
		this.employeeName = empUpdateDto.getEmployeeName();
		this.employeeBirth = empUpdateDto.getEmployeeBirth();
		this.employeeSex = empUpdateDto.getEmployeeSex();
		this.employeeUsername = empUpdateDto.getEmployeeUsername();
		this.employeePassword = empUpdateDto.getEmployeePassword();
		this.employeeEmail = empUpdateDto.getEmployeeEmail();
		this.employeeLocation = empUpdateDto.getEmployeeLocation();
		this.employeeTel = empUpdateDto.getEmployeeTel();
	}

	// 엔티티 필드 아님
	private Integer companyId;
	private Integer jobId;
}
