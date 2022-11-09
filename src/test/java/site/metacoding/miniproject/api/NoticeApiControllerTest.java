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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;

@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@WebAppConfiguration
public class NoticeApiControllerTest {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private NoticeDao noticeDao;

	private MockHttpSession session;

	@BeforeEach
	public void empSessionInit() {
		session = new MockHttpSession();

		Employee employee = Employee.builder().employeeId(1).employeeUsername("jinsa").build();
		session.setAttribute("empSessionUser", new EmpSessionUser(employee));

		Company company = Company.builder().companyId(1).companyUsername("samsungman1234").build();
		session.setAttribute("companySessionUser", new CompanySessionUser(company));
	}

	@Test
	public void getAllNoticeList_test() throws Exception {
		// given

		// when
		ResultActions resultActions = mvc
				.perform(get("/")
						.accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.[0]noticeTitle").value("백엔드 개발자 모집중"));
	}

	@Test
	public void saveNotice_test() throws Exception {
		// given
		CompanySessionUser companySessionUser = (CompanySessionUser) session.getAttribute("companySessionUser");
		NoticeSaveReqDto noticeSaveReqDto = new NoticeSaveReqDto();
		noticeSaveReqDto.setNoticeTitle("사원모집");
		noticeSaveReqDto.setCompanyId(companySessionUser.getCompanyId());
		noticeSaveReqDto.setJobId(1);

		String body = om.writeValueAsString(noticeSaveReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/cs/co/notice/save").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.session(session));

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
				.perform(get("/cs/co/notice/" + companyId)
						.accept(APPLICATION_JSON)
						.session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void updateNotice_test() throws Exception {
		// given
		CompanySessionUser companySessionUser = (CompanySessionUser) session.getAttribute("companySessionUser");
		Integer noticeId = 2;
		Notice noticePS = noticeDao.findById(noticeId);
		NoticeUpdateReqDto noticeUpdateReqDto = new NoticeUpdateReqDto();
		noticeUpdateReqDto.setNoticeTitle("테스트중");
		noticeUpdateReqDto.setNoticeDept("망한부서");
		noticeUpdateReqDto.setNoticeTask("설거지");
		noticeUpdateReqDto.setCompanyId(companySessionUser.getCompanyId());
		noticePS.update(noticeUpdateReqDto);

		String body = om.writeValueAsString(noticePS);

		// when
		ResultActions resultActions = mvc
				.perform(put("/cs/co/notice/update/" + noticePS.getNoticeId()).content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON).session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void deleteNotice_test() throws Exception {
		// given
		Integer noticeId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(delete("/cs/co/notice/delete/" + noticeId)
						.accept(APPLICATION_JSON).session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.code").value(1));
	}

	@Test
	public void noticeDetail_test() throws Exception {
		// given
		Integer noticeId = 1;

		// when
		ResultActions resultActions = mvc
				.perform(get("/co/notice/detail/" + noticeId)
						.accept(APPLICATION_JSON)
						.session(session));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(jsonPath("$.data.noticeTitle").value("백엔드 개발자 모집중"));
	}
}
