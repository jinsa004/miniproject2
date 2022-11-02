package site.metacoding.miniproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.service.IntroService;

@RequiredArgsConstructor
@RestController
public class EmployeeApiController {

    private final IntroService introService;

    // 개인이 보는기업소개 상세보기
    @GetMapping("/emp/companyIntroDetail/{introId}")
    public ResponseDto<?> findByDetail(@PathVariable Integer introId, Integer principalId) {
        return new ResponseDto<>(1, "성공", introService.findByDetail(introId, principalId));
    }

    @GetMapping("/emp/companyList")
    public ResponseDto<?> findAll() {
        return new ResponseDto<>(1, "성공", introService.findAll());
    }

}
