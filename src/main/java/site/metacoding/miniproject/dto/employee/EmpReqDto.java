package site.metacoding.miniproject.dto.employee;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.employee.Employee;

public class EmpReqDto {

    @Setter
    @Getter
    public static class EmpJoinReqDto { // 로그인 전 로직들 전부다 앞에 엔티티 안붙임. POST /user -> /join
        private Integer employeeId;
        private String employeeName;
        private String employeeBirth;
        private String employeeSex;
        private String employeeUsername;
        private String employeePassword;
        private String employeeEmail;
        private String employeeLocation;
        private String employeeTel;
        private List<Integer> jobIds;

        public Employee toEmpEntity() {
            return Employee.builder()
                    .employeeId(employeeId)
                    .employeeName(employeeName)
                    .employeeBirth(employeeBirth)
                    .employeeSex(employeeSex)
                    .employeeUsername(employeeUsername)
                    .employeePassword(employeePassword)
                    .employeeEmail(employeeEmail)
                    .employeeLocation(employeeLocation)
                    .employeeTel(employeeTel)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class EmpLoginReqDto {
        private String employeeUsername;
        private String employeePassword;
        private boolean remember;
    }

    @Setter
    @Getter
    public class EmpUpdateReqDto {
        private Integer employeeId;
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
}
