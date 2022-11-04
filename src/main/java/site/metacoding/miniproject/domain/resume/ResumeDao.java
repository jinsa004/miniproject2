package site.metacoding.miniproject.domain.resume;

import java.util.List;

import site.metacoding.miniproject.dto.resume.ResumeRespDto.MatchingResumeRespDto;
import site.metacoding.miniproject.dto.resume.ResumeRespDto.ResumeAllRespDto;

public interface ResumeDao {
    public List<ResumeAllRespDto> findAll();

    public List<ResumeAllRespDto> findByJobCodeToResume(Integer jobCode);

    public Resume findById(Integer resumeId);

    public List<MatchingResumeRespDto> findMatchingByJobId(Integer companyId);

    public List<Resume> findByEmployeeId(Integer employeeId);

    public void insert(Resume resume);

    public void deleteById(Integer resumeId);

    public void update(Resume resume);

    public void updateMain(Integer resumeId);
}
