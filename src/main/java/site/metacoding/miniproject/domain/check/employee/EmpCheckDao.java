package site.metacoding.miniproject.domain.check.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmpCheckDao {

    public void insert(@Param("employeeId") Integer employeeId, @Param("jobId") Integer jobId);

    public List<EmpCheck> findAll(Integer employeeId);

    public void deleteById(Integer employeeId);

}
