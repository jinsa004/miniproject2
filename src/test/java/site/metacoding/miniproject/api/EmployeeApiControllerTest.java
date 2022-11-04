package site.metacoding.miniproject.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.employee.EmployeeDao;
import site.metacoding.miniproject.dto.employee.EmpReqDto.EmpJoinReqDto;

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

	// private MockHttpSession session;

	// ==================== 개인 화면 ====================== //

	@Test
	public void join_test() throws Exception {
		// given
		List<Integer> jobId = new ArrayList<>();
		jobId.add(1);
		jobId.add(2);
		EmpJoinReqDto empJoinReqDto = new EmpJoinReqDto();
		empJoinReqDto.setEmployeeName("웅우우");
		empJoinReqDto.setEmployeeBirth("1111-11-11");
		empJoinReqDto.setEmployeeSex("남");
		empJoinReqDto.setEmployeeUsername("ssar");
		empJoinReqDto.setEmployeePassword("1234");
		empJoinReqDto.setEmployeeEmail("ssar@naver.com");
		empJoinReqDto.setEmployeeLocation("부산");
		empJoinReqDto.setEmployeeTel("111-1111-1111");
		empJoinReqDto.setJobIds(jobId);

		String body = om.writeValueAsString(empJoinReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(post("/emp/join").content(body)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON));
		// then
		MvcResult mvcResult = resultActions.andReturn();
		System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.data.employeeUsername").value("ssar"));
	}

}
