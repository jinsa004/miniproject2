package site.metacoding.miniproject.config.auth.company;

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
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyLoginReqDto;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.util.SHA256;

@Slf4j
@RequiredArgsConstructor
public class CompanyJwtAuthenticationFilter implements Filter {

  private final CompanyDao companyDao;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    // Post 요청 아닌거 걸러내기
    if (!req.getMethod().equals("POST")) {
      filterResponse("로그인 시에는 post 요청을 해야합니다.", resp);
      return;
    }

    // Body 값 받기
    ObjectMapper om = new ObjectMapper();
    CompanyLoginReqDto companyLoginReqDto = om.readValue(req.getInputStream(), CompanyLoginReqDto.class);
    log.debug("디버그 : " + companyLoginReqDto.getCompanyUsername());
    log.debug("디버그 : " + companyLoginReqDto.getCompanyPassword());

    // 유저네임 체크
    Company companyPS = companyDao.findByCompanyUsername(companyLoginReqDto.getCompanyUsername());
    if (companyPS == null) {
      filterResponse("유저네임을 찾을 수 없습니다.", resp);
      return;
    }

    // 패스워드 체크
    SHA256 sh = new SHA256();
    String encPassword = sh.encrypt(companyLoginReqDto.getCompanyPassword());
    if (!companyPS.getCompanyPassword().equals(encPassword)) {
      filterResponse("패스워드가 틀렸습니다.", resp);
      return;
    }

    // JWT토큰 생성
    Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

    String jwtToken = JWT.create()
        .withSubject("메타코딩")
        .withExpiresAt(expire)
        .withClaim("companyId", companyPS.getCompanyId())
        .withClaim("companyUsername", companyPS.getCompanyUsername())
        .sign(Algorithm.HMAC512("취직"));
    log.debug("디버그 : " + jwtToken);

    // JWT토큰 응답
    filterJwtResponse(jwtToken, companyPS, resp);
  }

  private void filterJwtResponse(String token, Company companyPS, HttpServletResponse resp)
      throws IOException, JsonProcessingException {
    resp.setContentType("application/json; charset=utf-8");
    resp.setHeader("Authorization", "Bearer " + token);
    PrintWriter out = resp.getWriter();
    resp.setStatus(200);
    ResponseDto<?> responseDto = new ResponseDto<>(1, "성공", new CompanySessionUser(companyPS));
    ObjectMapper om = new ObjectMapper();
    String body = om.writeValueAsString(responseDto);
    out.println(body);
    out.flush();
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
