package site.metacoding.miniproject.domain.check.company;

import org.apache.ibatis.annotations.Param;

public interface CoCheckDao {

    public void insert(@Param("companyId") Integer companyId, @Param("jobId") Integer jobId);

    public void deleteById(Integer companyId);

}
