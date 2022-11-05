package site.metacoding.miniproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.employee.EmployeeDao;

@ActiveProfiles("test")
// @Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EmployeeApiControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private EmployeeDao employeeDao;

	private MockHttpSession session;

	

}
