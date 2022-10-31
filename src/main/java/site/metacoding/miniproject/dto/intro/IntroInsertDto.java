package site.metacoding.miniproject.dto.intro;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class IntroInsertDto {
    private Integer introImageId;
    private Integer companyId;
    private String introConame;
    private String introBirth;
    private String introTask;
    private String introSal;
    private String introWellfare;
    private String introContent;
    private String introLocation;
    private Integer jobId;
    private MultipartFile image;
}
