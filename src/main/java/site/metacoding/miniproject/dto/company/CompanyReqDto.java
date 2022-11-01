package site.metacoding.miniproject.dto.company;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

@Setter
@Getter
public class CompanyReqDto {
  private Integer companyNumber;
  private String companyName;
  private String companyEmail;
  private String companyTel;
  private String companyLocation;
  private String companyUsername;
  private String companyPassword;
  private List<Integer> jobIds;

  @Setter
  @Getter
  public class CompanyJoinReqDto {
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private List<Integer> jobIds;

    public Company toEntity() {
      return Company.builder()
          .companyNumber(companyNumber).companyName(companyName).companyEmail(companyEmail).companyTel(companyTel)
          .companyLocation(companyLocation).companyUsername(companyUsername).companyPassword(companyPassword)
          .jobIds(jobIds).build();
    }
  }

  @Setter
  @Getter
  public class CompanyLoginReqDto {
    private String companyUsername;
    private String companyPassword;
    private boolean remember;

  }

  @Setter
  @Getter
  public class CompanyUpdateReqDto {
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private Integer jobId;
    private List<Integer> jobIds;

    public Company toEntity() {
      return Company.builder()
          .companyNumber(companyNumber).companyName(companyName).companyEmail(companyEmail).companyTel(companyTel)
          .companyLocation(companyLocation).companyUsername(companyUsername).companyPassword(companyPassword)
          .jobIds(jobIds).build();
    }
  }
}
