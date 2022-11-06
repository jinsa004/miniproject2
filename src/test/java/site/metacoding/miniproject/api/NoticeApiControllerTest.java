package site.metacoding.miniproject.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import site.metacoding.miniproject.domain.notice.Notice;
import site.metacoding.miniproject.domain.notice.NoticeDao;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeSaveReqDto;
import site.metacoding.miniproject.dto.notice.NoticeReqDto.NoticeUpdateReqDto;

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
		Integer noticeId = 1;
		Notice noticePS = noticeDao.findById(noticeId);
		NoticeUpdateReqDto noticeUpdateReqDto = new NoticeUpdateReqDto();
		noticeUpdateReqDto.setNoticeId(noticeId);
		noticeUpdateReqDto.setNoticeTitle(noticePS.getNoticeTitle());
		noticeUpdateReqDto.setNoticePeriod(noticePS.getNoticePeriod());
		noticeUpdateReqDto.setNoticePosition(noticePS.getNoticePosition());
		noticeUpdateReqDto.setNoticeTask(noticePS.getNoticeTask());
		noticeUpdateReqDto.setNoticeSal(noticePS.getNoticeSal());
		noticeUpdateReqDto.setJobId(noticePS.getJobId());

		String body = om.writeValueAsString(noticeUpdateReqDto);

		// when
		ResultActions resultActions = mvc
				.perform(put("/cs/co/notice/update/" + noticeUpdateReqDto.getNoticeId()).content(body)
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
		Integer noticeId = 1;

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
