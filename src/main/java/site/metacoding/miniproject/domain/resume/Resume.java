package site.metacoding.miniproject.domain.resume;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.resume.UpdateDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Resume {
    private Integer resumeId;
    private String resumeTitle;
    private Integer employeeId;
    private String resumeImageId;
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

    public void update(UpdateDto updateDto) {
        this.resumeTitle = updateDto.getResumeTitle();
        this.resumeImageId = updateDto.getResumeImageId();
        this.highschoolName = updateDto.getHighschoolName();
        this.highschoolStartdate = updateDto.getHighschoolStartdate();
        this.highschoolEnddate = updateDto.getHighschoolEnddate();
        this.highschoolMajor = updateDto.getHighschoolMajor();
        this.univName = updateDto.getUnivName();
        this.univStartdate = updateDto.getUnivStartdate();
        this.univEnddate = updateDto.getUnivEnddate();
        this.univMajor = updateDto.getUnivMajor();
        this.univGrades = updateDto.getUnivGrades();
        this.prevCo = updateDto.getPrevCo();
        this.careerPeriod = updateDto.getCareerPeriod();
        this.careerPosition = updateDto.getCareerPosition();
        this.careerDepartment = updateDto.getCareerDepartment();
        this.careerTask = updateDto.getCareerTask();
        this.jobId = updateDto.getJobId();
    }
}