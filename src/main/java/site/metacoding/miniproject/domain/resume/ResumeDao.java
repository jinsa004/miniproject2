package site.metacoding.miniproject.domain.resume;

import java.util.List;

import site.metacoding.miniproject.dto.resume.ResumeRespDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeSaveRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeUpdateRespDto;

public interface ResumeDao {
    public List<Resume> findAll();

    public List<Resume> findByJobCodeToResume(Integer jobCode);

    public Resume findById(Integer resumeId);

    public List<Resume> findMatchingByJobId(Integer companyId);

    public List<Resume> findByEmployeeId(Integer employeeId);

    public void insert(ResumeSaveReqDto resumesSaveReqDto);

    public void deleteById(Integer resumeId);

    public void update(ResumeUpdateReqDto resumeUpdateReqDto);

    public void updateMain(Integer resumeId);
}
