package site.metacoding.miniproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateRespDto;
import site.metacoding.miniproject.service.ResumeService;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {

	private final ResumeService resumeService;

	/* =============================개인회원========================================= */

	@GetMapping("/emp/resume/{resumeId}")
	public ResponseDto<?> findResumeById(@PathVariable Integer resumeId) {
		return new ResponseDto<>(1, "이력서 한건 불러오기 성공", resumeService.이력서상세보기(resumeId));
	}

	@PostMapping("/emp/resume/save")
	public ResponseDto<?> insertResume(@RequestBody ResumeSaveReqDto resumeSaveReqDto) {
		ResumeSaveRespDto resumeSaveRespDto = resumeService.이력서작성(resumeSaveReqDto);
		return new ResponseDto<>(1, "이력서 등록 성공", resumeSaveRespDto);
	}

	@PutMapping("/emp/resume/update/{resumeId}")
	public ResponseDto<?> updateResume(@PathVariable Integer resumeId,
			@RequestBody ResumeUpdateReqDto resumeUpdateReqDto) {
		resumeUpdateReqDto.setResumeId(resumeId);
		ResumeUpdateRespDto resumeUpdateRespDto = resumeService.이력서수정(resumeUpdateReqDto);
		return new ResponseDto<>(1, "이력서 수정 성공", resumeUpdateRespDto);
	}

}
