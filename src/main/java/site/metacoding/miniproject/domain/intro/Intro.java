package site.metacoding.miniproject.domain.intro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.intro.UpdateDto;

@NoArgsConstructor
@Getter
@Setter
public class Intro {
	private Integer introId;
	private Integer companyId;
	private String introConame;
	private String introBirth;
	private String introTask;
	private String introSal;
	private String introWellfare;
	private String introContent;
	private String introLocation;
	private String introImageId;
	private Integer jobId;

	// 엔티티가 아닌 필드
	private String companyName;
	private String jobName;
	private String newImageName;

	public void Update(UpdateDto updateDto) {
		this.companyName = updateDto.getCompanyName();
		this.introConame = updateDto.getIntroConame();
		this.introBirth = updateDto.getIntroBirth();
		this.introTask = updateDto.getIntroTask();
		this.introSal = updateDto.getIntroSal();
		this.introWellfare = updateDto.getIntroWellfare();
		this.introContent = updateDto.getIntroContent();
		this.introLocation = updateDto.getIntroLocation();
		this.introImageId = updateDto.getIntroImageId();
		this.jobName = updateDto.getJobName();
		this.jobId = updateDto.getJobId();
	}
}