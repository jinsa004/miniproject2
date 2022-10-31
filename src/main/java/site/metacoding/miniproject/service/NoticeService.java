package site.metacoding.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.notice.NoticeUpdateDto;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    public Notice 내공고상세보기(Integer noticeId) {// 기업회원이 수정할 때 사용
        return noticeDao.findById(noticeId);
    }

    public Notice 기업공고하나보기(Integer noticeId) {
        return noticeDao.findByNoticeIdToNoticeDetail(noticeId);
    }

    public List<Notice> 구독공고목록보기(Integer employeeId) {
        return noticeDao.findSubsByEmployeeId(employeeId);
    }

    public void 내공고삭제(Integer noticeId) {
        Notice noticePS = noticeDao.findById(noticeId); // 영속화
        noticeDao.deleteById(noticePS.getNoticeId());
    }

    public List<Notice> 채용공고전체목록보기() {
        return noticeDao.findAll();
    }

    public List<Notice> 채용공고분야별목록보기(Integer jobCode) {
        return noticeDao.findByJobCodeToNotice(jobCode);
    }

    public List<Notice> 구직자매칭리스트보기(Integer employeeId) {
        return noticeDao.findMatchingByJobId(employeeId);
    }

    public List<Notice> 내공고목록보기(Integer companyId) {
        return noticeDao.findByCompanyId(companyId);
    }

    public void 이력서수정(Integer noticeId, NoticeUpdateDto noticeUpdateDto) {
        Notice noticePS = noticeDao.findById(noticeId);
        noticePS.update(noticeUpdateDto);
        noticeDao.update(noticePS);
    }

    public void 공고등록(Notice notice) {
        noticeDao.insert(notice);
    }

}