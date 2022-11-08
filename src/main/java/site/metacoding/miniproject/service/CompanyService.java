package site.metacoding.miniproject.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.check.company.CoCheckDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.dto.check.company.CoCheckRespDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinReqDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyLoginReqDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyJoinRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.util.SHA256;
import site.metacoding.miniproject.dto.company.CompanySessionUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyDao companyDao;
  private final CoCheckDao coCheckDao;
  private final SHA256 sha256;
  private final HttpSession session;

  @Transactional(readOnly = true)
  public CompanySessionUser 로그인(CompanyLoginReqDto companyLoginReqDto) {
    Company companyPS = companyDao.findByCompanyUsername(companyLoginReqDto.getCompanyUsername());
    String encPassword = sha256.encrypt(companyLoginReqDto.getCompanyPassword());
    if (companyPS != null &&
        companyPS.getCompanyPassword().equals(encPassword)) {
      return new CompanySessionUser(companyPS);
    } else {
      throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
    }
  }

  @Transactional
  public CompanyJoinRespDto join(CompanyJoinReqDto companyJoinReqDto) {
    String encPassword = sha256.encrypt(companyJoinReqDto.getCompanyPassword());
    companyJoinReqDto.setCompanyPassword(encPassword);

    Company companyPS = companyJoinReqDto.toEntity();
    companyDao.insert(companyPS);

    for (Integer jobId : companyJoinReqDto.getJobIds()) {
      coCheckDao.insert(companyPS.getCompanyId(), jobId);
    }

    List<CoCheckRespDto> coCheckList = coCheckDao.findByCompanyId(companyPS.getCompanyId());
    return new CompanyJoinRespDto(companyPS, coCheckList);
  }

  public CompanyDetailRespDto findByCompanyIdToCompanyDetail(Integer companyId) {
    Company companyPS = companyDao.findById(companyId);
    // List<CoCheckRespDto> coCheckList =
    // coCheckDao.findByCompanyId(companyPS.getCompanyId());
    // CompanyDetailRespDto companyDetailRespDto = new
    // CompanyDetailRespDto(companyPS, coCheckList);
    // return companyDetailRespDto;
    if (companyPS != null) {
      List<CoCheckRespDto> coCheckList = coCheckDao.findByCompanyId(companyPS.getCompanyId());
      CompanyDetailRespDto companyDetailRespDto = new CompanyDetailRespDto(companyPS, coCheckList);
      return companyDetailRespDto;
    } else {
      throw new RuntimeException("해당 " + companyId + "로 상세보기를 할 수 없습니다.");
    }

  }

  public CompanyUpdateRespDto updateCompany(Integer companyId, CompanyUpdateReqDto companyUpdateReqDto) {
    Company companyPS = companyDao.findById(companyId);
    if (companyPS == null) {
      throw new RuntimeException("해당 " + companyId + "로 수정할 수 없습니다.");
    }

    CompanySessionUser coPrincipal = (CompanySessionUser) session.getAttribute("companySessionUser");
    System.out.println("디버그 세션 : " + coPrincipal.getCompanyId());
    if (coPrincipal.getCompanyId().equals(companyPS.getCompanyId())) {
      // co_check 값 업데이트
      coCheckDao.deleteById(companyId);
      for (Integer jobId : companyUpdateReqDto.getJobIds()) {
        coCheckDao.insert(companyId, jobId);
      }
      List<CoCheckRespDto> jobCheckList = coCheckDao.findByCompanyId(companyId);

      // 회원정보 업데이트
      companyPS.update(companyUpdateReqDto);
      companyDao.update(companyPS);
      return new CompanyUpdateRespDto(companyPS, jobCheckList);

    } else {
      throw new RuntimeException("해당 게시글을 수정할 권한이 없습니다.");
    }

  }

  public void deleteCompany(Integer companyId) {
    Company companyPS = companyDao.findById(companyId);
    log.debug("디버그 : service ");
    if (companyPS == null) {
      throw new RuntimeException("해당 " + companyId + "로 삭제를 할 수 없습니다.");
    }
    CompanySessionUser companySessionUser = (CompanySessionUser) session.getAttribute("companySessionUser");
    System.out.println("디버그 세션 : " + companySessionUser.getCompanyId());
    if (companySessionUser.getCompanyId().equals(companyPS.getCompanyId())) {
      companyDao.deleteById(companyPS.getCompanyId());
      coCheckDao.deleteById(companyPS.getCompanyId());
    } else {
      throw new RuntimeException("해당 게시글을 지울 권한이 없습니다.");
    }
  }

  public boolean usernameSameCheck(String companyUsername) {
    Company companyPS = companyDao.findByIdCompanyUsername(companyUsername);
    if (companyPS == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean passwordCheck(String companyPassword, String companyPasswordSame) {
    // companyDao.findByCompanyPassword(companyPassword);
    System.out.println("값1=" + companyPassword);
    System.out.println("값2=" + companyPasswordSame);
    if (companyPassword.equals(companyPasswordSame)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean emailCheck(String companyEmail) {
    Company companyPS = companyDao.findByCompanyEmail(companyEmail);
    if (companyPS == null)
      return false;
    return true;
  }
}
