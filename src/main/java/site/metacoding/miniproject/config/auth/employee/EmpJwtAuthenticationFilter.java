package site.metacoding.miniproject.config.auth.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.employee.EmployeeDao;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpLoginReqDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.util.SHA256;

@Slf4j
@RequiredArgsConstructor
public class EmpJwtAuthenticationFilter implements Filter {

    private final EmployeeDao employeeDao; // DI (FilterConfig 주입받음)

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Post요청이 아닌것을 거부
        if (!req.getMethod().equals("POST")) {
            filterResponse("로그인시에는 post요청을 해야 합니다.", resp);
            return;
        }

        // body값 받기
        ObjectMapper om = new ObjectMapper();
        EmpLoginReqDto empLoginReqDto = om.readValue(req.getInputStream(),
                EmpLoginReqDto.class);
        log.debug("디버그 : " + empLoginReqDto.getEmployeeUsername());
        log.debug("디버그 : " + empLoginReqDto.getEmployeePassword());

        // EmployeeUsername 있는지 체크
        Employee employeePS = employeeDao.findByEmployeeUsername(empLoginReqDto.getEmployeeUsername());
        if (employeePS == null) {
            filterResponse("유저네임을 찾을 수 없습니다.", resp);
            return;
        }

        // 패스워드 체크
        SHA256 sh = new SHA256();
        String encPassword = sh.encrypt(empLoginReqDto.getEmployeePassword());
        log.debug("디버그 : 비번" + encPassword);
        if (!employeePS.getEmployeePassword().equals(encPassword)) {
            filterResponse("패스워드가 틀렸습니다.", resp);
            return;
        }

        // JWT토큰 생성 1초 = 1/1000
        Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

        String jwtToken = JWT.create()
                .withSubject("메타코딩")
                .withExpiresAt(expire)
                .withClaim("employeeId", employeePS.getEmployeeId())
                .withClaim("employeeUsername", employeePS.getEmployeeUsername())
                .sign(Algorithm.HMAC512("취직"));
        log.debug("디버그 : " + jwtToken);

        // JWT토큰 응답
        filterJwtResponse(jwtToken, employeePS, resp);
    }

    private void filterResponse(String msg, HttpServletResponse resp) throws IOException, JsonProcessingException {
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        resp.setStatus(400);
        ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
        ObjectMapper om = new ObjectMapper();
        String body = om.writeValueAsString(responseDto);
        out.println(body);
        out.flush();
    }

    private void filterJwtResponse(String token, Employee employeePS, HttpServletResponse resp)
            throws IOException, JsonProcessingException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Authorization", "Bearer " + token);
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인 성공", new EmpSessionUser(employeePS));
        ObjectMapper om = new ObjectMapper();
        String body = om.writeValueAsString(responseDto);
        out.println(body);
        out.flush();
    }
}
