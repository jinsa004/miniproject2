package site.metacoding.miniproject.dto.subscribe;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.subscribe.Subscribe;

public class SubscribeReqDto {

    @Setter
    @Getter
    public static class SubscribeSaveReqDto {
        private Integer subscribeId;
        private Integer employeeId;
        private Integer companyId;

        public Subscribe toEntity() {
            return Subscribe.builder().subscribeId(subscribeId).employeeId(employeeId).companyId(companyId).build();
        }
    }
}
