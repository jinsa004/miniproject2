package site.metacoding.miniproject.domain.resume;

import java.util.List;

import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeSaveReqDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumeUpdateReqDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeAllRespDto;

public interface ResumeDao {
    public List<ResumeAllRespDto> findAll();

    public List<Resume> findByJobCodeToResume(Integer jobCode);

    public Resume findById(Integer resumeId);

    public List<Resume> findMatchingByJobId(Integer companyId);

    public List<Resume> findByEmployeeId(Integer employeeId);

    public void insert(ResumeSaveReqDto resumeSaveReqDto);

    public void deleteById(Integer resumeId);

    public void update(ResumeUpdateReqDto resumeUpdateReqDto);

    public void updateMain(Integer resumeId);
}
