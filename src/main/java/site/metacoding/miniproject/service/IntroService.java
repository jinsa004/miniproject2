package site.metacoding.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.intro.Intro;
import site.metacoding.miniproject.domain.intro.IntroDao;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.domain.subscribe.SubscribeDao;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroAllRespDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroFindByCompanyIdRespDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroFindByDetailRespDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroSaveRespDto;
import site.metacoding.miniproject.dto.intro.IntroRespDto.IntroUpdateRespDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto.SubscribeSaveReqDto;
import site.metacoding.miniproject.dto.subscribe.SubscribeRespDto.SubscribeSaveRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class IntroService {

    private final IntroDao introDao;
    private final SubscribeDao subscribeDao;
    private final HttpSession session;

    @Transactional(readOnly = true)
    public List<IntroAllRespDto> findAll() { // intro 목록보기
        List<Intro> introList = introDao.findAll();
        List<IntroAllRespDto> introAllRespDtosList = new ArrayList<>();
        for (Intro intro : introList) {
            introAllRespDtosList.add(new IntroAllRespDto(intro));
        }
        return introAllRespDtosList;
    }

    @Transactional(readOnly = true)
    public IntroFindByCompanyIdRespDto findByCompanyId(Integer companyId) { // 기업입장에서 기업소개 목록보기
        Intro introPS = introDao.findByCompanyId(companyId);
        IntroFindByCompanyIdRespDto introFindByCompanyIdRespDto = new IntroFindByCompanyIdRespDto(introPS);
        return introFindByCompanyIdRespDto;
    }

    @Transactional(readOnly = true)
    public IntroFindByDetailRespDto findByDetail(Integer introId, Integer principalId) {
        Intro introPS = introDao.findByDetail(introId, principalId);
        IntroFindByDetailRespDto introFindByDetailRespDto = new IntroFindByDetailRespDto(introPS);
        return introFindByDetailRespDto;
    }

    @Transactional
    public IntroSaveRespDto saveIntro(IntroSaveReqDto introSaveReqDto) {
        introDao.insert(introSaveReqDto);
        log.debug("디버그 : " + introSaveReqDto.getCompanyId());
        Intro introPS = introDao.findByIntroId(introSaveReqDto.getIntroId());
        IntroSaveRespDto introSaveRespDto = new IntroSaveRespDto(introPS);
        return introSaveRespDto;
    }


    @Transactional
    // 기업소개 업데이트
    public IntroUpdateRespDto update(IntroUpdateReqDto introUpdateReqDto) {
        introDao.update(introUpdateReqDto);
        Intro introPS = introDao.findByIntroId(introUpdateReqDto.getIntroId());
        IntroUpdateRespDto introUpdateRespDto = new IntroUpdateRespDto(introPS);
        return introUpdateRespDto;

    // public SubscribeSaveRespDto 구독하기(SubscribeSaveReqDto subscribeSaveReqDto) {
    // Subscribe subscribePS = subscribeDao.insert(subscribeSaveReqDto.toEntity());
    // SubscribeSaveRespDto subscribeSaveRespDto = new
    // SubscribeSaveRespDto(subscribePS);
    // return subscribeSaveRespDto;
    // }

    public SubscribeSaveRespDto 구독하기(SubscribeSaveReqDto subscribeSaveReqDto) {
        subscribeDao.insert(subscribeSaveReqDto);
        log.debug("디버그 : " + subscribeSaveReqDto.getSubscribeId());
        Subscribe subscribePS = subscribeDao.findById(subscribeSaveReqDto.getSubscribeId());
        SubscribeSaveRespDto subscribeSaveRespDto = new SubscribeSaveRespDto(subscribePS);
        return subscribeSaveRespDto;
    }

    public void 구독취소하기(Integer subscribeId) {
        subscribeDao.deleteById(subscribeId);
    }

    // public Intro 마이페이지설정(Integer companyId) {// 기업이 보는 마이페이지
    // Intro intro = introDao.findById(companyId);
    // return intro;
    // }

    // public void 기업소개등록(IntroInsertDto introInsertDto) {
    // introDao.insert(introInsertDto);
    // }

    // public List<Intro> 기업소개목록보기() {
    // return introDao.findAll();
    // }

    // public List<Intro> 기업소개분야별목록보기(Integer jobCode) {
    // return introDao.findByJobCodeToIntro(jobCode);
    // }

    // public DetailDto 기업소개상세보기(Integer companyId, Integer principalId) {// 개인이 보는
    // 기업상세보기
    // return introDao.findByDetail(companyId, principalId);
    // }

    // public Intro 기업소개상세보기(Integer companyId) {// 기업이 보는 마이페이지
    // Intro intro = introDao.findById(companyId);
    // return intro;
    // }

    // public void 기업소개수정하기(Integer companyId, UpdateDto updateDto) {
    // Intro introPS = introDao.findById(companyId);
    // // introPS.Update(updateDto);
    // System.out.println(introPS.getJobId());
    // introDao.update(introPS);
    // }
}