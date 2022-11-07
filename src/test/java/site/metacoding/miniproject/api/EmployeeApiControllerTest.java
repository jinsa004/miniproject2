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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.employee.Employee;
import site.metacoding.miniproject.domain.employee.EmployeeDao;
import site.metacoding.miniproject.dto.company.CompanySessionUser;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;

@ActiveProfiles("test")
// @Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EmployeeApiControllerTest {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private EmployeeDao employeeDao;

	private MockHttpSession session;

	@BeforeEach
	public void empSessionInit() {
		session = new MockHttpSession();
		Employee employee = Employee.builder().employeeId(1).employeeUsername("jinsa").build();
		session.setAttribute("empSessionUser", new EmpSessionUser(employee));

		Company company = Company.builder().companyId(1).companyUsername("삼성전자").build();
		session.setAttribute("companySessionUser", new CompanySessionUser(company));
	}

	// 기업소개 목록보기
	@Test
	public void findAll_test() throws Exception {
		// given

		// when
		ResultActions resultActions = mvc
				.perform(MockMvcRequestBuilders.get("/emp/companyList").accept(APPLICATION_JSON));

		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
		resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].introTask").value("flutter 신규 앱 개발"));
	}

	// 유저측 기업소개 한건보기
	// 기업입장에서 기업소개 상세보기
	@Test
	public void findByDetail_test() throws Exception {
		// given
		Integer introId = 3;
		// when
		ResultActions resultActions = mvc
				.perform(get("/emp/companyIntroDetail/" + introId).accept(APPLICATION_JSON));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.introId").value(3));
	}

}
