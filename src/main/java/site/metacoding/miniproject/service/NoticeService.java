package site.metacoding.miniproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeAllRespDto;
import site.metacoding.miniproject.dto.notice.NoticeRespDto.NoticeSaveRespDto;

@Slf4j
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

    public List<NoticeAllRespDto> findNoticeAllList() {
        // List<Notice> noticeList = noticeDao.findAll();

        // List<NoticeAllRespDto> noticeAllRespDtoList = new ArrayList<>();
        // for (Notice notice : noticeList) {
        // noticeAllRespDtoList.add(new NoticeAllRespDto(notice));
        // }
        // return noticeAllRespDtoList;

        return noticeDao.findAll().stream().map((notice) -> new NoticeAllRespDto(notice)).collect(Collectors.toList());
    }

    public List<NoticeAllRespDto> findByJobCodeToNoticeList(Integer jobCode) {
        return noticeDao.findByJobCodeToNotice(jobCode).stream().map((notice) -> new NoticeAllRespDto(notice))
                .collect(Collectors.toList());
    }

    public List<NoticeAllRespDto> findMachingNoticeList(Integer employeeId) {
        return noticeDao.findMatchingByJobId(employeeId).stream().map((notice) -> new NoticeAllRespDto(notice))
                .collect(Collectors.toList());
    }

    public List<Notice> 내공고목록보기(Integer companyId) {
        return noticeDao.findByCompanyId(companyId);
    }

    // public void 이력서수정(Integer noticeId, NoticeUpdateDto noticeUpdateDto) {
    // Notice noticePS = noticeDao.findById(noticeId);
    // // noticePS.update(noticeUpdateDto);
    // noticeDao.update(noticePS);
    // }

    @Transactional
    public NoticeSaveRespDto saveNotice(NoticeSaveReqDto noticeSaveReqDto) {
        Notice noticePS = noticeSaveReqDto.toEntity();
        noticeDao.insert(noticePS);
        noticeDao.findById(noticePS.getNoticeId());
        log.debug("디버그 : " + noticePS.getNoticeId());
        NoticeSaveRespDto noticeSaveRespDto = new NoticeSaveRespDto(noticePS);
        return noticeSaveRespDto;
    }

}