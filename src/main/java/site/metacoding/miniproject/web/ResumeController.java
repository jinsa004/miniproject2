// package site.metacoding.miniproject.web;

// import java.util.List;

// import javax.servlet.http.HttpSession;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import lombok.RequiredArgsConstructor;
// import site.metacoding.miniproject.domain.application.Application;
// import site.metacoding.miniproject.domain.company.Company;
// import site.metacoding.miniproject.domain.intro.Intro;
// import site.metacoding.miniproject.domain.job.Job;
// import site.metacoding.miniproject.domain.resume.Resume;
// import site.metacoding.miniproject.dto.ResponseDto;
// import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
// import
// site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
// import site.metacoding.miniproject.service.IntroService;
// import site.metacoding.miniproject.service.JobService;
// import site.metacoding.miniproject.service.ResumeService;

// @RequiredArgsConstructor
// @Controller
// public class ResumeController {

// private final ResumeService resumeService;
// private final JobService jobService;
// private final IntroService introService;
// private final HttpSession session;

// /* =============================개인회원=========================================
// */

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

// /* =============================기업회원=========================================
// */

// @GetMapping("/co")
// public String getAllResumeList(Model model) { // 기업회원이 보는 이력서리스트
// List<Job> jobPS = jobService.관심직무보기();
// model.addAttribute("jobPS", jobPS);
// List<Resume> resumeAllList = resumeService.이력서목록보기();
// model.addAttribute("resumeAllList", resumeAllList);

// Company principal = (Company) session.getAttribute("coprincipal");
// if (principal != null) {
// Intro introPS = introService.마이페이지설정(principal.getCompanyId());
// model.addAttribute("introPS", introPS);
// }
// return "company/mainCompany";
// }

// @GetMapping("/co/resume")
// public String getJobResumeList(@RequestParam("jobCode") Integer jobCode,
// Model model) {
// List<Resume> jobResumeList = resumeService.이력서분야별목록보기(jobCode);
// model.addAttribute("jobResumeList", jobResumeList);
// return "company/jobResume";
// }

// @GetMapping("/cs/co/matchingResume/{companyId}")
// public String companyMatchingList(@PathVariable Integer companyId, Model
// model) {
// List<Resume> matchingResume = resumeService.기업매칭리스트보기(companyId);
// model.addAttribute("matchingResume", matchingResume);
// return "company/matchingResume";
// }

// @GetMapping("/co/resumeDetail/{resumeId}")
// public String getResumeDetail(@PathVariable Integer resumeId, Model model) {
// Company companyPS = (Company) session.getAttribute("coprincipal");
// model.addAttribute("company", companyPS);
// model.addAttribute("resume", resumeService.이력서상세보기(resumeId));
// return "company/resumeDetail";
// }

// // 나중에 지원자 관리 메서드 생성 필요 (resumeList.jsp)
// }
