package site.metacoding.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyDao companyDao;
  private final CoCheckDao coCheckDao;
  private final SHA256 sha256;

  public CompanySessionUser login(CompanyLoginReqDto companyLoginReqDto) {
    Company companyPS = companyDao.findByCompanyUsername(companyLoginReqDto.getCompanyUsername());
    if (companyPS != null &&
        companyPS.getCompanyPassword().equals(companyLoginReqDto.getCompanyPassword())) {
      return new CompanySessionUser(companyPS);
    }
    return null;
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
    List<CoCheckRespDto> coCheckList = coCheckDao.findByCompanyId(companyPS.getCompanyId());
    CompanyDetailRespDto companyDetailRespDto = new CompanyDetailRespDto(companyPS, coCheckList);
    return companyDetailRespDto;
  }

  public CompanyUpdateRespDto updateCompany(Integer companyId, CompanyUpdateReqDto companyUpdateReqDto) {
    // co_check 값 업데이트
    coCheckDao.deleteById(companyId);
    for (Integer jobId : companyUpdateReqDto.getJobIds()) {
      coCheckDao.insert(companyId, jobId);
    }
    List<CoCheckRespDto> jobCheckList = coCheckDao.findByCompanyId(companyId);

    // 회원정보 업데이트
    Company companyPS = companyDao.findById(companyId);
    companyPS.update(companyUpdateReqDto);
    companyDao.update(companyPS);
    return new CompanyUpdateRespDto(companyPS, jobCheckList);
  }

  public void deleteCompany(Integer companyId) {

    Company companyPS = companyDao.findById(companyId);
    if (companyPS != null) {
      companyDao.deleteById(companyId);
      coCheckDao.deleteById(companyId);
    } else {
      throw new RuntimeException("해당 " + companyId + "로 삭제를 할 수 없습니다.");
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
