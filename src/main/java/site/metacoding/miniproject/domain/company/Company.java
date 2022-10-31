package site.metacoding.miniproject.domain.company;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.company.CompanyJoinDto;
import site.metacoding.miniproject.dto.company.CompanyUpdateDto;

@NoArgsConstructor
@Setter
@Getter
public class Company implements Serializable {
	private Integer companyId;
	private Integer companyNumber;
	private String companyName;
	private String companyEmail;
	private String companyTel;
	private String companyLocation;
	private String companyUsername;
	private String companyPassword;
	private List<Integer> jobIds;

	// 엔티티 필드 아님
	private Integer employeeId;

	// public Company(Integer companyNumber, String companyName, String
	// companyEmail, String companyTel,
	// String companyLocation, String companyUsername, String companyPassword,
	// Integer jobId) {
	// this.companyNumber = companyNumber;
	// this.companyName = companyName;
	// this.companyEmail = companyEmail;
	// this.companyTel = companyTel;
	// this.companyLocation = companyLocation;
	// this.companyUsername = companyUsername;
	// this.companyPassword = companyPassword;
	// this.jobId = jobId;
	// }

	public Company(CompanyJoinDto companyJoinDto) {
		this.companyNumber = companyJoinDto.getCompanyNumber();
		this.companyName = companyJoinDto.getCompanyName();
		this.companyEmail = companyJoinDto.getCompanyEmail();
		this.companyTel = companyJoinDto.getCompanyTel();
		this.companyLocation = companyJoinDto.getCompanyLocation();
		this.companyUsername = companyJoinDto.getCompanyUsername();
		this.companyPassword = companyJoinDto.getCompanyPassword();
	}

	public void update(CompanyUpdateDto companyupdateDto) {
		this.companyNumber = companyupdateDto.getCompanyNumber();
		this.companyName = companyupdateDto.getCompanyName();
		this.companyEmail = companyupdateDto.getCompanyEmail();
		this.companyTel = companyupdateDto.getCompanyTel();
		this.companyLocation = companyupdateDto.getCompanyLocation();
		this.companyUsername = companyupdateDto.getCompanyUsername();
		this.companyPassword = companyupdateDto.getCompanyPassword();
	}
}
