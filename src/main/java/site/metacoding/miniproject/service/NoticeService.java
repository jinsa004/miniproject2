package site.metacoding.miniproject.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeAllRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeDetailRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeFindByCompanyIdRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeJobRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeMatchingRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeSaveRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeSubscribeRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeUpdateRespDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;
    private final HttpSession session;

    @Transactional
    public List<NoticeAllRespDto> findNoticeAllList() { // 개인회원이 채용공고 전체 목록보기
        // List<Notice> noticeList = noticeDao.findAll();
        // List<NoticeAllRespDto> noticeAllRespDtoList = new ArrayList<>();
        // for (Notice notice : noticeList) {
        // noticeAllRespDtoList.add(new NoticeAllRespDto(notice));
        // }
        // return noticeAllRespDtoList;
        return noticeDao.findAll().stream().map((notice) -> new NoticeAllRespDto(notice)).collect(Collectors.toList());
    }

    @Transactional
    public List<NoticeJobRespDto> findByJobCodeToNoticeList(Integer jobCode) { // 개인회원이 채용공고 분야별 목록보기 (필터기능)
        return noticeDao.findByJobCodeToNotice(jobCode).stream().map((notice) -> new NoticeJobRespDto(notice))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<NoticeMatchingRespDto> findMachingNoticeList(Integer employeeId) { // 개인회원이 매칭리스트 공고 보기
        return noticeDao.findMatchingByJobId(employeeId).stream().map((notice) -> new NoticeMatchingRespDto(notice))
                .collect(Collectors.toList());
    }

    @Transactional
    public NoticeSaveRespDto saveNotice(NoticeSaveReqDto noticeSaveReqDto) {
        Notice noticePS = noticeSaveReqDto.toEntity();
        noticeDao.insert(noticePS);
        noticeDao.findById(noticePS.getNoticeId());
        log.debug("디버그 : " + noticePS.getNoticeId());
        NoticeSaveRespDto noticeSaveRespDto = new NoticeSaveRespDto(noticePS);
        return noticeSaveRespDto;
    }

    @Transactional(readOnly = true)
    public List<NoticeFindByCompanyIdRespDto> findByCompanyIdToNotice(Integer companyId) {
        CompanySessionUser coPrincipal = (CompanySessionUser) session.getAttribute("companySessionUser");
        return noticeDao.findByCompanyId(coPrincipal.getCompanyId())
                .stream().map((notice) -> new NoticeFindByCompanyIdRespDto(notice)).collect(Collectors.toList());
    }

    @Transactional
    public NoticeUpdateRespDto updateNotice(Integer noticeId, NoticeUpdateReqDto NoticeUpdateReqDto) {
        Notice noticePS = noticeDao.findById(noticeId);
        if (noticePS == null) {
            throw new RuntimeException("해당 " + noticeId + "로 수정할 수 없습니다.");
        }
        CompanySessionUser coPrincipal = (CompanySessionUser) session.getAttribute("companySessionUser");
        if (coPrincipal.getCompanyId().equals(noticePS.getCompanyId())) {
            noticePS.update(NoticeUpdateReqDto);
            noticeDao.update(noticePS);
            noticeDao.findById(noticePS.getNoticeId());
            NoticeUpdateRespDto noticeUpdateRespDto = new NoticeUpdateRespDto(noticePS);
            return noticeUpdateRespDto;
        } else {
            throw new RuntimeException("해당 게시글을 수정할 권한이 없습니다.");
        }
    }

    @Transactional
    public void deleteNotice(Integer noticeId) {
        Notice noticePS = noticeDao.findById(noticeId); // 영속화
        if (noticePS == null) {
            throw new RuntimeException("해당 " + noticeId + "로 삭제를 할 수 없습니다.");
        }
        CompanySessionUser coPrincipal = (CompanySessionUser) session.getAttribute("companySessionUser");
        log.debug("디버그 company : " + coPrincipal.getCompanyId());
        if (coPrincipal.getCompanyId().equals(noticePS.getCompanyId())) {
            noticeDao.deleteById(noticePS.getNoticeId());
        } else {
            throw new RuntimeException("해당 게시글을 지울 권한이 없습니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<NoticeSubscribeRespDto> subsNoticeAll(Integer employeeId) {
        return noticeDao.findSubsByEmployeeId(employeeId).stream()
                .map((notice) -> new NoticeSubscribeRespDto(notice)).collect(Collectors.toList());
    }

    @Transactional
    public NoticeDetailRespDto getNoticeDetail(Integer noticeId) { // 메서드이름 수정
        Notice noticePS = noticeDao.findByNoticeIdToNoticeDetail(noticeId);
        NoticeDetailRespDto noticeDetailRespDto = new NoticeDetailRespDto(noticePS);
        return noticeDetailRespDto;
    }

}
