package site.metacoding.miniproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.intro.IntroResDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroResDto.IntroUpdateReqDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroSaveRespDto;
import site.metacoding.miniproject.service.IntroService;

@RequiredArgsConstructor
@RestController
public class CompanyApiController {

    private final IntroService introService;

    @GetMapping("/cs/co/companyIntroDetail/{companyId}")
    public ResponseDto<?> findByCompanyId(@PathVariable Integer companyId) {// 기업에서 기업소개 상세보기
        return new ResponseDto<>(1, "성공", introService.findByCompanyId(companyId));
    }

    // @PostMapping("/api/cs/co/companyIntroInsert")
    // public ResponseDto<?> insertIntro(@RequestBody IntroSaveReqDto
    // introSaveReqDto) {
    // // SessionUser sessionUser = (SessionUser)
    // // session.getAttribute("sessionUser");
    // // introSaveReqDto.setSessionUser(sessionUser);
    // IntroSaveRespDto introSaveRespDto = introService.saveIntro(introSaveReqDto);
    // return new ResponseDto<>(1, "성공", introSaveRespDto);
    // }

    // @PutMapping("")
    // public ResponseDto<?> updateIntro(@PathVariable Integer introId, @RequestBody
    // IntroUpdateReqDto introUpdateReqDto) {
    // // SessionUser sessionUser = (SessionUser)
    // // session.getAttribute("sessionUser");
    // introUpdateReqDto.setIntroId(introId);
    // return new ResponseDto<>(1, "성공", introService.update(introUpdateReqDto));
    // }
}
