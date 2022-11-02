// package site.metacoding.miniproject.web;

// import java.util.List;

// import javax.servlet.http.HttpSession;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import lombok.RequiredArgsConstructor;
// import site.metacoding.miniproject.domain.employee.Employee;
// import site.metacoding.miniproject.domain.job.Job;
// import site.metacoding.miniproject.domain.notice.Notice;
// import site.metacoding.miniproject.domain.resume.Resume;
// import site.metacoding.miniproject.dto.ResponseDto;
// import site.metacoding.miniproject.service.JobService;
// import site.metacoding.miniproject.service.NoticeService;
// import site.metacoding.miniproject.service.ResumeService;

// @RequiredArgsConstructor
// @Controller
// public class NoticeController {

// private final NoticeService noticeService;
// private final ResumeService resumeService;
// private final HttpSession session;
// private final JobService jobService;

// /* =============================개인회원=========================================
// */

// @GetMapping({ "/emp/main", "emp", "/" }) // ({ "emp/", "emp/notice" }) 로 두 개
// 걸어주는 것 불가 (쿼리스트링시 매핑 주소 "notice"가 중복되기
// // 때문)
// public String getAllNoticeList(Model model) {
// List<Job> jobPS = jobService.관심직무보기();
// model.addAttribute("jobPS", jobPS);
// List<Notice> noticeAllList = noticeService.채용공고전체목록보기();
// model.addAttribute("noticeAllList", noticeAllList);
// return "employee/main";
// }

// @GetMapping("/emp/notice")
// public String getJobNoticeList(@RequestParam("jobCode") Integer jobCode,
// Model model) {
// List<Notice> jobNoticeList = noticeService.채용공고분야별목록보기(jobCode);
// model.addAttribute("jobNoticeList", jobNoticeList);
// return "employee/jobNotice";
// }

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

// @GetMapping("/es/emp/matchingNotice/{employeeId}")
// public String matchingList(@PathVariable Integer employeeId, Model model) {
// List<Notice> matchingNotice = noticeService.구직자매칭리스트보기(employeeId);
// model.addAttribute("matchingNotice", matchingNotice);
// return "employee/matchingNotice";
// }

// @GetMapping("/es/emp/subscribeNotice/{employeeId}")
// public String subs(@PathVariable Integer employeeId, Model model) {
// List<Notice> noticeList = noticeService.구독공고목록보기(employeeId);
// model.addAttribute("noticeList", noticeList);
// return "employee/subscription";
// }

// /* =============================기업회원=========================================
// */

// @GetMapping("/co/noticeDetail")
// public String noticeDetail() {// 기업회원 입장에서 채용공고 상세보기
// return "company/noticeDetail";
// }

// @GetMapping("/cs/co/noticeSave/{companyId}")
// public String 공고등록(@PathVariable Integer companyId, Model model) {
// session.getAttribute("coprincipal");
// List<Job> jobPS = jobService.관심직무보기();
// model.addAttribute("jobPS", jobPS);
// return "notice/noticeSave";
// }

// @PostMapping("/coapi/cs/co/noticeSave")
// public @ResponseBody ResponseDto<?> insert(@RequestBody Notice notice) {
// noticeService.공고등록(notice);
// return new ResponseDto<>(1, "통신성공", null);
// }

// @GetMapping("/cs/co/noticeService/{companyId}")
// public String FindAllmyNotice(@PathVariable Integer companyId, Model model) {
// // 메서드이름은 동사여야 하지 않나요
// List<Notice> noticeList = noticeService.내공고목록보기(companyId);
// model.addAttribute("noticeList", noticeList);
// return "company/supporter";
// }

// @GetMapping("/cs/co/noticeService/{companyId}/noticeDetail/{noticeId}")
// public String updateMyNotice(@PathVariable Integer companyId,
// @PathVariable Integer noticeId, Model model) {
// List<Job> jobPS = jobService.관심직무보기();
// model.addAttribute("jobPS", jobPS);
// Notice noticePS = noticeService.내공고상세보기(noticeId);
// model.addAttribute("noticePS", noticePS);
// return "notice/noticeUpdate";
// }

// // @PutMapping("/coapi/cs/co/noticeUpdate/{noticeId}")
// // public @ResponseBody ResponseDto<?> updateResume(@PathVariable Integer
// // noticeId,
// // @RequestBody NoticeUpdateDto noticeUpdateDto) {
// // noticeService.이력서수정(noticeId, noticeUpdateDto);
// // return new ResponseDto<>(1, "공고 수정 성공", null);
// // }

// @DeleteMapping("/coapi/cs/co/noticeDelete/{noticeId}")
// public @ResponseBody ResponseDto<?> deleteNotice(@PathVariable Integer
// noticeId) {
// noticeService.내공고삭제(noticeId);
// return new ResponseDto<>(1, "공고 삭제 성공", null);
// }
// }
