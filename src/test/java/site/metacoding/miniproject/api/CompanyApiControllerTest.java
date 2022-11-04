package site.metacoding.miniproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.company.CompanyDao;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql")
@Transactional
// @AutoConfigureMockMvc
// @SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyApiControllerTest {

	// @Autowired
	// private MockMvc mvc;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private CompanyDao companyDao;

	// private MockHttpSession session;
}
