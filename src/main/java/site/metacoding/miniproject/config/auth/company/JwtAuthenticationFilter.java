// package site.metacoding.miniproject.config.auth.company;
// package site.metacoding.miniproject.config.auth;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Date;
// import java.util.Optional;

// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import site.metacoding.miniproject.util.SHA256;

// @Slf4j
// @RequiredArgsConstructor
// public class JwtAuthenticationFilter implements Filter {

// private final UserRepository userRepository; // DI (FilterConfig 주입받음)

// // /login 요청시
// // post 요청시
// // username, password (json)
// // db확인
// // 토큰 생성
// @Override
// public void doFilter(ServletRequest request, ServletResponse response,
// FilterChain chain)
// throws IOException, ServletException {
// HttpServletRequest req = (HttpServletRequest) request;
// HttpServletResponse resp = (HttpServletResponse) response;

// // Post요청이 아닌것을 거부
// if (!req.getMethod().equals("POST")) {
// customResponse("로그인시에는 post요청을 해야 합니다.", resp);
// return;
// }

// // Body 값 받기
// ObjectMapper om = new ObjectMapper();
// LoginReqDto loginReqDto = om.readValue(req.getInputStream(),
// LoginReqDto.class);
// log.debug("디버그 : " + loginReqDto.getUsername());
// log.debug("디버그 : " + loginReqDto.getPassword());

// // 유저네임 있는지 체크
// Optional<User> userOP =
// userRepository.findByUsername(loginReqDto.getUsername());
// if (userOP.isEmpty()) {
// customResponse("유저네임을 찾을 수 없습니다.", resp);
// return;
// }

// // 패스워드 체크
// User userPS = userOP.get();
// SHA256 sh = new SHA256();
// String encPassword = sh.encrypt(loginReqDto.getPassword());
// if (!userPS.getPassword().equals(encPassword)) {
// customResponse("패스워드가 틀렸습니다.", resp);
// return;
// }

// // JWT토큰 생성 1초 = 1/1000
// Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

// String jwtToken = JWT.create()
// .withSubject("메타코딩")
// .withExpiresAt(expire)
// .withClaim("id", userPS.getId())
// .withClaim("username", userPS.getUsername())
// .sign(Algorithm.HMAC512("뺑소니"));
// log.debug("디버그 : " + jwtToken);

// // JWT토큰 응답
// customJwtResponse(jwtToken, userPS, resp);

// // chain.doFilter(req, resp);
// }

// private void customJwtResponse(String token, User userPS, HttpServletResponse
// resp)
// throws IOException, JsonProcessingException {
// resp.setContentType("application/json; charset=utf-8");
// resp.setHeader("Authorization", "Bearer " + token);
// PrintWriter out = resp.getWriter();
// resp.setStatus(200);
// ResponseDto<?> responseDto = new ResponseDto<>(1, "성공", new
// SessionUser(userPS));
// ObjectMapper om = new ObjectMapper();
// String body = om.writeValueAsString(responseDto);
// out.println(body);
// out.flush();
// }

// private void customResponse(String msg, HttpServletResponse resp) throws
// IOException, JsonProcessingException {
// resp.setContentType("application/json; charset=utf-8");
// PrintWriter out = resp.getWriter();
// resp.setStatus(400);
// ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
// ObjectMapper om = new ObjectMapper();
// String body = om.writeValueAsString(responseDto);
// out.println(body);
// out.flush();
// }

// }