package site.metacoding.miniproject.domain.check.company;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.check.company.CoCheckRespDto;

public interface CoCheckDao {

    public void insert(@Param("companyId") Integer companyId, @Param("jobId") Integer jobId);

    public void deleteById(Integer companyId);

    public List<CoCheck> findCheckAll(Integer companyId);

    public List<CoCheckRespDto> findByCompanyId(Integer companyId);
}
