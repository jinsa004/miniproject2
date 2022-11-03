package site.metacoding.miniproject.dto.company;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.dto.check.company.CoCheckRespDto;

public class CompanyRespDto {

  @Setter
  @Getter
  public static class CompanyJoinRespDto {
    private Integer companyId;
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private List<CoCheckRespDto> jobCheckList;

    public CompanyJoinRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyId = company.getCompanyId();
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobCheckList = jobCheckList;
    }
  }

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
    private List<CoCheckRespDto> jobCheckList;

    public CompanyUpdateRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyId = company.getCompanyId();
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobCheckList = jobCheckList;
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
    private List<CoCheckRespDto> jobCheckList;

    public CompanyDetailRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobCheckList = jobCheckList;
    }
  }

  @Setter
  @Getter
  public static class CompanyDeleteRespDto {
    private Integer companyNumber;
    private String companyName;
    private String companyEmail;
    private String companyTel;
    private String companyLocation;
    private String companyUsername;
    private String companyPassword;
    private List<CoCheckRespDto> jobCheckList;

    public CompanyDeleteRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyNumber = company.getCompanyNumber();
      this.companyName = company.getCompanyName();
      this.companyEmail = company.getCompanyEmail();
      this.companyTel = company.getCompanyTel();
      this.companyLocation = company.getCompanyLocation();
      this.companyUsername = company.getCompanyUsername();
      this.companyPassword = company.getCompanyPassword();
      this.jobCheckList = jobCheckList;
    }

  }
}