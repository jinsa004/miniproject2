package site.metacoding.miniproject.domain.subscribe;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Subscribe {
    private Integer subscribeId;
    private Integer employeeId;
    private Integer companyId;

    public Subscribe(Integer employeeId, Integer companyId) {
        this.employeeId = employeeId;
        this.companyId = companyId;
    }

    @Builder
    public Subscribe(Integer subscribeId, Integer employeeId, Integer companyId) {
        this.subscribeId = subscribeId;
        this.employeeId = employeeId;
        this.companyId = companyId;
    }

}
