package site.metacoding.miniproject.domain.subscribe;

import java.util.List;

public interface SubscribeDao {
    public List<Subscribe> findAll();

    public Subscribe findById(Integer subscribeId);

    public void insert(Subscribe subscribe);

    public void update(Integer subscribeId, Subscribe subscribe);

    public void deleteById(Integer subscribeId);
}
