package site.metacoding.miniproject.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.intro.Intro;
import site.metacoding.miniproject.domain.intro.IntroDao;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinReqDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;

@ActiveProfiles("test")
// @Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyApiControllerTest {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private IntroDao introDao;

	private static HttpHeaders headers;

	// private MockHttpSession session;

	// 기업소개 등록 테스트
	@Test
	public void insertIntro_test() throws Exception {
		// given
		IntroSaveReqDto introSaveReqDto = new IntroSaveReqDto();
		introSaveReqDto.setIntroConame("삼성");
		introSaveReqDto.setIntroBirth("19991111");
		introSaveReqDto.setIntroTask("밥먹기");

		String body = om.writeValueAsString(introSaveReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/cs/co/intro/insert").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON));
		// then

		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	// 기업입장에서 기업소개 상세보기
	@Test
	public void findByCompanyId_test() throws Exception {
		// given
		Integer companyId = 1;
		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/companyIntroDetail/" + companyId).accept(APPLICATION_JSON));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.companyId").value(1));
	}

	// 기업소개 수정
	@Test
	public void updateIntro_test() throws Exception {
		// given
		Integer introId = 4;
		Intro introPS = introDao.findByIntroId(introId);
		IntroUpdateReqDto introUpdateReqDto = new IntroUpdateReqDto();
		introUpdateReqDto.setIntroId(introId);
		introUpdateReqDto.setIntroBirth(introPS.getIntroBirth());
		introUpdateReqDto.setIntroContent(introPS.getIntroContent());
		String body = om.writeValueAsString(introUpdateReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(put("/cs/co/intro/update/" + introUpdateReqDto.getIntroId()).content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON));
		// then

		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	// 기업 회원가입
	// @Test
	// public void CoJoin_test() throws JsonProcessingException {
	// // given
	// CompanyJoinReqDto companyJoinReqDto = new CompanyJoinReqDto();
	// companyJoinReqDto.setCompanyName("very");
	// companyJoinReqDto.setCompanyPassword("1234");

	// String body = om.writeValueAsString(companyJoinReqDto);
	// System.out.println(body);

	// // when
	// HttpEntity<String> request = new HttpEntity<>(body, headers);
	// ResponseEntity<String> response = rt.exchange("/join", HttpMethod.POST,
	// request, String.class);

	// // then
	// // System.out.println(response.getStatusCode());
	// // System.out.println(response.getBody());

	// DocumentContext dc = JsonPath.parse(response.getBody());
	// // System.out.println(dc.jsonString());
	// Integer code = dc.read("$.code");
	// Assertions.assertThat(code).isEqualTo(1);
	// }

	// 기업회원정보 보기
	@Test
	public void findByCompanyIdToCompanyDetail_test() throws Exception {
		// given
		Integer companyId = 1;
		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/company/detail/" + companyId).accept(APPLICATION_JSON));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.companyName").value("삼성전자"));
	}
	// 기업 업데이트
	// @Test
	// public void updateCompany_test() throws Exception {
	// // given
	// Integer companyId = 1;
	// Company companyPS = companyDao.findById(companyId);
	// CompanyUpdateReqDto companyUpdateReqDto = new CompanyUpdateReqDto();
	// companyUpdateReqDto.setCompanyId(companyId);
	// companyUpdateReqDto.setCompanyName(companyPS.getCompanyName());
	// companyUpdateReqDto.setCompanyNumber(companyPS.getCompanyNumber());
	// String body = om.writeValueAsString(companyUpdateReqDto);

	// // when
	// ResultActions resultActions = mvc
	// .perform(put("/cs/co/company/update/" +
	// companyUpdateReqDto.getCompanyId()).content(body)
	// .contentType(APPLICATION_JSON)
	// .accept(APPLICATION_JSON));
	// // then

	// MvcResult mvcResult = resultActions.andReturn();
	// System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
	// resultActions.andExpect(jsonPath("$.code").value(1));
	// }

	// 기업 탈퇴
	@Test
	public void deleteById_test() throws Exception {
		// given
		Integer companyId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(delete("/cs/co/company/delete/" + companyId)
						.accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1L));

	}

}
