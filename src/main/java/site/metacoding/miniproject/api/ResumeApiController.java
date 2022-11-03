package site.metacoding.miniproject.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateRespDto;
import site.metacoding.miniproject.service.IntroService;
import site.metacoding.miniproject.service.JobService;
import site.metacoding.miniproject.service.ResumeService;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;
    private final JobService jobService;
    private final IntroService introService;
    private final HttpSession session;

    /* =============================개인회원========================================= */

    @GetMapping("/emp/resume/{resumeId}")
    public ResponseDto<?> findResumeById(@PathVariable Integer resumeId) {
        return new ResponseDto<>(1, "이력서 한건 불러오기 성공", resumeService.이력서상세보기(resumeId));
    }

    @PostMapping("/emp/resume/save")
    public ResponseDto<?> insertResume(@RequestBody ResumeSaveReqDto resumeSaveReqDto) {
        ResumeSaveRespDto resumeSaveRespDto = resumeService.이력서작성(resumeSaveReqDto);
        return new ResponseDto<>(1, "이력서 등록 성공", resumeSaveRespDto);
    }

    @PutMapping("/emp/resume/update/{resumeId}")
    public ResponseDto<?> updateResume(@PathVariable Integer resumeId,
            @RequestBody ResumeUpdateReqDto resumeUpdateReqDto) {
        resumeUpdateReqDto.setResumeId(resumeId);
        ResumeUpdateRespDto resumeUpdateRespDto = resumeService.이력서수정(resumeUpdateReqDto);
        return new ResponseDto<>(1, "이력서 수정 성공", resumeUpdateRespDto);
    }

    // @PostMapping("/empapi/es/emp/resume/applicate")
    // public @ResponseBody ResponseDto<?> applicateByResumeId(@RequestBody
    // Application application) {
    // resumeService.지원하기(application);
    // return new ResponseDto<>(1, "공고 지원 성공", null);
    // }

    // @PutMapping("/empapi/es/emp/resume/setMainResume/{resumeId}")
    // public @ResponseBody ResponseDto<?> setMainResume(@PathVariable Integer
    // resumeId) {
    // resumeService.메인이력서등록(resumeId);
    // return new ResponseDto<>(1, "메인 이력서 등록 성공", null);
    // }

    // @DeleteMapping("/empapi/es/emp/resumeDelete/{resumeId}")
    // public @ResponseBody ResponseDto<?> deleteResume(@PathVariable Integer
    // resumeId) {
    // resumeService.이력서삭제(resumeId);
    // return new ResponseDto<>(1, "이력서 삭제 성공", null);
    // }

    // @GetMapping("/es/emp/resumeSaveForm/{employeeId}")
    // public String insertResumeForm(@PathVariable Integer employeeId, Model model)
    // { // 이력서 등록 페이지
    // session.getAttribute("empprincipal");
    // List<Job> jobPS = jobService.관심직무보기();
    // model.addAttribute("jobPS", jobPS);
    // return "resume/resumeSave";
    // }

    // @PostMapping("/empapi/es/emp/resumeSave")
    // public @ResponseBody ResponseDto<?> insertImage(@RequestBody ResumeSaveReqDto
    // resumeSaveReqDto) throws Exception {
    // resumeService.이력서작성(resumeSaveReqDto);
    // return new ResponseDto<>(1, "이력서 등록 성공", null);
    // }

    // @GetMapping("/es/emp/resumeUpdate/{resumeId}")
    // public String updateResumeForm(@PathVariable Integer resumeId, Model model) {
    // // 이력서 수정 페이지
    // session.getAttribute("empprincipal");
    // List<Job> jobPS = jobService.관심직무보기();
    // model.addAttribute("jobPS", jobPS);
    // Resume resumePS = resumeService.이력서상세보기(resumeId);
    // model.addAttribute("resumePS", resumePS);
    // return "resume/resumeUpdate";
    // }

    // @PutMapping("/empapi/es/emp/resumeUpdate/{resumeId}")
    // public @ResponseBody ResponseDto<?> updateResume(@PathVariable Integer
    // resumeId,
    // @RequestBody ResumeUpdateReqDto resumeUpdateReqDto) {
    // resumeService.이력서수정(resumeUpdateReqDto);
    // return new ResponseDto<>(1, "이력서 수정 성공", null);
    // }

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

    // @GetMapping("/co/resumeDetail/{resumeId}")
    // public String getResumeDetail(@PathVariable Integer resumeId, Model model) {
    // Company companyPS = (Company) session.getAttribute("coprincipal");
    // model.addAttribute("company", companyPS);
    // model.addAttribute("resume", resumeService.이력서상세보기(resumeId));
    // return "company/resumeDetail";
    // }

    // 나중에 지원자 관리 메서드 생성 필요 (resumeList.jsp)

}