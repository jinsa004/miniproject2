package site.metacoding.miniproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.check.company.CoCheckDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.dto.company.CompanyJoinDto;
import site.metacoding.miniproject.dto.company.CompanyLoginDto;
import site.metacoding.miniproject.dto.company.CompanyUpdateDto;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyDao companyDao;
  private final CoCheckDao coCheckDao;

  public Company 로그인(CompanyLoginDto loginDto) {
    Company companyPS = companyDao.findByCompanyUsername(loginDto.getCompanyUsername());

    if (companyPS != null && companyPS.getCompanyPassword().equals(loginDto.getCompanyPassword())) {
      return companyPS;
    }
    return null;
  }

  @Transactional
  public void 회원가입(CompanyJoinDto companyJoinDto) {
    Company company = companyJoinDto.toEntity(companyJoinDto);
    companyDao.insert(company);

    for (Integer jobId : companyJoinDto.getJobIds()) {
      coCheckDao.insert(company.getCompanyId(), jobId);
    }
  }

  public Company 기업소개하나보기(Integer companyId) {
    return companyDao.findById(companyId);
  }

  public Company 기업회원정보수정(Integer companyId, CompanyUpdateDto companyUpdateDto) {
    // emp_check 값 업데이트
    coCheckDao.deleteById(companyId);
    for (Integer jobId : companyUpdateDto.getJobIds()) {
      coCheckDao.insert(companyId, jobId);
    }

    // 회원정보 업데이트
    Company companyPS = companyDao.findById(companyId);
    companyPS.update(companyUpdateDto);
    companyDao.update(companyPS);
    return companyPS;
  }

  public void 기업회원탈퇴(Integer companyId) {
    companyDao.deleteById(companyId);
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
