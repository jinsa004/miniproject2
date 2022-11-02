package site.metacoding.miniproject.api;

import org.springframework.ui.Model;
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
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyJoinRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.service.CompanyService;
import site.metacoding.miniproject.service.JobService;

@RequiredArgsConstructor
@RestController
public class CompanyApiController {

    private final CompanyService companyService;
    private final JobService jobService;

    @PostMapping("/co/join")
    public ResponseDto<?> companyJoin(@RequestBody CompanyJoinReqDto companyJoinReqDto) { // 기업 회원가입
        CompanyJoinRespDto companyJoinRespDtp = companyService.join(companyJoinReqDto);
        return new ResponseDto<>(1, "회원가입성공", companyJoinRespDtp);
    }

    @GetMapping("/co/company/detail/{companyId}")
    public ResponseDto<?> findByCompanyIdToCompanyDetail(@PathVariable Integer companyId, Model model) { // 기업회원정보보기
        CompanyDetailRespDto companyDetailRespDto = companyService.findByCompanyIdToCompanyDetail(companyId);
        // List<Job> jobPS = jobService.관심직무보기();
        // model.addAttribute("jobPS", jobPS);
        // Company companyPS = (Company) session.getAttribute("coprincipal");
        // model.addAttribute("company", companyPS);
        return new ResponseDto<>(1, "성공", companyDetailRespDto);
    }

    @PutMapping("/co/company/update/{companyId}")
    public ResponseDto<?> updateCompany(@PathVariable Integer companyId,
            @RequestBody CompanyUpdateReqDto companyUpdateReqDto) {
        CompanyUpdateRespDto companyUpdateRespDto = companyService.updateCompany(companyId, companyUpdateReqDto);
        // session.setAttribute("coprincipal", companyPS);
        return new ResponseDto<>(1, "수정성공", companyUpdateRespDto);
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

}
