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
    public ApplicationSaveRespDto applicateByResumeId(ApplicationSaveReqDto applicationSaveReqDto) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        Resume resumePS = resumeDao.findById(applicationSaveReqDto.getResumeId());

        if (resumePS.getEmployeeId() != empSessionUser.getEmployeeId()) {
            throw new RuntimeException("??????" + applicationSaveReqDto.getResumeId() + "??? ????????? ??? ??? ????????????.");
        }

        Application applicationPS = applicationSaveReqDto.toEntity();
        applicationDao.insert(applicationPS);
        applicationPS = applicationDao.findById(applicationPS.getApplicationId());
        return new ApplicationSaveRespDto(applicationPS);
    }

    @Transactional
    public List<ResumeUpdateMainRespDto> setMainResume(ResumeUpdateMainReqDto resumeUpdateMainReqDto) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        Resume resumePS1 = resumeDao.findById(resumeUpdateMainReqDto.getResumeId());

        if (resumePS1 == null || resumePS1.getEmployeeId() != empSessionUser.getEmployeeId()) {
            throw new RuntimeException("??????" + resumeUpdateMainReqDto.getResumeId() + "??? ????????? ??? ??? ????????????.");
        }

        Resume resumePS2 = resumeUpdateMainReqDto.toEntity();

        resumeDao.updateMain(resumePS2.getResumeId());

        List<Resume> resumeList = resumeDao.findByEmployeeId(resumePS2.getEmployeeId());
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
    public ResumeSaveRespDto insertResume(ResumeSaveReqDto resumeSaveReqDto) {

        Resume resumePS = resumeSaveReqDto.toEntity();
        resumeDao.insert(resumePS);
        log.debug("????????? : " + resumePS.getResumeId());
        resumePS = resumeDao.findById(resumePS.getResumeId());
        return new ResumeSaveRespDto(resumePS);
    }

    @Transactional
    public ResumeDetailRespDto empFindById(Integer resumeId) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");

        Resume resumePS = resumeDao.findById(resumeId);

        if (resumePS == null) {
            throw new RuntimeException("??????" + resumeId + "??? ????????????.");
        }

        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("?????????1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("??????" + resumePS.getEmployeeId() + "??? ??? ??? ????????? ????????????.");
        }

        return new ResumeDetailRespDto(resumePS);
    }

    @Transactional
    public ResumeDetailRespDto coFindById(Integer resumeId) {
        Resume resumePS = resumeDao.findById(resumeId);

        if (resumePS == null) {
            throw new RuntimeException("??????" + resumeId + "??? ????????????.");
        }

        return new ResumeDetailRespDto(resumePS);
    }

    @Transactional
    public ResumeUpdateRespDto updateResume(ResumeUpdateReqDto resumeUpdateReqDto) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");

        Resume resume = resumeUpdateReqDto.toEntity();
        Resume resumePS = resumeDao.findById(resume.getResumeId());

        if (resumePS == null) {
            throw new RuntimeException("??????" + resume.getResumeId() + "??? ????????? ??? ??? ????????????.");
        }

        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("?????????1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("??????" + resumePS.getEmployeeId() + "??? ?????? ??? ????????? ????????????.");
        }

        resumeDao.update(resume);
        return new ResumeUpdateRespDto(resume);

    }

    @Transactional
    public List<ResumeMyListRespDto> getMyResumeList(Integer employeeId) { // ?????????????????????
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        if (empSessionUser.getEmployeeId() != employeeId) {
            log.debug("?????????1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("??????" + employeeId + "??? ??? ????????? ????????????.");
        }
        List<Resume> resumeList = resumeDao.findByEmployeeId(employeeId);
        List<ResumeMyListRespDto> resumeMyListRespDto = new ArrayList<>();
        for (Resume resume : resumeList) {
            resumeMyListRespDto.add(new ResumeMyListRespDto(resume));
        }
        return resumeMyListRespDto;
    }

    public void deleteResume(Integer resumeId) {
        EmpSessionUser empSessionUser = (EmpSessionUser) session.getAttribute("empSessionUser");
        Resume resumePS = resumeDao.findById(resumeId);
        if (resumePS == null) {
            throw new RuntimeException("??????" + resumeId + "??? ????????????.");
        }
        if (empSessionUser.getEmployeeId() != resumePS.getEmployeeId()) {
            log.debug("?????????1 : " + empSessionUser.getEmployeeId());
            throw new RuntimeException("??????" + resumeId + "??? ?????? ??? ????????? ????????????.");
        }

        resumeDao.deleteById(resumeId);
    }
}