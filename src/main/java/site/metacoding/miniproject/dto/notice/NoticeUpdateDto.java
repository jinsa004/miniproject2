package site.metacoding.miniproject.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeUpdateDto {
    private String noticeTitle;
    private String noticePeriod;
    private String noticeDept;
    private String noticePosition;
    private String noticeTask;
    private String noticeSal;
    private String noticeQual;
    private String noticeCareer;
    private String noticeWellfare;
    private Integer jobId;
}
