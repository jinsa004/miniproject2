package site.metacoding.miniproject.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeRespDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto.SubscribeSaveReqDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeRespDto.SubscribeSaveRespDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpJoinRespDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.service.EmployeeService;
import site.metacoding.miniproject.service.IntroService;

@RequiredArgsConstructor
@RestController
public class EmployeeApiController {

    private final IntroService introService;
    private final EmployeeService employeeService;

    // 개인이 보는기업소개 상세보기
    @GetMapping("/emp/companyIntroDetail/{introId}")
    public ResponseDto<?> findByDetail(@PathVariable Integer introId, Integer principalId) {
        return new ResponseDto<>(1, "성공", introService.findByDetail(introId, principalId));
    }

    @PostMapping("/emp/join")
    public ResponseDto<?> 회원가입(@RequestBody EmpJoinReqDto empJoinReqDto) {
        EmpJoinRespDto empJoinRespDto = employeeService.employeeJoin(empJoinReqDto);
        // employeeService.employeeJoin(empJoinReqDto);
        return new ResponseDto<>(1, "회원가입 성공", empJoinRespDto);
    }

    // 구독하기
    // @PostMapping("/emp/subscribe")
    // public ResponseDto<?> insertSub(@RequestBody SubscribeSaveReqDto
    // subscribeSaveReqDto) {
    // // Employee principal = (Employee) session.getAttribute("empprincipal");
    // SubscribeSaveRespDto subscribeSaveRespDto =
    // introService.구독하기(subscribeSaveReqDto);
    // return new ResponseDto<>(1, "구독성공", subscribeSaveRespDto);
    // }

    // 구독취소
    @DeleteMapping("/emp/subscribe/{subscribeId}")
    public ResponseDto<?> deleteSub(
            @PathVariable Integer subscribeId) {
        introService.구독취소하기(subscribeId);
        return new ResponseDto<>(1, "구독취소성공", null);
    }
}
