package site.metacoding.miniproject.dto.intro;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.intro.Intro;

public class IntroReqDto {

    @Setter
    @Getter
    public static class IntroSaveReqDto {
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

        public Intro toEntity() {
            return Intro.builder().introId(introId).introConame(introConame).introBirth(introBirth)
                    .introTask(introTask).introSal(introSal)
                    .introWellfare(introWellfare).introContent(introContent).introLocation(introLocation).jobId(jobId)
                    .jobName(jobName)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class IntroUpdateReqDto {
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

        public Intro toEntity() {
            return Intro.builder().introId(introId).introConame(introConame).introBirth(introBirth)
                    .introTask(introTask).introSal(introSal)
                    .introWellfare(introWellfare).introContent(introContent).introLocation(introLocation).jobId(jobId)
                    .build();
        }
    }
}
