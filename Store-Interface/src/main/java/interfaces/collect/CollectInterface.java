package interfaces.collect;

import pojo.Collect;

import java.util.List;

public interface CollectInterface {

    //添加
    int insertCollect(Collect collect);

    //删除根据id
    int deleteById(int collectId);

    //修改根据id
    int updateById(Collect collect);

    //查询所有
    List<Collect> selectAll();
}
