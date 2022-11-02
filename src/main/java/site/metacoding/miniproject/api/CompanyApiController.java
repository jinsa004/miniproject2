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

    // @GetMapping("/cs/co/companyInfo/{companyId}")
    // public @ResponseBody ResponseDto<?> 기업정보관리(@PathVariable In
    // eger companyId,
    // del) {
    //
    //

    // model.addAttribute("company", companyPS);
    //
    //

    // Mapping("/coapi/cs/co/companyUpdate/{companyId}")
    // public @ResponseBody ResponseDto<?> companyUpdate(@PathVariable Integer
    // anyId,
    //

    //
    // ion.setAttribute("coprincipal", companyPS);
    //

    //
    // eteMapping("/coapi/cs/co/companyDelete/{companyId}")
    // ResponseBody ResponseDto<?> companyDelete(@PathVariable Integer
    // anyId) {
    // anyService.기업회원탈퇴(companyId);
    // ion.invalidate();
    // r

    // @GetMapping("/co/logout")
    // public String Companylogout() {
    // session.invalidate();
    // return "redirect:/co";
    // }

}
