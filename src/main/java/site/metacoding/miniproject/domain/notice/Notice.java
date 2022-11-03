package site.metacoding.miniproject.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Notice {
	private Integer noticeId;
	private Integer companyId;
	private String noticeTitle;
	private String noticePeriod;
	private String noticeDept;
	private String noticePosition;
	private String noticeTask;
	private String noticeSal;
	private String noticeQual;
	private String noticeCareer;
	private String noticeWellfare;
	private Integer jobId;

	// 엔티티가 아닌 필드
	private Integer jobCode;
	private String jobName;
	private String introId;
	private String introConame;
	private String introBirth;
	private String introTask;
	private String introSal;
	private String introWellfare;
	private String introContent;
	private String introLocation;
	private Integer resumeId;

	@Builder
	public Notice(Integer noticeId, Integer companyId, String noticeTitle, String noticePeriod, String noticeDept,
			String noticePosition, String noticeTask, String noticeSal, String noticeQual, String noticeCareer,
			String noticeWellfare, Integer jobId) {
		this.noticeId = noticeId;
		this.companyId = companyId;
		this.noticeTitle = noticeTitle;
		this.noticePeriod = noticePeriod;
		this.noticeDept = noticeDept;
		this.noticePosition = noticePosition;
		this.noticeTask = noticeTask;
		this.noticeSal = noticeSal;
		this.noticeQual = noticeQual;
		this.noticeCareer = noticeCareer;
		this.noticeWellfare = noticeWellfare;
		this.jobId = jobId;
	}

	public void update(NoticeUpdateReqDto noticeUpdateReqDto) {
		this.companyId = noticeUpdateReqDto.getCompanyId();
		this.noticeTitle = noticeUpdateReqDto.getNoticeWellfare();
		this.noticePeriod = noticeUpdateReqDto.getNoticePeriod();
		this.noticeDept = noticeUpdateReqDto.getNoticeDept();
		this.noticePosition = noticeUpdateReqDto.getNoticePosition();
		this.noticeTask = noticeUpdateReqDto.getNoticeTask();
		this.noticeSal = noticeUpdateReqDto.getNoticeSal();
		this.noticeQual = noticeUpdateReqDto.getNoticeQual();
		this.noticeCareer = noticeUpdateReqDto.getNoticeCareer();
		this.noticeWellfare = noticeUpdateReqDto.getNoticeWellfare();
		this.jobId = noticeUpdateReqDto.getJobId();
	}
}
