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
    private String companyUsername;
    private List<CoCheckRespDto> jobCheckList;

    public CompanyJoinRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyId = company.getCompanyId();
      this.companyUsername = company.getCompanyUsername();
      this.jobCheckList = jobCheckList;
    }
  }

  @Setter
  @Getter
  public static class CompanyUpdateRespDto {
    private Integer companyId;
    private String companyUsername;
    private List<CoCheckRespDto> jobCheckList;

    public CompanyUpdateRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyId = company.getCompanyId();
      this.companyUsername = company.getCompanyUsername();
      this.jobCheckList = jobCheckList;
    }
  }

  @Setter
  @Getter
  public static class CompanyDetailRespDto {
    private String companyUsername;
    private List<CoCheckRespDto> jobCheckList;

    public CompanyDetailRespDto(Company company, List<CoCheckRespDto> jobCheckList) {
      this.companyUsername = company.getCompanyUsername();
      this.jobCheckList = jobCheckList;
    }
  }

}