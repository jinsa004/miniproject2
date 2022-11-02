package site.metacoding.miniproject.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.service.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmpApiController {

    private final EmployeeService employeeService;
    private final HttpSession session;

    @PostMapping("/emp/login")
    public ResponseDto<?> login(@RequestBody EmpLoginReqDto empLoginReqDto) {
        EmpSessionUser empSessionUser = employeeService.로그인(empLoginReqDto);

        System.out.println("===============");
        System.out.println(empLoginReqDto.isRemember());
        System.out.println("===============");

        // Employee principal = employeeService.로그인(empLoginReqDto);
        if (empSessionUser == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        session.setAttribute("empSessionUser", empSessionUser);
        return new ResponseDto<>(1, "로그인성공", empSessionUser);
    }

    @PostMapping("/emp/join")
    public ResponseDto<?> 회원가입(@RequestBody EmpJoinReqDto empJoinReqDto) {
        // EmpJoinRespDto empJoinRespDto = employeeService.employeeJoin(empJoinReqDto);
        employeeService.employeeJoin(empJoinReqDto);
        return new ResponseDto<>(1, "회원가입 성공", null);
    }

}
