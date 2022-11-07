package site.metacoding.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.application.Application;
import site.metacoding.miniproject.domain.application.ApplicationDao;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.resume.ResumeDao;
import site.metacoding.miniproject.dto.employee.EmpSessionUser;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ApplicationSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateMainReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ApplicationSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.MatchingResumeRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeAllRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeDetailRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeMyListRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateMainRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeDao resumeDao;
    private final ApplicationDao applicationDao;
    private final HttpSession session;

    @Transactional
    public ApplicationSaveRespDto 지원하기(ApplicationSaveReqDto applicationSaveReqDto) {
        Application applicationPS = applicationSaveReqDto.toEntity();
        applicationDao.insert(applicationPS);
        applicationPS = applicationDao.findById(applicationPS.getApplicationId());
        return new ApplicationSaveRespDto(applicationPS);
    }

    @Transactional
    public List<ResumeUpdateMainRespDto> 메인이력서등록(ResumeUpdateMainReqDto resumeUpdateMainReqDto) {
        Resume resumePS = resumeUpdateMainReqDto.toEntity();
        resumeDao.updateMain(resumePS.getResumeId());
        List<Resume> resumeList = resumeDao.findByEmployeeId(resumePS.getEmployeeId());
        List<ResumeUpdateMainRespDto> resumeUpdateMainRespDtoList = new ArrayList<>();
        for (Resume resume : resumeList) {
            resumeUpdateMainRespDtoList.add(new ResumeUpdateMainRespDto(resume));
        }

        return resumeUpdateMainRespDtoList;
    }

    public List<ResumeAllRespDto> findResumeAllList() {
        return resumeDao.findAll();
    }

    public List<ResumeAllRespDto> findByJobCodeToResumeList(Integer jobCode) {
        return resumeDao.findByJobCodeToResume(jobCode);
    }

    public List<MatchingResumeRespDto> findMachingResumeList(Integer employeeId) {
        return resumeDao.findMatchingByJobId(employeeId);
    }

    @Transactional
    public ResumeSaveRespDto 이력서작성(ResumeSaveReqDto resumeSaveReqDto) {
        Resume resumePS = resumeSaveReqDto.toEntity();
        resumeDao.insert(resumePS);
        log.debug("디버그 : " + resumePS.getResumeId());
        resumePS = resumeDao.findById(resumePS.getResumeId());
        return new ResumeSaveRespDto(resumePS);
    }

    @Transactional
    public ResumeDetailRespDto 이력서상세보기(Integer resumeId) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");

        Resume resumePS = resumeDao.findById(resumeId);

        if (resumePS == null) {
            throw new RuntimeException("해당" + resumeId + "가 없습니다.");
        }

        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("디버그1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("해당" + resumePS.getEmployeeId() + "를 볼 할 권한이 없습니다.");
        }

        return new ResumeDetailRespDto(resumePS);
    }

    @Transactional
    public ResumeUpdateRespDto 이력서수정(ResumeUpdateReqDto resumeUpdateReqDto) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");

        Resume resume = resumeUpdateReqDto.toEntity();
        Resume resumePS = resumeDao.findById(resume.getResumeId());

        if (resumePS == null) {
            throw new RuntimeException("해당" + resume.getResumeId() + "로 수정을 할 수 없습니다.");
        }

        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("디버그1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("해당" + resumePS.getEmployeeId() + "를 수정 할 권한이 없습니다.");
        }

        resumeDao.update(resume);
        return new ResumeUpdateRespDto(resume);

    }

    @Transactional
    public List<ResumeMyListRespDto> getMyResumeList(Integer employeeId) { // 메서드이름수정
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        if (empSessionUser.getEmployeeId() != employeeId) {
            log.debug("디버그1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("해당" + employeeId + "를 볼 권한이 없습니다.");
        }
        List<Resume> resumeList = resumeDao.findByEmployeeId(employeeId);
        List<ResumeMyListRespDto> resumeMyListRespDto = new ArrayList<>();
        for (Resume resume : resumeList) {
            resumeMyListRespDto.add(new ResumeMyListRespDto(resume));
        }
        return resumeMyListRespDto;
    }

    public void 이력서삭제(Integer resumeId) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        Resume resumePS = resumeDao.findById(resumeId);
        if (resumePS == null) {
            throw new RuntimeException("해당" + resumeId + "가 없습니다.");
        }
        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("디버그1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("해당" + resumeId + "를 삭제 할 권한이 없습니다.");
        }

        resumeDao.deleteById(resumeId);
    }
}