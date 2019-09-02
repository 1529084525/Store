package service.collect;

import interfaces.collect.CollectInterface;
import mapper.collect.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Collect;

import java.util.List;

@Component
public class CollectService implements CollectInterface {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int insertCollect(Collect collect) {
        return collectMapper.insertCollect(collect);
    }

    @Override
    public int deleteById(int collectId) {
        return collectMapper.deleteById(collectId);
    }

    @Override
    public int updateById(Collect collect) {
        return collectMapper.updateById(collect);
    }

    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectAll();
    }
}
