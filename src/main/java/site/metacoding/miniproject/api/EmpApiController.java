package site.metacoding.miniproject.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpJoinRespDto;
import site.metacoding.miniproject.service.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmpApiController {

    private final EmployeeService employeeService;

    @PostMapping("/emp/login")
    public ResponseDto<?> login(@RequestBody EmpLoginReqDto empLoginReqDto,
            HttpServletResponse response) {
        // EmpSessionUser empSessionUser = employeeService.login(empLoginReqDto);

        System.out.println("===============");
        System.out.println(empLoginReqDto.isRemember());
        System.out.println("===============");

        Employee principal = employeeService.로그인(empLoginReqDto);
        if (principal == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        // session.setAttribute("empprincipal", principal);
        return new ResponseDto<>(1, "로그인성공", null);
    }

    @PostMapping("/emp/join")
    public ResponseDto<?> 회원가입(@RequestBody EmpJoinReqDto empJoinReqDto) {
        // EmpJoinRespDto empJoinRespDto = employeeService.employeeJoin(empJoinReqDto);
        employeeService.employeeJoin(empJoinReqDto);
        return new ResponseDto<>(1, "회원가입 성공", null);
    }

}
