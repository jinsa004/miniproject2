package site.metacoding.miniproject.dto.employee;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.dto.check.employee.EmpCheckRespDto;

public class EmpRespDto {

    @Setter
    @Getter
    public static class EmpJoinRespDto {
        private Integer employeeId;
        private String employeeUsername;
        private List<EmpCheckRespDto> jobCheckList;

        public EmpJoinRespDto(Employee employee, List<EmpCheckRespDto> jobCheckList) {
            this.employeeId = employee.getEmployeeId();
            this.employeeUsername = employee.getEmployeeUsername();
            this.jobCheckList = jobCheckList;
        }
    }

    @Setter
    @Getter
    public static class EmpUpdateRespDto {
        private Integer employeeId;
        private String employeeName;
        private String employeeBirth;
        private String employeeSex;
        private String employeeUsername;
        private String employeePassword;
        private String employeeEmail;
        private String employeeLocation;
        private String employeeTel;
        private List<EmpCheckRespDto> jobCheckList;

        public EmpUpdateRespDto(Employee employee, List<EmpCheckRespDto> jobCheckList) {
            this.employeeId = employee.getEmployeeId();
            this.employeeName = employee.getEmployeeName();
            this.employeeBirth = employee.getEmployeeBirth();
            this.employeeSex = employee.getEmployeeSex();
            this.employeeUsername = employee.getEmployeeUsername();
            this.employeePassword = employee.getEmployeePassword();
            this.employeeEmail = employee.getEmployeeEmail();
            this.employeeLocation = employee.getEmployeeLocation();
            this.employeeTel = employee.getEmployeeTel();
            this.jobCheckList = jobCheckList;
        }
    }

}
