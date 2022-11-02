package site.metacoding.miniproject.domain.intro;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.intro.IntroResDto.IntroSaveReqDto;

public interface IntroDao {
	public List<Intro> findAll();

	public Intro findById(Integer companyId);

	// public List<Intro> findByJobCodeToIntro(Integer jobCode);

	public void insert(IntroSaveReqDto introsSaveReqDto);

	public void update(Intro intro);

	public void deleteById(Integer introId);

	public Intro findByDetail(@Param("introId") Integer introId, @Param("principalId") Integer principalId);

}
