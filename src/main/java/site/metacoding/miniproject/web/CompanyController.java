// package site.metacoding.miniproject.web;

// import java.util.List;

// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseBody;

// import lombok.RequiredArgsConstructor;
// import site.metacoding.miniproject.domain.company.Company;
// import site.metacoding.miniproject.domain.intro.Intro;
// import site.metacoding.miniproject.domain.job.Job;
// import site.metacoding.miniproject.dto.ResponseDto;
// import
// site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinReqDto;
// import
// site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyLoginReqDto;
// import
// site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
// import
// site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
// import site.metacoding.miniproject.dto.company.CompanySessionUser;
// import site.metacoding.miniproject.service.CompanyService;
// import site.metacoding.miniproject.service.IntroService;
// import site.metacoding.miniproject.service.JobService;

// @RequiredArgsConstructor
// @Controller
// public class CompanyController {

// private final CompanyService companyService;
// private final HttpSession session;
// private final IntroService introService;
// private final JobService jobService;

// @PostMapping("/co/login2")
// public @ResponseBody ResponseDto<?> login(@RequestBody CompanyLoginReqDto
// companyLoginReqDto,
// HttpServletResponse response) {
// System.out.println("===============");
// System.out.println(companyLoginReqDto.isRemember());
// System.out.println("===============");

// // if (loginDto.isRemember() == true) {
// // Cookie cookie = new Cookie("companyUsername",
// loginDto.getCompanyUsername());
// // cookie.setMaxAge(60 * 60 * 24);
// // response.addCookie(cookie);

// // } else {
// // Cookie cookie = new Cookie("companyUsername", null);
// // cookie.setMaxAge(0);
// // response.addCookie(cookie);
// // }

// CompanySessionUser companySessionUser =
// companyService.?????????(companyLoginReqDto);
// if (companySessionUser == null) {
// return new ResponseDto<>(-1, "???????????????", null);
// }
// session.setAttribute("coprincipal", companySessionUser);
// return new ResponseDto<>(1, "???????????????", companySessionUser);
// }

// @GetMapping("/cs/co/companyInfo/{companyId}")
// public @ResponseBody ResponseDto<?> ??????????????????(@PathVariable Integer companyId,
// Model model) {
// List<Job> jobPS = jobService.??????????????????();
// model.addAttribute("jobPS", jobPS);
// Company companyPS = (Company) session.getAttribute("coprincipal");
// model.addAttribute("company", companyPS);
// return new ResponseDto<>(1, "??????", null);
// }

// @PutMapping("/coapi/cs/co/companyUpdate/{companyId}")
// public @ResponseBody ResponseDto<?> companyUpdate(@PathVariable Integer
// companyId,
// @RequestBody CompanyUpdateReqDto companyUpdateReqDto) {
// CompanyUpdateRespDto companyPS = companyService.????????????????????????(companyId,
// companyUpdateReqDto);
// session.setAttribute("coprincipal", companyPS);
// return new ResponseDto<>(1, "????????????", companyPS);
// }

// @DeleteMapping("/coapi/cs/co/companyDelete/{companyId}")
// public @ResponseBody ResponseDto<?> companyDelete(@PathVariable Integer
// companyId) {
// companyService.??????????????????(companyId);
// session.invalidate();
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @GetMapping("/cs/co/companyIntroInsert")
// public String ?????????????????????(Model model) {// ?????????
// session.getAttribute("coprincipal");
// List<Job> jobPS = jobService.??????????????????();
// model.addAttribute("jobPS", jobPS);
// return "company/coIntroInsert";
// }

// // @PostMapping("/coapi/cs/co/companyIntroInsert")
// // public @ResponseBody ResponseDto<?> ??????????????????(IntroInsertDto introInsertDto)
// // throws Exception {
// // introService.??????????????????(introInsertDto);
// // return new ResponseDto<>(1, "???????????? ?????? ??????", null);
// // }

// @GetMapping("/cs/co/companyIntroDetail/{companyId}")
// public String ??????????????????(@PathVariable Integer companyId, Model model) {// ????????????
// ???????????? intro ?????????
// session.getAttribute("coprincipal");
// Intro introPS = introService.????????????????????????(companyId);
// model.addAttribute("introPS", introPS);
// List<Job> jobPS = jobService.??????????????????();
// model.addAttribute("jobPS", jobPS);
// return "company/coIntroDetail";
// }

// @GetMapping("/cs/co/companyIntroUpdate/{companyId}")
// public String getIntroUpdate(@PathVariable Integer companyId, Model model) {
// session.getAttribute("coprincipal");
// List<Job> jobPS = jobService.??????????????????();
// model.addAttribute("jobPS", jobPS);
// model.addAttribute("intro", introService.????????????????????????(companyId));
// return "company/coIntroUpdate";
// }

// // @PutMapping("/coapi/cs/co/companyIntroUpdate/{companyId}/update")
// // public @ResponseBody ResponseDto<?> putIntroUpdate(@PathVariable Integer
// // companyId,
// // @RequestBody UpdateDto updateDto) {
// // introService.????????????????????????(companyId, updateDto);
// // return new ResponseDto<>(1, "????????????", null);
// // }

// @PostMapping("/co/join2")
// public @ResponseBody ResponseDto<?> companyJoin(@RequestBody
// CompanyJoinReqDto companyJoinReqDto) {
// companyService.????????????(companyJoinReqDto);
// return new ResponseDto<>(1, "??????????????????", null);
// }

// @GetMapping("/co/usernameSameCheck")
// public @ResponseBody ResponseDto<?> usernameSameCheck(String companyUsername)
// {
// System.out.println("company??????:" + companyUsername);
// boolean isSame = companyService.??????????????????????????????(companyUsername);
// return new ResponseDto<>(1, "??????", isSame);
// }

// @GetMapping("/co/logout")
// public String Companylogout() {
// session.invalidate();
// return "redirect:/co";
// }

// // =========================== ??????????????? ======================================
// @GetMapping("co/checkPasswordCo")
// public @ResponseBody ResponseDto<Boolean> checkPasswordCo(String
// companyPassword) {
// boolean isSame = companyService.??????????????????2?????????(companyPassword);
// return new ResponseDto<>(1, "??????", isSame);
// }

// @GetMapping("co/checkEmailCo")
// public @ResponseBody ResponseDto<Boolean> checkEmailCo(String companyEmail) {
// boolean isSame = companyService.???????????????????????????(companyEmail);
// return new ResponseDto<>(1, "??????", isSame);
// }
// }
