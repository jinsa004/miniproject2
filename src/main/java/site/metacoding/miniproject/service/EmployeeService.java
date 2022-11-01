package site.metacoding.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.check.employee.EmpCheck;
import site.metacoding.miniproject.domain.check.employee.EmpCheckDao;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.employee.EmployeeDao;
import site.metacoding.miniproject.dto.check.employee.EmpCheckRespDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpUpdateReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpUpdateRespDto;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmpCheckDao empCheckDao;

    public void employeeDelete(Integer employeeId) {
        employeeDao.deleteById(employeeId);
    }

    public List<EmpCheck> 관심분야값보기(Integer employeeId) {
        return empCheckDao.findCheckAll(employeeId);
    }

    public EmpUpdateRespDto employeeUpdate(Integer employeeId, EmpUpdateReqDto empUpdateReqDto) {
        // emp_check 값 업데이트
        empCheckDao.deleteById(employeeId);
        for (Integer jobId : empUpdateReqDto.getJobIds()) {
            empCheckDao.insert(employeeId, jobId);
        }
        List<EmpCheckRespDto> jobCheckList = empCheckDao.findAll(employeeId);

        // 회원정보 업데이트
        Employee employeePS = employeeDao.findById(employeeId);
        employeePS.update(empUpdateReqDto);
        employeeDao.update(employeePS);
        return new EmpUpdateRespDto(employeePS, jobCheckList);
    }

    public Employee 로그인(EmpLoginDto loginDto) {
        Employee employeePS = employeeDao.findByEmployeeUsername(loginDto.getEmployeeUsername());

        // if (employeePS.getEmployeePassword().equals(loginDto.getEmployeePassword()))
        if (employeePS != null && employeePS.getEmployeePassword().equals(loginDto.getEmployeePassword())) {
            return employeePS;
        }
        return null;
    }

    @Transactional
    public void employeeJoin(EmpJoinReqDto empJoinReqDto) {
        Employee employee = empJoinReqDto.toEmpEntity();
        employeeDao.insert(employee);

        for (Integer jobId : empJoinReqDto.getJobIds()) {
            empCheckDao.insert(employee.getEmployeeId(), jobId);
        }
        List<EmpCheckRespDto> jobCheckList = empCheckDao.findAll(employee.getEmployeeId());
    }

    // =========================== 유효성체크 ======================================
    public boolean 유저네임중복확인(String employeeUsername) {
        Employee employeePS = employeeDao.findByEmployeeUsername(employeeUsername);
        if (employeePS == null)
            return false;
        return true;
        // 있으면 true, 없으면 false
    }

    public boolean 비밀번호2차체크(String employeePassword) {
        employeeDao.findByEmployeePassword(employeePassword);
        return true;
    }

    public boolean 이메일형식체크(String employeeEmail) {
        Employee employeePS = employeeDao.findByEmployeeEmail(employeeEmail);
        if (employeePS == null)
            return false;
        return true;
    }
}
