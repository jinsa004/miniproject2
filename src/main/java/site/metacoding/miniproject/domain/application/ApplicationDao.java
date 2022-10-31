package site.metacoding.miniproject.domain.application;

import java.util.List;

public interface ApplicationDao {
    public List<Application> findAll();

    public Application findById(Integer applicationId);

    public void insert(Application application);

    public void update(Integer applicationId, Application application);

    public void deleteById(Integer applicationId);
}
