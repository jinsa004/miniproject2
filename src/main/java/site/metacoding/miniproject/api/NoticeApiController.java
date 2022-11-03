package site.metacoding.miniproject.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.job.Job;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;
import site.metacoding.miniproject.service.JobService;
import site.metacoding.miniproject.service.NoticeService;
import site.metacoding.miniproject.service.ResumeService;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;
    private final ResumeService resumeService;
    private final HttpSession session;
    private final JobService jobService;

    /* =============================개인회원========================================= */

    @GetMapping({ "/emp/main", "emp", "/" }) // ({ "emp/", "emp/notice" }) 로 두 개 걸어주는 것 불가 (쿼리스트링시 매핑 주소 "notice"가 중복되기
                                             // 때문)
    public ResponseDto<?> getAllNoticeList() {
        // List<Job> jobPS = jobService.관심직무보기();
        // model.addAttribute("jobPS", jobPS);
        return new ResponseDto<>(1, "성공", noticeService.findNoticeAllList());
    }

    @GetMapping("/emp/notice")
    public ResponseDto<?> getJobNoticeList(@RequestParam("jobCode") Integer jobCode) {
        return new ResponseDto<>(1, "성공", noticeService.findByJobCodeToNoticeList(jobCode));
    }

    @GetMapping("emp/matchingNotice/{employeeId}")
    public ResponseDto<?> matchingNoticeList(@PathVariable Integer employeeId) {
        return new ResponseDto<>(1, "성공", noticeService.findMachingNoticeList(employeeId));
    }

    // @GetMapping("/emp/noticeDetail/{noticeId}") // notice/Detail로 들어가는게 좋을 것 같습니다
    // public String recruitDetail(@PathVariable Integer noticeId, Model model) {//
    // 개인회원 입장에서 채용공고 상세보기
    // Employee principal = (Employee) session.getAttribute("empprincipal");
    // if (principal != null) {
    // List<Resume> resumePS = resumeService.내이력서가져오기(principal.getEmployeeId());
    // model.addAttribute("resumePS", resumePS);
    // }
    // Notice noticePS = noticeService.기업공고하나보기(noticeId);
    // model.addAttribute("noticePS", noticePS);
    // return "employee/noticeDetail";
    // }

    @GetMapping("/emp/subscribeNotice/{employeeId}")
    public ResponseDto<?> subsNoticeAll(@PathVariable Integer employeeId) { // 성진
        return new ResponseDto<>(1, "통신성공", noticeService.subsNoticeAll(employeeId));
    }

    /* =============================기업회원========================================= */

    @GetMapping("/co/noticeSave/{companyId}")
    public ResponseDto<?> 공고등록(@PathVariable Integer companyId, Model model) { // 등록폼을 가져오는 것
        session.getAttribute("coprincipal");
        List<Job> jobPS = jobService.관심직무보기();
        model.addAttribute("jobPS", jobPS);
        return new ResponseDto<>(1, "통신성공", null);
    }

    @PostMapping("/co/notice/save")
    public ResponseDto<?> saveNotice(@RequestBody NoticeSaveReqDto noticeSaveReqDto) {
        return new ResponseDto<>(1, "통신성공", noticeService.saveNotice(noticeSaveReqDto));
    }

    @GetMapping("/co/notice/{companyId}")
    public ResponseDto<?> findByCompanyIdToNotice(@PathVariable Integer companyId) { // 내 공고목록보기 기능
        return new ResponseDto<>(1, "통신성공", noticeService.findByCompanyIdToNotice(companyId));
    }

    @PutMapping("/co/notice/update/{noticeId}")
    public ResponseDto<?> updateNotice(@PathVariable Integer noticeId,
            @RequestBody NoticeUpdateReqDto NoticeUpdateReqDto) {
        return new ResponseDto<>(1, "공고 수정 성공", noticeService.updateNotice(noticeId, NoticeUpdateReqDto));
    }

    @DeleteMapping("/co/notice/delete/{noticeId}")
    public @ResponseBody ResponseDto<?> deleteNotice(@PathVariable Integer noticeId) {
        noticeService.deleteNotice(noticeId);
        return new ResponseDto<>(1, "공고 삭제 성공", null);
    }

    @GetMapping("/co/notice/{companyId}/detail/{noticeId}")
    public ResponseDto<?> noticeDetail(@PathVariable Integer companyId, @PathVariable Integer noticeId) {
        return new ResponseDto<>(1, "통신성공", noticeService.noticeDetail(noticeId));
    }
}
