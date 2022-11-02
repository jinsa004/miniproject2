package site.metacoding.miniproject.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinReqDto;
import site.metacoding.miniproject.service.CompanyService;
import site.metacoding.miniproject.service.IntroService;
import site.metacoding.miniproject.service.JobService;

@RequiredArgsConstructor
@RestController
public class CompanyApiController {

    private final CompanyService companyService;

    @PostMapping("/co/join")
    public ResponseDto<?> companyJoin(@RequestBody CompanyJoinReqDto companyJoinReqDto) {
        companyService.join(companyJoinReqDto);
        return new ResponseDto<>(1, "회원가입성공", null);
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
