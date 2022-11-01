package site.metacoding.miniproject.domain.company;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;

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

	@Builder
	public Company(Integer companyId, Integer companyNumber, String companyName, String companyEmail, String companyTel,
			String companyLocation, String companyUsername, String companyPassword, List<Integer> jobIds) {
		this.companyId = companyId;
		this.companyNumber = companyNumber;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.companyTel = companyTel;
		this.companyLocation = companyLocation;
		this.companyUsername = companyUsername;
		this.companyPassword = companyPassword;
		this.jobIds = jobIds;
	}

	public void update(CompanyUpdateReqDto companyupdateReqDto) {
		this.companyNumber = companyupdateReqDto.getCompanyNumber();
		this.companyName = companyupdateReqDto.getCompanyName();
		this.companyEmail = companyupdateReqDto.getCompanyEmail();
		this.companyTel = companyupdateReqDto.getCompanyTel();
		this.companyLocation = companyupdateReqDto.getCompanyLocation();
		this.companyUsername = companyupdateReqDto.getCompanyUsername();
		this.companyPassword = companyupdateReqDto.getCompanyPassword();
	}
}
