package site.metacoding.miniproject.dto.notice;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.notice.Notice;

public class NoticeReqDto {

    @Setter
    @Getter
    public static class NoticeSaveReqDto {
        private Integer noticeId;
        private Integer companyId;
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

        public Notice toEntity() {
            return Notice.builder()
                    .noticeId(noticeId)
                    .companyId(companyId)
                    .noticeTitle(noticeTitle)
                    .noticePeriod(noticePeriod)
                    .noticeDept(noticeDept)
                    .noticePosition(noticePosition)
                    .noticeTask(noticeTask)
                    .noticeSal(noticeSal)
                    .noticeQual(noticeQual)
                    .noticeCareer(noticeCareer)
                    .noticeWellfare(noticeWellfare)
                    .jobId(jobId)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class NoticeUpdateReqDto {
        private Integer noticeId;
        private Integer companyId;
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

        public Notice toEntity() {
            return Notice.builder()
                    .noticeId(noticeId)
                    .companyId(companyId)
                    .noticeTitle(noticeTitle)
                    .noticePeriod(noticePeriod)
                    .noticeDept(noticeDept)
                    .noticePosition(noticePosition)
                    .noticeTask(noticeTask)
                    .noticeSal(noticeSal)
                    .noticeQual(noticeQual)
                    .noticeCareer(noticeCareer)
                    .noticeWellfare(noticeWellfare)
                    .jobId(jobId)
                    .build();
        }
    }
}
