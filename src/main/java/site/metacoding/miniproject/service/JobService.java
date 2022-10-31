package site.metacoding.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.job.Job;
import site.metacoding.miniproject.domain.job.JobDao;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobDao jobDao;

    public List<Job> 관심직무보기() {
        return jobDao.findAll();
    }
}
