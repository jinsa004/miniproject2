package site.metacoding.miniproject.dto.resume;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.application.Application;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeDetailRespDto;

public class ResumeRespDto {

	@Getter
	@Setter
	public static class ApplicationSaveRespDto {
		private Integer applicationId;
		private Integer resumeId;
		private Integer noticeId;

		public ApplicationSaveRespDto(Application application) {
			this.applicationId = application.getApplicationId();
			this.resumeId = application.getResumeId();
			this.noticeId = application.getNoticeId();
		}
	}

	@Setter
	@Getter
	public static class ResumeAllRespDto {
		private String employeeName;
		private String resumeTitle;
		private String careerPeriod;
		private String jobName;
		private String resumeId;

		// public ResumeAllRespDto(Resume resume) {
		// this.resumeTitle = resume.getResumeTitle();
		// this.employeeId = resume.getEmployeeId();
		// this.careerPeriod = resume.getCareerPeriod();
		// this.jobId = resume.getJobId();
		// }
	}

	@Setter
	@Getter
	public static class MatchingResumeRespDto {
		private Integer resumeId;
		private String resumeTitle;
		private String employeeName;
		private Integer jobId;
		private String careerPeriod;
		private String jobName;
	}

	@Setter
	@Getter
	public static class NoticeHaveResumeRespDto {
		private NoticeDetailRespDto noticeDetailRespDto;
		private List<ResumeMyListRespDto> resumeMyListRespDto;

		public NoticeHaveResumeRespDto(NoticeDetailRespDto noticeDetailRespDto,
				List<ResumeMyListRespDto> resumeMyListRespDto) {
			this.noticeDetailRespDto = noticeDetailRespDto;
			this.resumeMyListRespDto = resumeMyListRespDto;
		}

	}

	@Setter
	@Getter
	public static class ResumeDetailRespDto {
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
		private String employeeName;
		private String employeeBirth;
		private String employeeSex;
		private String employeeEmail;
		private String employeeTel;
		private String employeeLocation;

		public ResumeDetailRespDto(Resume resume) {
			this.resumeTitle = resume.getResumeTitle();
			this.employeeId = resume.getEmployeeId();
			this.highschoolName = resume.getHighschoolName();
			this.highschoolStartdate = resume.getHighschoolStartdate();
			this.highschoolEnddate = resume.getHighschoolEnddate();
			this.highschoolMajor = resume.getHighschoolMajor();
			this.univName = resume.getUnivName();
			this.univStartdate = resume.getUnivStartdate();
			this.univEnddate = resume.getUnivEnddate();
			this.univMajor = resume.getUnivMajor();
			this.univGrades = resume.getUnivGrades();
			this.prevCo = resume.getPrevCo();
			this.careerPeriod = resume.getCareerPeriod();
			this.careerPosition = resume.getCareerPosition();
			this.careerDepartment = resume.getCareerDepartment();
			this.careerTask = resume.getCareerTask();
			this.jobId = resume.getJobId();
			this.employeeName = resume.getEmployeeName();
			this.employeeBirth = resume.getEmployeeBirth();
			this.employeeSex = resume.getEmployeeSex();
			this.employeeEmail = resume.getEmployeeEmail();
			this.employeeTel = resume.getEmployeeTel();
			this.employeeLocation = resume.getEmployeeLocation();
		}
	}

	/*
	 * @Setter
	 * 
	 * @Getter
	 * public class ResumeSaveFormRespDto {
	 * private Integer employeeId;
	 * private String employeeName;
	 * private String employeeBirth;
	 * private String employeeSex;
	 * private String employeeEmail;
	 * private String employeeTel;
	 * private String employeeLocation;
	 * }
	 */

	@Setter
	@Getter
	public static class ResumeSaveRespDto {
		private Integer resumeId;
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

		public ResumeSaveRespDto(Resume resume) {
			this.resumeId = resume.getResumeId();
			this.resumeTitle = resume.getResumeTitle();
			this.employeeId = resume.getEmployeeId();
			this.highschoolName = resume.getHighschoolName();
			this.highschoolStartdate = resume.getHighschoolStartdate();
			this.highschoolEnddate = resume.getHighschoolEnddate();
			this.highschoolMajor = resume.getHighschoolMajor();
			this.univName = resume.getUnivName();
			this.univStartdate = resume.getUnivStartdate();
			this.univEnddate = resume.getUnivEnddate();
			this.univMajor = resume.getUnivMajor();
			this.univGrades = resume.getUnivGrades();
			this.prevCo = resume.getPrevCo();
			this.careerPeriod = resume.getCareerPeriod();
			this.careerPosition = resume.getCareerPosition();
			this.careerDepartment = resume.getCareerDepartment();
			this.careerTask = resume.getCareerTask();
			this.jobId = resume.getJobId();
		}
	}

	@Setter
	@Getter
	public static class ResumeUpdateFormRespDto {
		private Integer resumeId;
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
		private boolean isMain;
		private String employeeName;
		private String employeeBirth;
		private String employeeSex;
		private String employeeEmail;
		private String employeeTel;
		private String employeeLocation;
	}

	@Setter
	@Getter
	public static class ResumeUpdateRespDto {
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

		public ResumeUpdateRespDto(Resume resume) {
			this.resumeTitle = resume.getResumeTitle();
			this.highschoolName = resume.getHighschoolName();
			this.highschoolStartdate = resume.getHighschoolStartdate();
			this.highschoolEnddate = resume.getHighschoolEnddate();
			this.highschoolMajor = resume.getHighschoolMajor();
			this.univName = resume.getUnivName();
			this.univStartdate = resume.getUnivStartdate();
			this.univEnddate = resume.getUnivEnddate();
			this.univMajor = resume.getUnivMajor();
			this.univGrades = resume.getUnivGrades();
			this.prevCo = resume.getPrevCo();
			this.careerPeriod = resume.getCareerPeriod();
			this.careerPosition = resume.getCareerPosition();
			this.careerDepartment = resume.getCareerDepartment();
			this.careerTask = resume.getCareerTask();
			this.jobId = resume.getJobId();
		}
	}

	@Getter
	@Setter
	public static class ResumeUpdateMainRespDto {
		private Integer resumeId;
		private Integer employeeId;
		private boolean isMain;

		public ResumeUpdateMainRespDto(Resume resume) {
			this.resumeId = resume.getResumeId();
			this.employeeId = resume.getEmployeeId();
			this.isMain = resume.isMain();
		}

	}

	@Setter
	@Getter
	public static class ResumeMyListRespDto {
		private Integer employeeId;
		private Integer resumeId;
		private String resumeTitle;
		private String careerPeriod;
		private boolean isMain;
		private Integer jobId;

		public ResumeMyListRespDto(Resume resume) {
			this.employeeId = resume.getEmployeeId();
			this.resumeId = resume.getResumeId();
			this.resumeTitle = resume.getResumeTitle();
			this.careerPeriod = resume.getCareerPeriod();
			this.isMain = resume.isMain();
			this.jobId = resume.getJobId();
		}

	}

}
