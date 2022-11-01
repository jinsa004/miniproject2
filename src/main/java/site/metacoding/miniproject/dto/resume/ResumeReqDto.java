package site.metacoding.miniproject.dto.resume;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.resume.Resume;

public class ResumeReqDto {

	/*
	 * @Getter
	 * 
	 * @Setter
	 * public static class ApplicationSaveReqDto {
	 * private Integer applicationId;
	 * private Integer resumeId;
	 * private Integer noticeId;
	 * 
	 * public Application toEntity(){
	 * return Resume.builder()
	 * .applicationId(applicationId)
	 * .resumeId(resumeId)
	 * .noticeId(noticeId)
	 * .build();
	 * }
	 * }
	 */

	@Setter
	@Getter
	public static class ResumeSaveReqDto {
		private String resumeTitle;
		private Integer employeeId;
		private String highschoolName;
		private String highschoolStartdate;
		private String highschoolEnddate;
		private String highschoolMajor;
		private String univName;
		private String univStartdate;
		private String univEnddate;
		private String univMajor;
		private String univGrades;
		private String prevCo;
		private String careerPeriod;
		private String careerPosition;
		private String careerDepartment;
		private String careerTask;
		private Integer jobId;

		public Resume toEntity() {
			return Resume.builder()
					.resumeTitle(resumeTitle)
					.employeeId(employeeId)
					.highschoolName(highschoolName)
					.highschoolStartdate(highschoolStartdate)
					.highschoolEnddate(highschoolEnddate)
					.highschoolMajor(highschoolMajor)
					.univName(univName)
					.univStartdate(univStartdate)
					.univEnddate(univEnddate)
					.univMajor(univMajor)
					.univGrades(univGrades)
					.prevCo(prevCo)
					.careerPeriod(careerPeriod)
					.careerPosition(careerPosition)
					.careerDepartment(careerDepartment)
					.careerTask(careerTask)
					.jobId(jobId)
					.build();
		}
	}

	@Setter
	@Getter
	public static class ResumeUpdateReqDto {
		private Integer resumeId;
		private String resumeTitle;
		private String highschoolName;
		private String highschoolStartdate;
		private String highschoolEnddate;
		private String highschoolMajor;
		private String univName;
		private String univStartdate;
		private String univEnddate;
		private String univMajor;
		private String univGrades;
		private String prevCo;
		private String careerPeriod;
		private String careerPosition;
		private String careerDepartment;
		private String careerTask;
		private Integer jobId;

		public Resume toEntity() {
			return Resume.builder()
					.resumeTitle(resumeTitle)
					.highschoolName(highschoolName)
					.highschoolStartdate(highschoolStartdate)
					.highschoolEnddate(highschoolEnddate)
					.highschoolMajor(highschoolMajor)
					.univName(univName)
					.univStartdate(univStartdate)
					.univEnddate(univEnddate)
					.univMajor(univMajor)
					.univGrades(univGrades)
					.prevCo(prevCo)
					.careerPeriod(careerPeriod)
					.careerPosition(careerPosition)
					.careerDepartment(careerDepartment)
					.careerTask(careerTask)
					.jobId(jobId)
					.build();
		}
	}

	@Setter
	@Getter
	public static class ResumeUpdateMainReqDto {
		private Integer resumeId;
		private Integer employeeId;
		private boolean isMain;

		public Resume toEntity() {
			return Resume.builder()
					.isMain(isMain)
					.build();
		}
	}

}
