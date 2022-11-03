package site.metacoding.miniproject.domain.application;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Application {
    private Integer applicationId;
    private Integer resumeId;
    private Integer noticeId;
    private Timestamp createdAt;

    @Builder
    public Application(Integer applicationId, Integer resumeId, Integer noticeId, Timestamp createdAt) {
        this.applicationId = applicationId;
        this.resumeId = resumeId;
        this.noticeId = noticeId;
        this.createdAt = createdAt;
    }

}
