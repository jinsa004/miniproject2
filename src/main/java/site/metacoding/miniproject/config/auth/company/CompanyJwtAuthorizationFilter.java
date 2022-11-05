package site.metacoding.miniproject.config.auth.company;

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

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanySessionUser;

@Slf4j
public class CompanyJwtAuthorizationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    String jwtToken = req.getHeader("Authorization");
    log.debug("디버그 토큰 : " + jwtToken);
    if (jwtToken == null) {
      filterResponse("JWT 토큰이 없어서 인가할 수 없습니다.", resp);
      return;
    }

    // 토큰 검증
    jwtToken = jwtToken.replace("Bearer ", "");
    jwtToken = jwtToken.trim();

    try {
      DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("취직")).build().verify(jwtToken);
      Integer companyId = decodedJWT.getClaim("companyId").asInt();
      String companyUsername = decodedJWT.getClaim("companyUsername").asString();
      CompanySessionUser companySessionUser = new CompanySessionUser(
          Company.builder().companyId(companyId).companyUsername(companyUsername).build());

      HttpSession coSession = req.getSession();
      coSession.setAttribute("companySessionUser", companySessionUser);
    } catch (Exception e) {
      filterResponse("토큰 검증 실패", resp);
    }

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
