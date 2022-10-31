package site.metacoding.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.application.Application;
import site.metacoding.miniproject.domain.application.ApplicationDao;
import site.metacoding.miniproject.domain.resume.Resume;
import site.metacoding.miniproject.domain.resume.ResumeDao;
import site.metacoding.miniproject.dto.resume.ResumeInsertDto;
import site.metacoding.miniproject.dto.resume.UpdateDto;

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

    public List<Resume> 이력서목록보기() {
        return resumeDao.findAll();
    }

    public List<Resume> 이력서분야별목록보기(Integer jobCode) {
        return resumeDao.findByJobCodeToResume(jobCode);
    }

    public List<Resume> 기업매칭리스트보기(Integer employeeId) {
        return resumeDao.findMatchingByJobId(employeeId);
    }

    public void 이력서작성(ResumeInsertDto rid) {
        resumeDao.insert(rid);
    }

    public Resume 이력서상세보기(Integer resumeId) {
        return resumeDao.findById(resumeId);
    }

    public void 이력서수정(Integer resumeId, UpdateDto updateDto) {
        Resume resumePS = resumeDao.findById(resumeId);
        resumePS.update(updateDto);
        resumeDao.update(resumePS);
    }

    public List<Resume> 내이력서가져오기(Integer employeeId) {
        List<Resume> resumePS = resumeDao.findByEmployeeId(employeeId);
        return resumePS;
    }

    public void 이력서삭제(Integer resumeId) {
        resumeDao.deleteById(resumeId);
    }
}
