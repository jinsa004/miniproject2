package site.metacoding.miniproject.domain.check.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.check.employee.EmpCheckRespDto;

public interface EmpCheckDao {

    public void insert(@Param("employeeId") Integer employeeId, @Param("jobId") Integer jobId);

    public List<EmpCheck> findCheckAll(Integer employeeId);

    public List<EmpCheckRespDto> findAll(Integer employeeId);

    public void deleteById(Integer employeeId);

}
