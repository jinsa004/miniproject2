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
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.dto.company.CompanySessionUser;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyDao companyDao;
  private final CoCheckDao coCheckDao;

  public CompanySessionUser login(CompanyLoginReqDto companyLoginReqDto) {
    Company companyPS = companyDao.findByCompanyUsername(companyLoginReqDto.getCompanyUsername());
    if (companyPS != null && companyPS.getCompanyPassword().equals(companyLoginReqDto.getCompanyPassword())) {
      return new CompanySessionUser(companyPS);
    }
    return null;
  }

  @Transactional
  public void join(CompanyJoinReqDto companyJoinReqDto) {
    Company companyPS = companyJoinReqDto.toEntity();
    companyDao.insert(companyPS);

    for (Integer jobId : companyJoinReqDto.getJobIds()) {
      coCheckDao.insert(companyPS.getCompanyId(), jobId);
    }
    // List<CoCheckRespDto> coCheckList =
    // coCheckDao.findAll(companyPS.getCompanyId());
  }

  // public CompanyDetailRespDto 기업소개하나보기(Integer companyId) {
  // Company companyPS = companyDao.findById(companyId);
  // CompanyDetailRespDto companyDetailRespDto = new
  // CompanyDetailRespDto(companyPS);
  // return companyDetailRespDto;
  // }

  public CompanyUpdateRespDto 기업회원정보수정(Integer companyId, CompanyUpdateReqDto companyUpdateReqDto) {
    // emp_check 값 업데이트
    coCheckDao.deleteById(companyId);
    for (Integer jobId : companyUpdateReqDto.getJobIds()) {
      coCheckDao.insert(companyId, jobId);
    }
    List<CoCheckRespDto> jobCheckList = coCheckDao.findAll(companyId);

    // 회원정보 업데이트
    Company companyPS = companyDao.findById(companyId);
    companyPS.update(companyUpdateReqDto);
    companyDao.update(companyPS);
    return new CompanyUpdateRespDto(companyPS, jobCheckList);
  }

  public void 기업회원탈퇴(Integer companyId) {
    companyDao.deleteById(companyId);
    coCheckDao.deleteById(companyId);
  }

  public boolean 회사유저네임중복확인(String companyUsername) {
    Company companyPS = companyDao.findByIdCompanyUsername(companyUsername);

    if (companyPS == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean 회사비밀번호2차체크(String companyPassword) {
    companyDao.findByCompanyPassword(companyPassword);
    return true;
  }

  public boolean 회사이메일형식체크(String companyEmail) {
    Company companyPS = companyDao.findByCompanyEmail(companyEmail);
    if (companyPS == null)
      return false;
    return true;
  }
}
