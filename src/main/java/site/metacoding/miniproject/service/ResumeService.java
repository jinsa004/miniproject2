package site.metacoding.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.application.Application;
import site.metacoding.miniproject.domain.application.ApplicationDao;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.resume.ResumeDao;
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

    @Transactional
    public ApplicationSaveRespDto 지원하기(ApplicationSaveReqDto applicationSaveReqDto) {
        log.debug("디버그0 : " + 0);
        Application applicationPS = applicationSaveReqDto.toEntity();
        log.debug("디버그1 : " + 1);
        applicationDao.insert(applicationPS);
        log.debug("디버그2 : " + 2);
        applicationPS = applicationDao.findById(applicationPS.getApplicationId());
        log.debug("디버그3 : " + 3);
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
        Resume resumePS = resumeDao.findById(resumeId);
        if (resumePS != null) {
            return new ResumeDetailRespDto(resumePS);
        } else {
            throw new RuntimeException("해당" + resumeId + "가 없습니다.");
        }

    }

    @Transactional
    public ResumeUpdateRespDto 이력서수정(ResumeUpdateReqDto resumeUpdateReqDto) {
        Resume resume = resumeUpdateReqDto.toEntity();
        Resume resumePS = resumeDao.findById(resume.getResumeId());
        if (resumePS != null) {
            resumeDao.update(resume);
            return new ResumeUpdateRespDto(resume);
        } else {
            throw new RuntimeException("해당" + resume.getResumeId() + "로 수정을 할 수 없습니다.");
        }

    }

    @Transactional
    public List<ResumeMyListRespDto> 내이력서가져오기(Integer employeeId) {
        List<Resume> resumeList = resumeDao.findByEmployeeId(employeeId);
        List<ResumeMyListRespDto> resumeMyListRespDto = new ArrayList<>();
        for (Resume resume : resumeList) {
            resumeMyListRespDto.add(new ResumeMyListRespDto(resume));
        }
        return resumeMyListRespDto;
    }

    public void 이력서삭제(Integer resumeId) {
        Resume resumePS = resumeDao.findById(resumeId);
        if (resumePS != null) {
            resumeDao.deleteById(resumeId);
        } else {
            throw new RuntimeException("해당" + resumeId + "로 수정을 할 수 없습니다.");
        }
    }
}