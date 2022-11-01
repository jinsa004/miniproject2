package site.metacoding.miniproject.dto.employee;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class EmpReqDto {
    @Setter
    @Getter
    public class EmpLoginDto {
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
