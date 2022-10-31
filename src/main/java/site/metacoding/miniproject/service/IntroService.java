package site.metacoding.miniproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.intro.Intro;
import site.metacoding.miniproject.domain.intro.IntroDao;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.domain.subscribe.SubscribeDao;
import site.metacoding.miniproject.dto.intro.DetailDto;
import site.metacoding.miniproject.dto.intro.IntroInsertDto;
import site.metacoding.miniproject.dto.intro.UpdateDto;

@RequiredArgsConstructor
@Service
public class IntroService {

    private final IntroDao introDao;
    private final SubscribeDao subscribeDao;
    private final HttpSession session;

    public Intro 마이페이지설정(Integer companyId) {// 기업이 보는 마이페이지
        Intro intro = introDao.findById(companyId);
        return intro;
    }

    public void 기업소개등록(IntroInsertDto introInsertDto) {
        introDao.insert(introInsertDto);
    }

    public List<Intro> 기업소개목록보기() {
        return introDao.findAll();
    }

    public List<Intro> 기업소개분야별목록보기(Integer jobCode) {
        return introDao.findByJobCodeToIntro(jobCode);
    }

    public DetailDto 기업소개상세보기(Integer companyId, Integer principalId) {// 개인이 보는 기업상세보기
        return introDao.findByDetail(companyId, principalId);
    }

    public Intro 기업소개상세보기(Integer companyId) {// 기업이 보는 마이페이지
        Intro intro = introDao.findById(companyId);
        return intro;
    }

    public void 기업소개수정하기(Integer companyId, UpdateDto updateDto) {
        Intro introPS = introDao.findById(companyId);
        introPS.Update(updateDto);
        System.out.println(introPS.getJobId());
        introDao.update(introPS);
    }

    public Subscribe 구독하기(Subscribe subscribe) {
        subscribeDao.insert(subscribe);
        return subscribe;
    }

    public void 구독취소하기(Integer subscribeId) {
        subscribeDao.deleteById(subscribeId);
    }
}
