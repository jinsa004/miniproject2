package site.metacoding.miniproject.dto.intro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetailDto {
    private Integer introId;
    private Integer companyId;
    private String introConame;
    private String introBirth;
    private String introTask;
    private String introSal;
    private String introWellfare;
    private String introContent;
    private String introLocation;
    private String introImage;
    private String companyName;
    private String jobName;
    private String jobCode;
    private Integer employeeId;
    private boolean isSubed;
    private Integer subscribeId;
    private String newImageName;
}
