package site.metacoding.miniproject.domain.subscribe;

import java.util.List;

import site.metacoding.miniproject.dto.subscribe.SubscribeReqDto.SubscribeSaveReqDto;

public interface SubscribeDao {
    public List<Subscribe> findAll();

    public Subscribe findById(Integer subscribeId);

    public void insert(SubscribeSaveReqDto subscribeSaveReqDto);

    public void update(Integer subscribeId, Subscribe subscribe);

    public void deleteById(Integer subscribeId);
}
