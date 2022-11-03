package site.metacoding.miniproject.dto.notice;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.notice.Notice;

public class NoticeRespDto {

    @Setter
    @Getter
    public static class NoticeSaveRespDto {
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

        public NoticeSaveRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeTask = notice.getNoticeTask();
            this.noticeSal = notice.getNoticeSal();
            this.noticeQual = notice.getNoticeQual();
            this.noticeCareer = notice.getNoticeCareer();
            this.noticeWellfare = notice.getNoticeWellfare();
            this.jobId = notice.getJobId();
        }
    }

    @Setter
    @Getter
    public static class NoticeDetailRespDto {

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

        // notice가 아닌 필드
        private Integer jobCode;
        private String jobName;
        private String introId;
        private String introConame;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;

        public NoticeDetailRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeTask = notice.getNoticeTask();
            this.noticeSal = notice.getNoticeSal();
            this.noticeQual = notice.getNoticeQual();
            this.noticeCareer = notice.getNoticeCareer();
            this.noticeWellfare = notice.getNoticeWellfare();
            this.jobId = notice.getJobId();
            this.jobCode = notice.getJobCode();
            this.jobName = notice.getJobName();
            this.introId = notice.getIntroId();
            this.introConame = notice.getIntroConame();
            this.introBirth = notice.getIntroBirth();
            this.introTask = notice.getIntroTask();
            this.introSal = notice.getIntroSal();
            this.introWellfare = notice.getIntroWellfare();
            this.introContent = notice.getIntroContent();
            this.introLocation = notice.getIntroLocation();
        }
    }

    @Setter
    @Getter
    public static class NoticeAllRespDto {
        private Integer noticeId;
        private Integer companyId;
        private String noticeTitle;
        private String noticePeriod;
        private String noticeDept;
        private String noticePosition;
        private String noticeQual;
        private String noticeTask;

        public NoticeAllRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeQual = notice.getNoticeQual();
            this.noticeTask = notice.getNoticeTask();
        }
    }

    @Setter
    @Getter
    public static class NoticeJobRespDto {
        private Integer noticeId;
        private Integer companyId;
        private String noticeTitle;
        private String noticePeriod;
        private String noticeDept;
        private String noticePosition;
        private String noticeQual;
        private String noticeTask;

        public NoticeJobRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeQual = notice.getNoticeQual();
            this.noticeTask = notice.getNoticeTask();
        }
    }

    @Setter
    @Getter
    public static class NoticeMatchingRespDto {
        private Integer noticeId;
        private Integer companyId;
        private String noticeTitle;
        private String noticePeriod;
        private String noticeDept;
        private String noticePosition;
        private String noticeQual;
        private String noticeTask;

        public NoticeMatchingRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeQual = notice.getNoticeQual();
            this.noticeTask = notice.getNoticeTask();
        }
    }

    @Setter
    @Getter
    public static class NoticeFindByCompanyIdRespDto {
        private Integer noticeId;
        private Integer companyId;
        private String noticeTitle;
        private String noticePeriod;
        private String noticeDept;
        private String noticePosition;
        private String noticeQual;
        private String noticeTask;

        public NoticeFindByCompanyIdRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeQual = notice.getNoticeQual();
            this.noticeTask = notice.getNoticeTask();
        }
    }

    @Setter
    @Getter
    public static class NoticeUpdateRespDto {
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

        public NoticeUpdateRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeTask = notice.getNoticeTask();
            this.noticeSal = notice.getNoticeSal();
            this.noticeQual = notice.getNoticeQual();
            this.noticeCareer = notice.getNoticeCareer();
            this.noticeWellfare = notice.getNoticeWellfare();
            this.jobId = notice.getJobId();
        }
    }

    @Setter
    @Getter
    public static class NoticeSubscribeRespDto {
        private Integer noticeId;
        private Integer companyId;
        private String noticeTitle;
        private String noticePeriod;
        private String noticeDept;
        private String noticePosition;
        private String noticeQual;
        private String noticeTask;

        public NoticeSubscribeRespDto(Notice notice) {
            this.noticeId = notice.getNoticeId();
            this.companyId = notice.getCompanyId();
            this.noticeTitle = notice.getNoticeTitle();
            this.noticePeriod = notice.getNoticePeriod();
            this.noticeDept = notice.getNoticeDept();
            this.noticePosition = notice.getNoticePosition();
            this.noticeQual = notice.getNoticeQual();
            this.noticeTask = notice.getNoticeTask();
        }
    }
}
