package site.metacoding.miniproject.domain.resume;

import java.util.List;

import site.metacoding.miniproject.dto.resume.ResumeInsertDto;

public interface ResumeDao {
    public List<Resume> findAll();

    public List<Resume> findByJobCodeToResume(Integer jobCode);

    public Resume findById(Integer resumeId);

    public List<Resume> findMatchingByJobId(Integer companyId);

    public List<Resume> findByEmployeeId(Integer employeeId);

    public void insert(ResumeInsertDto rid);

    public void deleteById(Integer resumeId);

    public void update(Resume resume);

    public void updateMain(Integer resumeId);
}
