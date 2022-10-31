package site.metacoding.miniproject.dto.company;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CompanyUpdateDto {
  private Integer companyNumber;
  private String companyName;
  private String companyEmail;
  private String companyTel;
  private String companyLocation;
  private String companyUsername;
  private String companyPassword;
  private Integer jobId;
  private List<Integer> jobIds;
}
