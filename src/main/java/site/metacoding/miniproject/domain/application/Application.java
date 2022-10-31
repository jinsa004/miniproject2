package site.metacoding.miniproject.domain.application;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Application {
    private Integer subscribeId;
    private Integer resumeId;
    private Integer noticeId;
    private Timestamp createdAt;
}
