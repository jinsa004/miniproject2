package site.metacoding.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.application.Application;
import site.metacoding.miniproject.domain.application.ApplicationDao;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.resume.ResumeDao;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.MatchingResumeRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeAllRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeDetailRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeDao resumeDao;
    private final ApplicationDao applicationDao;

    public void 지원하기(Application application) {
        applicationDao.insert(application);
    }

    public void 메인이력서등록(Integer resumeId) {
        resumeDao.updateMain(resumeId);
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
        resumeDao.insert(resumeSaveReqDto);
        log.debug("디버그 : " + resumeSaveReqDto.getResumeId());
        Resume resumePS = resumeDao.findById(resumeSaveReqDto.getResumeId());
        ResumeSaveRespDto resumeSaveRespDto = new ResumeSaveRespDto(resumePS);
        return resumeSaveRespDto;
    }

    public ResumeDetailRespDto 이력서상세보기(Integer resumeId) {
        Resume resumePS = resumeDao.findById(resumeId);
        ResumeDetailRespDto resumeDetailRespDto = new ResumeDetailRespDto(resumePS);
        return resumeDetailRespDto;
    }

    @Transactional
    public ResumeUpdateRespDto 이력서수정(ResumeUpdateReqDto resumeUpdateReqDto) {
        resumeDao.update(resumeUpdateReqDto);
        Resume resumePS = resumeDao.findById(resumeUpdateReqDto.getResumeId());
        ResumeUpdateRespDto resumeUpdateRespDto = new ResumeUpdateRespDto(resumePS);
        return resumeUpdateRespDto;
    }

    public List<Resume> 내이력서가져오기(Integer employeeId) {
        List<Resume> resumePS = resumeDao.findByEmployeeId(employeeId);
        return resumePS;
    }

    public void 이력서삭제(Integer resumeId) {
        resumeDao.deleteById(resumeId);
    }
}