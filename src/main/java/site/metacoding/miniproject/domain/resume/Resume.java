package site.metacoding.miniproject.domain.resume;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;

@NoArgsConstructor
@Setter
@Getter
public class Resume {
    private Integer resumeId;
    private String resumeTitle;
    private Integer employeeId;
    private String highschoolName;
    private String highschoolStartdate;
    private String highschoolEnddate;
    private String highschoolMajor;
    private String univName;
    private String univStartdate;
    private String univEnddate;
    private String univMajor;
    private String univGrades;
    private String prevCo;
    private String careerPeriod;

    // 신입지원이라면 신입지원이라고 입력 받게 해야 함
    private String careerPosition;
    private String careerDepartment;
    private String careerTask;
    private Integer jobId;
    private boolean isMain;
    private Timestamp createdAt;

    // 인재목록 볼 때 & 수정화면 불러올 시 employee 정보 불러오기용
    private String employeeName;
    private String employeeBirth;
    private String employeeSex;
    private String employeeEmail;
    private String employeeTel;
    private String employeeLocation;

    // 인재 목록 볼 때 & 내 이력서 목록 볼때 관심분야 표시용
    private String jobName;

    @Builder
    public Resume(Integer resumeId, String resumeTitle, Integer employeeId, String highschoolName,
            String highschoolStartdate, String highschoolEnddate, String highschoolMajor, String univName,
            String univStartdate, String univEnddate, String univMajor, String univGrades, String prevCo,
            String careerPeriod, String careerPosition, String careerDepartment, String careerTask, Integer jobId,
            boolean isMain, Timestamp createdAt, String employeeName, String employeeBirth, String employeeSex,
            String employeeEmail, String employeeTel, String employeeLocation) {
        this.resumeId = resumeId;
        this.resumeTitle = resumeTitle;
        this.employeeId = employeeId;
        this.highschoolName = highschoolName;
        this.highschoolStartdate = highschoolStartdate;
        this.highschoolEnddate = highschoolEnddate;
        this.highschoolMajor = highschoolMajor;
        this.univName = univName;
        this.univStartdate = univStartdate;
        this.univEnddate = univEnddate;
        this.univMajor = univMajor;
        this.univGrades = univGrades;
        this.prevCo = prevCo;
        this.careerPeriod = careerPeriod;
        this.careerPosition = careerPosition;
        this.careerDepartment = careerDepartment;
        this.careerTask = careerTask;
        this.jobId = jobId;
        this.isMain = isMain;
        this.createdAt = createdAt;
        this.employeeName = employeeName;
        this.employeeBirth = employeeBirth;
        this.employeeSex = employeeSex;
        this.employeeEmail = employeeEmail;
        this.employeeTel = employeeTel;
        this.employeeLocation = employeeLocation;
    }

    public void update(ResumeUpdateReqDto resumeUpdateReqDto) {
        this.resumeTitle = resumeUpdateReqDto.getResumeTitle();
        this.highschoolName = resumeUpdateReqDto.getHighschoolName();
        this.highschoolStartdate = resumeUpdateReqDto.getHighschoolStartdate();
        this.highschoolEnddate = resumeUpdateReqDto.getHighschoolEnddate();
        this.highschoolMajor = resumeUpdateReqDto.getHighschoolMajor();
        this.univName = resumeUpdateReqDto.getUnivName();
        this.univStartdate = resumeUpdateReqDto.getUnivStartdate();
        this.univEnddate = resumeUpdateReqDto.getUnivStartdate();
        this.univMajor = resumeUpdateReqDto.getUnivMajor();
        this.univGrades = resumeUpdateReqDto.getUnivGrades();
        this.prevCo = resumeUpdateReqDto.getPrevCo();
        this.careerPeriod = resumeUpdateReqDto.getCareerPeriod();
        this.careerPosition = resumeUpdateReqDto.getCareerPosition();
        this.careerDepartment = resumeUpdateReqDto.getCareerDepartment();
        this.careerTask = resumeUpdateReqDto.getCareerTask();
        this.jobId = resumeUpdateReqDto.getJobId();
    }
}