package site.metacoding.miniproject.domain.job;

import java.util.List;

public interface JobDao {
	public List<Job> findAll();

	public Job findById(Integer jobId);

	public void insert(Job job);

	public void update(Integer jobId, Job job);

	public void deleteById(Integer jobId);
}
