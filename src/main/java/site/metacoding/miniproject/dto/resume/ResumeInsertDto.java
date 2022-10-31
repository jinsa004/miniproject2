package site.metacoding.miniproject.dto.resume;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ResumeInsertDto {
    private String resumeTitle;
    private Integer employeeId;
    private Integer resumeImageId;
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
    private String careerPosition;
    private String careerDepartment;
    private String careerTask;
    private Integer jobId;
    private MultipartFile image;
}
