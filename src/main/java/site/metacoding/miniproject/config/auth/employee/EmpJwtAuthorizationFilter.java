package site.metacoding.miniproject.config.auth.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;

@Slf4j
@RequiredArgsConstructor
public class EmpJwtAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 헤더 Authorization 키값에 Bearer로 적힌 값이 있는지 체크
        String jwtToken = req.getHeader("Authorization");
        log.debug("디버그 : " + jwtToken);
        if (jwtToken == null) {
            filterResponse("JWT토큰이 없어서 인가할 수 없습니다", resp);
            return;
        }

        // 토큰 검증
        jwtToken = jwtToken.replace("Bearer ", "");
        jwtToken = jwtToken.trim();
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("취직")).build().verify(jwtToken);
            Integer employeeId = decodedJWT.getClaim("employeeId").asInt();
            String employeeUsername = decodedJWT.getClaim("employeeUsername").asString();
            EmpSessionUser empSessionUser = new EmpSessionUser(
                    Employee.builder().employeeId(employeeId).employeeUsername(employeeUsername).build());
            HttpSession session = req.getSession();
            session.setAttribute("empSessionUser", empSessionUser);
        } catch (Exception e) {
            filterResponse("토큰 검증 실패", resp);
        }

        // 디스패처 서블릿 입장 또는 Filter체인 타기
        chain.doFilter(req, resp);
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

}
