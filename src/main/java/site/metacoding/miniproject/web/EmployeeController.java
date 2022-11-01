package site.metacoding.miniproject.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.check.employee.EmpCheck;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.intro.Intro;
import site.metacoding.miniproject.domain.job.Job;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpUpdateReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpUpdateRespDto;
import site.metacoding.miniproject.service.EmployeeService;
import site.metacoding.miniproject.service.IntroService;
import site.metacoding.miniproject.service.JobService;
import site.metacoding.miniproject.service.ResumeService;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ResumeService resumeService;
    private final IntroService introService;
    private final JobService jobService;
    private final HttpSession session;

    @PostMapping("/emp/login")
    public @ResponseBody ResponseDto<?> login(@RequestBody EmpLoginDto empLoginDto, HttpServletResponse response) {
        System.out.println("===============");
        System.out.println(empLoginDto.isRemember());
        System.out.println("===============");

        // if (loginDto.isRemember() == true) {
        // Cookie cookie = new Cookie("employeeUsername",
        // loginDto.getEmployeeUsername());
        // cookie.setMaxAge(60 * 60 * 24);
        // response.addCookie(cookie);

        // } else {
        // Cookie cookie = new Cookie("employeeUsername", null);
        // cookie.setMaxAge(0);
        // response.addCookie(cookie);
        // }

        Employee principal = employeeService.로그인(empLoginDto);
        if (principal == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        session.setAttribute("empprincipal", principal);
        return new ResponseDto<>(1, "로그인성공", null);
    }

    @GetMapping("/es/emp/subscription")
    public String subscriptionList() {// 개인회원이 보는 구독기업공고탭(구독기업 공고 목록보기)
        return "employee/subscription";
    }

    @GetMapping("/emp/companyIntroDetail/{introId}")
    public String introDetail(@PathVariable Integer introId, Model model) {// 개인회원 보는 기업소개 상세보기
        Employee principal = (Employee) session.getAttribute("empprincipal");
        if (principal == null) {
            model.addAttribute("detailDto", introService.기업소개상세보기(introId, 0));
        } else {
            model.addAttribute("detailDto", introService.기업소개상세보기(introId, principal.getEmployeeId()));
        }
        return "employee/coIntroDetail";
    }

    @PostMapping("/empapi/es/emp/companyIntroDetail/{introId}/subscribe")
    public @ResponseBody ResponseDto<?> insertSub(@PathVariable Integer introId) {// 구독하기
        Employee principal = (Employee) session.getAttribute("empprincipal");
        Subscribe subscribe = new Subscribe(principal.getEmployeeId(), introId);
        introService.구독하기(subscribe);
        return new ResponseDto<>(1, "구독성공", subscribe);
    }

    @DeleteMapping("/empapi/es/emp/companyIntroDetail/{introId}/subscribe/{subscribeId}")
    public @ResponseBody ResponseDto<?> deleteSub(@PathVariable Integer introId, @PathVariable Integer subscribeId) {// 구독취소
        introService.구독취소하기(subscribeId);
        return new ResponseDto<>(1, "구독취소성공", null);
    }

    @GetMapping("/emp/companyList")
    public String companylist(Model model) {// 개인회원이 보는 기업소개 목록보기
        List<Intro> introList = introService.기업소개목록보기();
        model.addAttribute("introList", introList);
        return "employee/companyList";
    }

    @GetMapping("emp/companyList/search")
    public String getJobNoticeList(@RequestParam("jobCode") Integer jobCode, Model model) {
        List<Intro> jobIntroList = introService.기업소개분야별목록보기(jobCode);
        model.addAttribute("jobNoticeList", jobIntroList);
        return "employee/companyJobList";
    }

    @GetMapping("/es/emp/mypageInsertForm/{employeeId}")
    public String mypageResumeInsert(@PathVariable Integer employeeId, Model model) {// 이력서 등록, 수정, 삭제, 대표 이력서 선택
        List<Resume> resumePS = resumeService.내이력서가져오기(employeeId);
        model.addAttribute("resumePS", resumePS);
        session.getAttribute("principal");
        return "employee/mypageInsertForm";
    }

    @GetMapping("/es/emp/employeeInfo/{employeeId}")
    public String 회원정보수정탈퇴페이지(@PathVariable Integer employeeId, Model model) {// 개인회원 회원가입 정보수정
        // 리스트값 불러오기
        List<Job> jobPS = jobService.관심직무보기();
        model.addAttribute("jobPS", jobPS);
        List<EmpCheck> checkPS = employeeService.관심분야값보기(employeeId);
        model.addAttribute("checkPS", checkPS);

        // 세션값담기
        Employee employeePS = (Employee) session.getAttribute("empprincipal");
        /* Employee employeePS = employeeService.employeeUpdate(employeeId); */
        model.addAttribute("employee", employeePS);
        return "employee/empInfo";
    }

    @DeleteMapping("/empapi/es/emp/employeeInfo/{employeeId}")
    public @ResponseBody ResponseDto<?> 회원탈퇴(@PathVariable Integer employeeId, HttpServletResponse response) {
        employeeService.employeeDelete(employeeId);
        Cookie cookie = new Cookie("employeeUsername", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.invalidate();
        return new ResponseDto<>(1, "회원탈퇴성공", null);
    }

    @PutMapping("/empapi/es/emp/employeeInfo/{employeeId}")
    public @ResponseBody ResponseDto<?> 회원정보수정(@PathVariable Integer employeeId,
            @RequestBody EmpUpdateReqDto empUpdateReqDto) {
        EmpUpdateRespDto empUpdateRespDtoPS = employeeService.employeeUpdate(employeeId,
                empUpdateReqDto);
        session.setAttribute("empprincipal", empUpdateRespDtoPS);
        return new ResponseDto<>(1, "회원수정성공", null);
    }

    @PostMapping("/emp/join")
    public @ResponseBody ResponseDto<?> 회원가입(@RequestBody EmpJoinReqDto employeeJoinDto) {
        employeeService.employeeJoin(employeeJoinDto);
        return new ResponseDto<>(1, "회원가입성공", null);
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // =========================== 유효성체크 ======================================
    // http://localhost:8000/users/usernameSameCheck?username=ssar
    @GetMapping("/emp/usernameSameCheck")
    public @ResponseBody ResponseDto<Boolean> usernameSameCheck(String employeeUsername) {
        boolean isSame = employeeService.유저네임중복확인(employeeUsername);
        return new ResponseDto<>(1, "성공", isSame);
    }

    @GetMapping("/emp/checkPassword")
    public @ResponseBody ResponseDto<Boolean> checkPassword(String employeePassword) {
        boolean isSame = employeeService.비밀번호2차체크(employeePassword);
        return new ResponseDto<>(1, "성공", isSame);
    }

    @GetMapping("/emp/checkEmail")
    public @ResponseBody ResponseDto<Boolean> checkEmail(String employeeEmail) {
        boolean isSame = employeeService.이메일형식체크(employeeEmail);
        return new ResponseDto<>(1, "성공", isSame);
    }
}
