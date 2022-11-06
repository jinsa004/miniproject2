package site.metacoding.miniproject.api;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.job.Job;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.NoticeHaveResumeRespDto;
import site.metacoding.miniproject.service.JobService;
import site.metacoding.miniproject.service.NoticeService;
import site.metacoding.miniproject.service.ResumeService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;
    private final ResumeService resumeService;
    private final JobService jobService;
    private final HttpSession session;

    /*
     * =============================개인회원=========================================
     */

    @GetMapping({ "/emp/main", "emp", "/" })
    public ResponseDto<?> getAllNoticeList() {
        // List<Job> jobPS = jobService.관심직무보기();
        // model.addAttribute("jobPS", jobPS);
        return new ResponseDto<>(1, "성공", noticeService.findNoticeAllList());
    }

    @GetMapping("/emp/notice")
    public ResponseDto<?> getJobNoticeList(@RequestParam("jobCode") Integer jobCode) {
        return new ResponseDto<>(1, "성공",
                noticeService.findByJobCodeToNoticeList(jobCode));
    }

    @GetMapping("/es/emp/matchingNotice/{employeeId}")
    public ResponseDto<?> matchingNoticeList(@PathVariable Integer employeeId) {
        EmpSessionUser empPrincipal = (EmpSessionUser) session.getAttribute("empSessionUser");
        if (employeeId.equals(empPrincipal.getEmployeeId())) {
            return new ResponseDto<>(1, "성공",
                    noticeService.findMachingNoticeList(employeeId));
        }
        return new ResponseDto<>(-1, "사용자 id가 달라 매칭리스트를 볼 권한이 없습니다", null);
    }

    @GetMapping("/es/emp/noticeDetail/{noticeId}")
    public ResponseDto<?> getNoticeDetailWithResume(@PathVariable Integer noticeId) {// 개인회원 입장에서 채용공고보기
        EmpSessionUser empPrincipal = (EmpSessionUser) session.getAttribute("empSessionUser");
        if (empPrincipal != null) {
            return new ResponseDto<>(1, "성공", new NoticeHaveResumeRespDto(noticeService.getNoticeDetail(noticeId),
                    resumeService.getMyResumeList(empPrincipal.getEmployeeId())));
        } else {
            return new ResponseDto<>(1, "성공", noticeService.getNoticeDetail(noticeId));
        }
    }

    @GetMapping("/emp/subscribeNotice/{employeeId}")

    public ResponseDto<?> subsNoticeAll(@PathVariable Integer employeeId) {
        return new ResponseDto<>(1, "통신성공", noticeService.subsNoticeAll(employeeId));
    }

    /*
     * =============================기업회원=========================================
     */

    @GetMapping("/cs/co/noticeSave/{companyId}")
    public ResponseDto<?> 공고등록(@PathVariable Integer companyId) { // 등록폼을 가져오는 것
        session.getAttribute("companySessionUser");
        List<Job> jobPS = jobService.관심직무보기();
        return new ResponseDto<>(1, "통신성공", null);
    }

    @PostMapping("/cs/co/notice/save")
    public ResponseDto<?> saveNotice(@RequestBody NoticeSaveReqDto noticeSaveReqDto) {
        return new ResponseDto<>(1, "통신성공",
                noticeService.saveNotice(noticeSaveReqDto));
    }

    @GetMapping("/cs/co/notice/{companyId}")
    public ResponseDto<?> findByCompanyIdToNotice(@PathVariable Integer companyId) { // 내 공고목록보기 기능
        return new ResponseDto<>(1, "통신성공",
                noticeService.findByCompanyIdToNotice(companyId));
    }

    @PutMapping("/cs/co/notice/update/{noticeId}")
    public ResponseDto<?> updateNotice(@PathVariable Integer noticeId,
            @RequestBody NoticeUpdateReqDto NoticeUpdateReqDto) {
        return new ResponseDto<>(1, "공고 수정 성공", noticeService.updateNotice(noticeId,
                NoticeUpdateReqDto));
    }

    @DeleteMapping("/cs/co/notice/delete/{noticeId}")
    public @ResponseBody ResponseDto<?> deleteNotice(@PathVariable Integer noticeId) {
        noticeService.deleteNotice(noticeId);
        return new ResponseDto<>(1, "공고 삭제 성공", null);
    }

    @GetMapping("/cs/co/notice/{companyId}/detail/{noticeId}")
    public ResponseDto<?> noticeDetail(@PathVariable Integer companyId,
            @PathVariable Integer noticeId) {
        return new ResponseDto<>(1, "통신성공", noticeService.getNoticeDetail(noticeId));
    }
}
