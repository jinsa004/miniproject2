package site.metacoding.miniproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.check.employee.EmpCheckDao;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.employee.EmployeeDao;
import site.metacoding.miniproject.dto.check.employee.EmpCheckRespDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.dto.employee.EmpRespDto.EmpJoinRespDto;
import site.metacoding.miniproject.util.SHA256;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmpCheckDao empCheckDao;
    private final SHA256 sha256;
    private final HttpSession session;

    @Transactional(readOnly = true)
    public EmpSessionUser 로그인(EmpLoginReqDto empLoginReqDto) {
        Employee employeePS = employeeDao.findByEmployeeUsername(empLoginReqDto.getEmployeeUsername());
        String encPassword = sha256.encrypt(empLoginReqDto.getEmployeePassword());
        if (employeePS != null &&
                employeePS.getEmployeePassword().equals(encPassword)) {
            return new EmpSessionUser(employeePS);
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
    }

    @Transactional
    public EmpJoinRespDto employeeJoin(EmpJoinReqDto empJoinReqDto) {
        // 비밀번호 해시
        String encPassword = sha256.encrypt(empJoinReqDto.getEmployeePassword());
        empJoinReqDto.setEmployeePassword(encPassword);

        Employee employeePS = empJoinReqDto.toEntity();
        employeeDao.insert(employeePS);
        // employeeDao.insert(employee);

        for (Integer jobId : empJoinReqDto.getJobIds()) {
            empCheckDao.insert(employeePS.getEmployeeId(), jobId);
        }
        List<EmpCheckRespDto> jobCheckList = empCheckDao.findAll(employeePS.getEmployeeId());

        EmpJoinRespDto empJoinRespDto = new EmpJoinRespDto(employeePS, jobCheckList);
        return empJoinRespDto;
    }

    public void deleteEmployee(Integer employeeId) {
        employeeDao.deleteById(employeeId);
    }

    /*
     * public List<EmpCheck> 관심분야값보기(Integer employeeId) {
     * return empCheckDao.findCheckAll(employeeId);
     * }
     */

    /*
     * public EmpUpdateRespDto employeeUpdate(Integer employeeId, EmpUpdateReqDto
     * empUpdateReqDto) {
     * // emp_check 값 업데이트
     * empCheckDao.deleteById(employeeId);
     * for (Integer jobId : empUpdateReqDto.getJobIds()) {
     * empCheckDao.insert(employeeId, jobId);
     * }
     * List<EmpCheckRespDto> jobCheckList = empCheckDao.findAll(employeeId);
     * 
     * // 회원정보 업데이트
     * Employee employeePS = employeeDao.findById(employeeId);
     * employeePS.update(empUpdateReqDto);
     * employeeDao.update(employeePS);
     * return new EmpUpdateRespDto(employeePS, jobCheckList);
     * }
     */

    // =========================== 유효성체크 ======================================
    /*
     * public boolean 유저네임중복확인(String employeeUsername) {
     * Employee employeePS = employeeDao.findByEmployeeUsername(employeeUsername);
     * if (employeePS == null)
     * return false;
     * return true;
     * // 있으면 true, 없으면 false
     * }
     * 
     * public boolean 비밀번호2차체크(String employeePassword) {
     * employeeDao.findByEmployeePassword(employeePassword);
     * return true;
     * }
     * 
     * public boolean 이메일형식체크(String employeeEmail) {
     * Employee employeePS = employeeDao.findByEmployeeEmail(employeeEmail);
     * if (employeePS == null)
     * return false;
     * return true;
     * }
     */
}
