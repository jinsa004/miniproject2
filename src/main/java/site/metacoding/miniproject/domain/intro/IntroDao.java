package site.metacoding.miniproject.domain.intro;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;

public interface IntroDao {
	public List<Intro> findAll();

	public Intro findByCompanyId(Integer companyId);

	public Intro findByIntroId(Integer introId);

	// public List<Intro> findByJobCodeToIntro(Integer jobCode);

	public void insert(IntroSaveReqDto introSaveReqDto);

	public void update(IntroUpdateReqDto introUpdateReqDto);

	public void deleteById(Integer introId);

	public Intro findByDetail(@Param("introId") Integer introId, @Param("principalId") Integer principalId);

}
