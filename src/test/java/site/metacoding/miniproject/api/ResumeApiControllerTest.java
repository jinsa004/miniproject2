package site.metacoding.miniproject.api;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.resume.ResumeDao;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ApplicationSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateMainReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;

@Slf4j
@ActiveProfiles("test")
// @Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ResumeApiControllerTest {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private ResumeDao resumeDao;

	private MockHttpSession session;

	@BeforeEach
	public void empSessionInit() {
		session = new MockHttpSession();
		Company company = Company.builder().companyId(1).companyUsername("삼성전자").build();
		session.setAttribute("companySessionUser", new CompanySessionUser(company));

		Employee employee = Employee.builder().employeeId(1).employeeUsername("jinsa").build();
		session.setAttribute("empSessionUser", new EmpSessionUser(employee));
	}

	// ==================== 개인 화면 ====================== //

	@Test
	public void mypageResumeList_test() throws Exception {
		// given
		EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
		// when
		ResultActions resultActions = mvc
				.perform(get("/es/emp/mypage/resume/" + empSessionUser.getEmployeeId())
						.accept(APPLICATION_JSON)
						.session(session));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		log.debug("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.[0]resumeTitle").value("완성하겠습니다."));
	}

	@Test
	public void insertResume_test() throws Exception {
		// given
		ResumeSaveReqDto resumeSaveReqDto = new ResumeSaveReqDto();
		resumeSaveReqDto.setResumeTitle("5252");
		resumeSaveReqDto.setJobId(1);

		String body = om.writeValueAsString(resumeSaveReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/es/emp/resume/save").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.session(session));
		// then

		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void deleteResume_test() throws Exception {
		// given
		Integer resumeId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(delete("/es/emp/resume/delete/" + resumeId)
						.accept(APPLICATION_JSON)
						.session(session));
		// then

		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void updateResume_test() throws Exception {
		// given
		Integer resumeId = 1;
		Resume resumePS = resumeDao.findById(resumeId);
		ResumeUpdateReqDto resumeUpdateReqDto = new ResumeUpdateReqDto();
		resumeUpdateReqDto.setResumeId(resumeId);
		resumeUpdateReqDto.setResumeTitle("영운고고고고고고고고고");
		resumeUpdateReqDto.setJobId(resumePS.getJobId());

		String body = om.writeValueAsString(resumeUpdateReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(put("/es/emp/resume/update/" + resumeUpdateReqDto.getResumeId()).content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.session(session));
		// then

		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void setMainResume_test() throws Exception {
		// given
		EmpSessionUser sessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
		Integer resumeId = 1;
		ResumeUpdateMainReqDto resumeUpdateMainReqDto = new ResumeUpdateMainReqDto();
		resumeUpdateMainReqDto.setEmployeeId(sessionUser.getEmployeeId());
		resumeUpdateMainReqDto.setResumeId(resumeId);
		String body = om.writeValueAsString(resumeUpdateMainReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(put("/es/emp/resume/setMain/" + resumeId).content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.session(session));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void applicateByResumeId_test() throws Exception {

		// when
		Integer resumeId = 1;
		Integer noticeId = 2;
		ApplicationSaveReqDto applicationSaveReqDto = new ApplicationSaveReqDto();
		applicationSaveReqDto.setResumeId(resumeId);
		applicationSaveReqDto.setNoticeId(noticeId);

		String body = om.writeValueAsString(applicationSaveReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/es/emp/resume/applicate").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.session(session));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void findResumeById() throws Exception {
		// given
		Integer resumeId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/es/emp/resume/" + resumeId)
						.accept(APPLICATION_JSON)
						.session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.resumeTitle").value("완성하겠습니다."));
	}

	// ====================== 기업 화면 ======================== //

	@Test
	public void getAllResumeList_test() throws Exception {
		// when
		ResultActions resultActions = mvc
				.perform(get("/co").accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.[0].resumeTitle").value("asdf"));
	}

	@Test
	public void getJobResumeList_test() throws Exception {
		// given
		Integer jobCode = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/co/resume?jobCode=" + jobCode).accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.[0].resumeTitle").value("asdf"));
	}

	@Test
	public void getCompanyMatchingList_test() throws Exception {
		// given
		CompanySessionUser companySessionUser = (CompanySessionUser) session.getAttribute("companySessionUser");
		log.debug("디버그 : " + companySessionUser.getCompanyId());

		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/matchingResume/" + companySessionUser.getCompanyId())
						.accept(APPLICATION_JSON)
						.session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.[0].resumeTitle").value("완성하겠습니다."));
	}

	@Test
	public void getResumeDetail_test() throws Exception {
		// given
		Integer resumeId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/cs/co/resume/detail/" + resumeId)
						.accept(APPLICATION_JSON)
						.session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.resumeTitle").value("완성하겠습니다."));
	}

}