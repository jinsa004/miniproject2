package site.metacoding.miniproject.dto.intro;

import lombok.Getter;
import lombok.Setter;

public class IntroRespDto {

    @Setter
    @Getter
    public static class IntroSaveRespDto {
        private Integer introId;
        private String introConame;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private Integer jobId;
        private String jobName;

        // public IntroSaveRespDto(Intro intro) {
        // this.introId = intro.getIntroId();
        // this.introConame = intro.getIntroConame();
        // this.introBirth = intro.getIntroBirth();
        // this.introTask = intro.getIntroTask();
        // this.introSal = intro.getIntroSal();
        // this.introWellfare = intro.getIntroWellfare();
        // this.introContent = intro.getIntroContent();
        // this.jobId = intro.getJobId();
        // this.jobName = intro.getJobName();
        // }
    }

    @Setter
    @Getter
    public static class IntroFindByIdRespDto {
        private Integer introId;
        private Integer companyId;
        private String companyName;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private String jobId;
        private String jobName;

        // public IntroDetailRespDto(Intro intro) {
        // this.introId = intro.getIntroId();
        // this.companyId = intro.getCompanyId();
        // this.introConame = intro.getIntroConame();
        // this.introBirth = intro.getIntroBirth();
        // this.introTask = intro.getIntroTask();
        // this.introSal = intro.getIntroSal();
        // this.introWellfare = intro.getIntroWellfare();
        // this.introContent = intro.getIntroContent();
        // this.introLocation = intro.getIntroLocation();
        // this.jobName = intro.getJobName();
        // this.jobCode = intro.getJobCode();
        // this.subscribeId = intro.getSubscribeId();
        // this.isSubed = intro.isSubed();
        // }
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

        // public IntroDetailRespDto(Intro intro) {
        // this.introId = intro.getIntroId();
        // this.companyId = intro.getCompanyId();
        // this.introConame = intro.getIntroConame();
        // this.introBirth = intro.getIntroBirth();
        // this.introTask = intro.getIntroTask();
        // this.introSal = intro.getIntroSal();
        // this.introWellfare = intro.getIntroWellfare();
        // this.introContent = intro.getIntroContent();
        // this.introLocation = intro.getIntroLocation();
        // this.jobName = intro.getJobName();
        // this.jobCode = intro.getJobCode();
        // this.subscribeId = intro.getSubscribeId();
        // this.isSubed = intro.isSubed();
        // }
    }

    @Setter
    @Getter
    public static class IntroAllRespDto {
        private Integer introId;
        private String introConame;
        private String introTask;

        // public IntroAllRespDto(Intro intro) {
        // this.introId = intro.getIntroId();
        // this.introConame = intro.getIntroConame();
        // this.introTask = intro.getIntroTask();
        // }
    }

    @Setter
    @Getter
    public static class IntroUpdateRespDto {
        private String introConame;
        private String introBirth;
        private String introTask;
        private String introSal;
        private String introWellfare;
        private String introContent;
        private String introLocation;
        private Integer jobId;
    }
}
