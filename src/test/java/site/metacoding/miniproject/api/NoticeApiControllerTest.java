package site.metacoding.miniproject.api;

import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;

@Slf4j
@ActiveProfiles("test")
// @Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class NoticeApiControllerTest {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private NoticeDao noticeDao;

	private MockHttpSession session;

	@BeforeEach // 최초 트랜잭션이 실행됨. BeforeEach는 각각의 테스트가 실행되기 직전에 먼저 실행되는 것.**
	public void sessionInit() {
		session = new MockHttpSession();
		Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

		String jwtToken = JWT.create()
				.withSubject("메타코딩")
				.withExpiresAt(expire)
				.withClaim("companyId", 1)
				.withClaim("companyUsername", "jinsa")
				.sign(Algorithm.none());
		DecodedJWT decodedJWT = JWT.decode(jwtToken);
		Integer companyId = decodedJWT.getClaim("companyId").asInt();
		String companyUsername = decodedJWT.getClaim("companyUsername").asString();
		CompanySessionUser companySessionUser = new CompanySessionUser(
				Company.builder().companyId(companyId).companyUsername(companyUsername).build());
		session.setAttribute("companySessionUser", companySessionUser);
	}

	@Test
	public void saveNotice_test() throws Exception {
		// given
		NoticeSaveReqDto noticeSaveReqDto = new NoticeSaveReqDto();
		noticeSaveReqDto.setNoticeTitle("사원모집");
		noticeSaveReqDto.setCompanyId(1);
		noticeSaveReqDto.setJobId(1);

		String body = om.writeValueAsString(noticeSaveReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/cs/co/notice/save").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void findByCompanyIdToNotice_test() throws Exception {
		// given
		Integer companyId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/notice/" + companyId).accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void updateNotice_test() throws Exception {
		// given
		Integer noticeId = 6;
		Notice noticePS = noticeDao.findById(noticeId);
		NoticeUpdateReqDto noticeUpdateReqDto = new NoticeUpdateReqDto();
		noticeUpdateReqDto.setNoticeTitle("테스트중");
		noticeUpdateReqDto.setNoticeDept("망한부서");
		noticeUpdateReqDto.setNoticeTask("설거지");
		noticePS.update(noticeUpdateReqDto);

		String body = om.writeValueAsString(noticePS);

		// when
		ResultActions resultActions = mvc
				.perform(put("/cs/co/notice/update/" + noticePS.getNoticeId()).content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void deleteNotice_test() throws Exception {
		// given
		Integer noticeId = 6;

		// when
		ResultActions resultActions = mvc
				.perform(delete("/cs/co/notice/delete/" + noticeId)
						.accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void noticeDetail_test() throws Exception {
		// given
		Integer noticeId = 1;
		Integer companyId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/notice/" + companyId + "/detail/" + noticeId).accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}
}
