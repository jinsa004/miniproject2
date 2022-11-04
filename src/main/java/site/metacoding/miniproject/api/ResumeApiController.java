package site.metacoding.miniproject.api;

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
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ApplicationSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateMainReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.service.IntroService;
import site.metacoding.miniproject.service.JobService;
import site.metacoding.miniproject.service.ResumeService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;
    private final JobService jobService;
    private final IntroService introService;
    private final HttpSession session;

    /* =============================개인회원========================================= */

    @PostMapping("/emp/resume/applicate")
    public @ResponseBody ResponseDto<?> applicateByResumeId(@RequestBody ApplicationSaveReqDto applicationSaveReqDto) {
        return new ResponseDto<>(1, "공고 지원 성공", resumeService.지원하기(applicationSaveReqDto));
    }

    @GetMapping("/emp/mypage/resume/{employeeId}")
    public @ResponseBody ResponseDto<?> mypageResumeList(@PathVariable Integer employeeId) {// 이력서 편집, 대표이력서선택
        return new ResponseDto<>(1, "내 이력서 불러오기 성공", resumeService.getMyResumeList(employeeId));
    }

    @PutMapping("/emp/resume/setMain/{resumeId}")
    public @ResponseBody ResponseDto<?> setMainResume(@PathVariable Integer resumeId) {
        ResumeUpdateMainReqDto resumeUpdateMainReqDto = new ResumeUpdateMainReqDto();

        // given (세션 구현되면 수정하면 됨)
        Integer employeeId = 1;

        resumeUpdateMainReqDto.setEmployeeId(employeeId);
        resumeUpdateMainReqDto.setResumeId(resumeId);
        return new ResponseDto<>(1, "메인 이력서 등록 성공", resumeService.메인이력서등록(resumeUpdateMainReqDto));
    }

    @DeleteMapping("/emp/resume/delete/{resumeId}")
    public @ResponseBody ResponseDto<?> deleteResume(@PathVariable Integer resumeId) {
        resumeService.이력서삭제(resumeId);
        return new ResponseDto<>(1, "이력서 삭제 성공", null);
    }

    @GetMapping("/emp/resume/{resumeId}")
    public ResponseDto<?> findResumeById(@PathVariable Integer resumeId) {
        return new ResponseDto<>(1, "이력서 한건 불러오기 성공", resumeService.이력서상세보기(resumeId));
    }

    @PostMapping("/emp/resume/save")
    public ResponseDto<?> insertResume(@RequestBody ResumeSaveReqDto resumeSaveReqDto) {
        return new ResponseDto<>(1, "이력서 등록 성공", resumeService.이력서작성(resumeSaveReqDto));
    }

    @PutMapping("/emp/resume/update/{resumeId}")
    public ResponseDto<?> updateResume(@PathVariable Integer resumeId,
            @RequestBody ResumeUpdateReqDto resumeUpdateReqDto) {
        resumeUpdateReqDto.setResumeId(resumeId);
        return new ResponseDto<>(1, "이력서 수정 성공", resumeService.이력서수정(resumeUpdateReqDto));
    }

    /* =============================기업회원========================================= */

    @GetMapping("/co")
    public ResponseDto<?> getAllResumeList() { // 기업회원이 보는 이력서리스트
        // List<Job> jobPS = jobService.관심직무보기();
        // model.addAttribute("jobPS", jobPS);
        // jobPS 일단 필요없어서 주석처리해놓음

        // Company principal = (Company) session.getAttribute("coprincipal");
        // if (principal != null) {
        // Intro introPS = introService.마이페이지설정(principal.getCompanyId());
        // model.addAttribute("introPS", introPS);
        // }
        return new ResponseDto<>(1, "성공", resumeService.findResumeAllList());
    }

    @GetMapping("/co/resume")
    public ResponseDto<?> getJobResumeList(@RequestParam("jobCode") Integer jobCode) {
        return new ResponseDto<>(1, "성공", resumeService.findByJobCodeToResumeList(jobCode));
    }

    @GetMapping("/co/matchingResume/{companyId}")
    public ResponseDto<?> getCompanyMatchingList(@PathVariable Integer companyId) {
        return new ResponseDto<>(1, "성공", resumeService.findMachingResumeList(companyId));
    }

    @GetMapping("/co/resume/detail/{resumeId}")
    public ResponseDto<?> getResumeDetail(@PathVariable Integer resumeId) {
        // Company companyPS = (Company) session.getAttribute("coprincipal"); 세션
        return new ResponseDto<>(1, "성공", resumeService.이력서상세보기(resumeId));
    }
}