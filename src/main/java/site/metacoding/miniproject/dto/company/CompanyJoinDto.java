package site.metacoding.miniproject.dto.company;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyJoinDto {
  private Integer companyId;
  private Integer companyNumber;
  private String companyName;
  private String companyEmail;
  private String companyTel;
  private String companyLocation;
  private String companyUsername;
  private String companyPassword;
  private List<Integer> jobIds;

  public Company toEntity(CompanyJoinDto joinDto) {
    Company company = new Company(joinDto);
    return company;
  }

}
