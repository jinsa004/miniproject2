package site.metacoding.miniproject.dto.check.employee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpCheckRespDto {
    private Integer empCheckId;
    private Integer employeeId;
    private Integer jobId;
}
