package site.metacoding.miniproject.dto.company;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

@Setter
@Getter
public class CompanySessionUser {
  private Integer companyId;
  private String companyUsername;

  public CompanySessionUser(Company company) {
    this.companyId = company.getCompanyId();
    this.companyUsername = company.getCompanyUsername();
  }

  public Company toEntity() {
    return Company.builder()
        .companyId(companyId).companyUsername(companyUsername).build();
  }

  @Builder
  public CompanySessionUser(Integer companyId, String companyUsername) {
    this.companyId = companyId;
    this.companyUsername = companyUsername;
  }
}
