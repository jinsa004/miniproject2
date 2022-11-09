package site.metacoding.miniproject.domain.notice;

import java.util.List;

import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeJobRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeMatchingRespDto;

public interface NoticeDao {
	public List<Notice> findAll();

	public List<NoticeJobRespDto> findByJobCodeToNotice(Integer jobCode);

	public Notice findById(Integer noticeId);

	public List<NoticeMatchingRespDto> findMatchingByJobId(Integer employeeId);

	public void insert(Notice notice);

	public void update(Notice notice);

	public void deleteById(Integer noticeId);

	public List<Notice> findByCompanyId(Integer companyId);

	public List<Notice> findSubsByEmployeeId(Integer employeeId);

	public Notice findByNoticeIdToNoticeDetail(Integer noticeId);
}
