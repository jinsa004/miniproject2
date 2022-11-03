package site.metacoding.miniproject.dto.intro;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.intro.Intro;

public class IntroRespDto {

    @Setter
    @Getter
    public static class IntroSaveRespDto {
        private Integer introId;
        private Integer companyId;
        private String introConame;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private Integer jobId;
        private String jobName;

        public IntroSaveRespDto(Intro intro) {
            this.introId = intro.getIntroId();
            this.companyId = intro.getCompanyId();
            this.introConame = intro.getIntroConame();
            this.introBirth = intro.getIntroBirth();
            this.introTask = intro.getIntroTask();
            this.introSal = intro.getIntroSal();
            this.introWellfare = intro.getIntroWellfare();
            this.introContent = intro.getIntroContent();
            this.introLocation = intro.getIntroLocation();
            this.jobId = intro.getJobId();
            this.jobName = intro.getJobName();
        }
    }

    @Setter
    @Getter
    public static class IntroFindByCompanyIdRespDto {
        private Integer introId;
        private Integer companyId;
        private String companyName;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private Integer jobId;
        private String jobName;

        public IntroFindByCompanyIdRespDto(Intro intro) {
            this.introId = intro.getIntroId();
            this.companyId = intro.getCompanyId();
            this.companyName = intro.getCompanyName();
            this.introBirth = intro.getIntroBirth();
            this.introTask = intro.getIntroTask();
            this.introSal = intro.getIntroSal();
            this.introWellfare = intro.getIntroWellfare();
            this.introContent = intro.getIntroContent();
            this.introLocation = intro.getIntroLocation();
            this.jobId = intro.getJobId();
            this.jobName = intro.getJobName();
        }
    }

    @Setter
    @Getter
    public static class IntroFindByDetailRespDto {
        private Integer introId;
        private Integer companyId;
        private String companyName;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private String jobName;
        private boolean isSubed;
        private Integer subscribeId;
        private Integer principalId;

        public IntroFindByDetailRespDto(Intro intro) {
            this.introId = intro.getIntroId();
            this.companyId = intro.getCompanyId();
            this.companyName = intro.getCompanyName();
            this.introBirth = intro.getIntroBirth();
            this.introTask = intro.getIntroTask();
            this.introSal = intro.getIntroSal();
            this.introWellfare = intro.getIntroWellfare();
            this.introContent = intro.getIntroContent();
            this.introLocation = intro.getIntroLocation();
            this.jobName = intro.getJobName();
            this.subscribeId = intro.getSubscribeId();
            this.isSubed = intro.isSubed();
            this.principalId = intro.getPrincipalId();
        }
    }

    @Setter
    @Getter
    public static class IntroAllRespDto {
        private Integer introId;
        private String companyName;
        private String introTask;

        public IntroAllRespDto(Intro intro) {
            this.introId = intro.getIntroId();
            this.companyName = intro.getCompanyName();
            this.introTask = intro.getIntroTask();
        }
    }

    @Setter
    @Getter
    public static class IntroUpdateRespDto {
        private String companyName;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private Integer jobId;

        public IntroUpdateRespDto(Intro intro) {
            this.companyName = intro.getCompanyName();
            this.introBirth = intro.getIntroBirth();
            this.introTask = intro.getIntroTask();
            this.introSal = intro.getIntroSal();
            this.introWellfare = intro.getIntroWellfare();
            this.introContent = intro.getIntroContent();
            this.introLocation = intro.getIntroLocation();
            this.jobId = intro.getJobId();
        }

    }
}
