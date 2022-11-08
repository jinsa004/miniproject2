package site.metacoding.miniproject.dto.subscribe;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;

public class SubscribeRespDto {

    @Setter
    @Getter
    public static class SubscribeSaveRespDto {
        private Integer subscribeId;
        private Integer employeeId;
        private Integer companyId;

        public SubscribeSaveRespDto(Subscribe subscribe, EmpSessionUser empSessionUser) {
            this.subscribeId = subscribe.getSubscribeId();
            this.employeeId = empSessionUser.getEmployeeId();
            this.companyId = subscribe.getCompanyId();
        }
    }
}
