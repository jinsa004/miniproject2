package site.metacoding.miniproject.domain.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Job {
	private Integer jobId;
	private String jobName;
	private Integer jobCode;
}
