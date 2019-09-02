package interfaces.commodity;

import customPojo.CommodityJson;
import customPojo.CommodityTreeJson;
import pojo.Commodity;

import java.util.List;

public interface CommodityInterface {
    //添加
    int insertCommodity(Commodity commodity);

    //删除根据id
    int deleteById(int commodityId);

    //修改根据id
    int updateById(Commodity commodity);

    /**
     * 查询所有 用于三级联动
     * @return
     */
    List<Commodity> selectAll(String commodityId);

    /**
     * 查询所有分类
     *
     * @param commodityId
     * @return
     */
    List<CommodityJson> selectAllByJson(String commodityId);

    /**
     * 获取树形数据
     * @param commodityId
     * @return
     */
    List<CommodityTreeJson> selectAllByTree(String commodityId);

    /**
     * 查询大分类
     * @return
     */
    List<Commodity> selectOne();

    /**
     * 根据分类随机查询
     * @param one
     * @return
     */
    List<Commodity> selectByOne(String one);

}
