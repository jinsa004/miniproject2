package site.metacoding.miniproject.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinReqDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroSaveRespDto;
import site.metacoding.miniproject.service.CompanyService;
import site.metacoding.miniproject.service.IntroService;
import site.metacoding.miniproject.service.JobService;

@RequiredArgsConstructor
@RestController
public class CompanyApiController {

    private final IntroService introService;
    private final CompanyService companyService;
    private final JobService jobService;

    @PostMapping("/co/join")
    public ResponseDto<?> companyJoin(@RequestBody CompanyJoinReqDto companyJoinReqDto) { // 기업 회원가입
        return new ResponseDto<>(1, "회원가입성공", companyService.join(companyJoinReqDto));
    }

    @GetMapping("/cs/co/company/detail/{companyId}")
    public ResponseDto<?> findByCompanyIdToCompanyDetail(@PathVariable Integer companyId) { // 기업회원정보보기
        // Company companyPS = (Company) session.getAttribute("coprincipal");
        return new ResponseDto<>(1, "성공", companyService.findByCompanyIdToCompanyDetail(companyId));
    }

    @PutMapping("/cs/co/company/update/{companyId}")
    public ResponseDto<?> updateCompany(@PathVariable Integer companyId,
            @RequestBody CompanyUpdateReqDto companyUpdateReqDto) {
        CompanyUpdateRespDto companyUpdateRespDto = companyService.updateCompany(companyId, companyUpdateReqDto);
        // session.setAttribute("coprincipal", companyPS);
        return new ResponseDto<>(1, "수정성공", companyUpdateRespDto);
    }

    @DeleteMapping("/cs/co/company/delete/{companyId}")
    public ResponseDto<?> deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
        // session.invalidate();
        return new ResponseDto<>(1, "기업탈퇴성공", null);
    }

    @GetMapping("/cs/co/companyIntroDetail/{companyId}")
    public ResponseDto<?> findByCompanyId(@PathVariable Integer companyId) {// 기업에서 기업소개 상세보기
        return new ResponseDto<>(1, "성공", introService.findByCompanyId(companyId));
    }

    @GetMapping("/co/logout")
    public ResponseDto<?> Companylogout() {
        // session.invalidate();
        return new ResponseDto<>(1, "로그아웃", null);
    }

    // ================= 유효성 체크 ================
    @GetMapping("/co/usernameSameCheck")
    public ResponseDto<?> usernameSameCheck(String companyUsername) {
        boolean isSame = companyService.usernameSameCheck(companyUsername);
        if (isSame == true) {
            return new ResponseDto<Boolean>(-1, "실패", isSame);
        }
        return new ResponseDto<>(1, "성공", isSame);
    }

    @GetMapping("/co/passwordCheck")
    public ResponseDto<Boolean> passwordCheck(String companyPassword, String companyPasswordSame) {
        boolean isSame = companyService.passwordCheck(companyPassword, companyPasswordSame);
        if (isSame == false) {
            return new ResponseDto<Boolean>(-1, "실패", isSame);
        }
        return new ResponseDto<>(1, "성공", isSame);
    }

    @GetMapping("/co/emailCheck")
    public ResponseDto<Boolean> emailCheck(String companyEmail) {
        boolean isSame = companyService.emailCheck(companyEmail);
        if (isSame == true) {
            return new ResponseDto<Boolean>(-1, "실패", isSame);
        }
        return new ResponseDto<>(1, "성공", isSame);
    }

    @PostMapping("/cs/co/intro/insert")
    public ResponseDto<?> insertIntro(@RequestBody IntroSaveReqDto introSaveReqDto) {
        // SessionUser sessionUser = (SessionUser)
        // session.getAttribute("sessionUser");
        // introSaveReqDto.setSessionUser(sessionUser);
        Integer companyId = 1;
        introSaveReqDto.setCompanyId(companyId);
        IntroSaveRespDto introSaveRespDto = introService.saveIntro(introSaveReqDto);
        return new ResponseDto<>(1, "성공", introSaveRespDto);
    }

    @PutMapping("/cs/co/intro/update/{introId}")
    public ResponseDto<?> updateIntro(@PathVariable Integer introId, @RequestBody IntroUpdateReqDto introUpdateReqDto) {
        // SessionUser sessionUser = (SessionUser)
        // session.getAttribute("sessionUser");
        introUpdateReqDto.setIntroId(introId);
        return new ResponseDto<>(1, "성공", introService.update(introUpdateReqDto));
    }

}
