package site.metacoding.miniproject.dto.subscribe;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;

public class SubscribeReqDto {

    @Setter
    @Getter
    public static class SubscribeSaveReqDto {
        private Integer subscribeId;
        private Integer employeeId;
        private Integer companyId;

        public Subscribe toEntity(EmpSessionUser empSessionUser) {
            return Subscribe.builder().subscribeId(subscribeId).employeeId(empSessionUser.getEmployeeId())
                    .companyId(companyId).build();
        }
    }
}
