package site.metacoding.miniproject.dto.company;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

public class CompanyRespDto {

  @Setter
  @Getter
  public static class CompanyUpdateRespDto {
    private Integer companyId;
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private Integer jobId;
    private List<Integer> jobIds;

    public CompanyUpdateRespDto(Company company) {
      this.companyId = company.getCompanyId();
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobIds = company.getJobIds();
    }
  }

  @Setter
  @Getter
  public static class CompanyDetailRespDto {
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private Integer jobId;
    private List<Integer> jobIds;

    public CompanyDetailRespDto(Company company) {
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobIds = company.getJobIds();
    }

  }
}