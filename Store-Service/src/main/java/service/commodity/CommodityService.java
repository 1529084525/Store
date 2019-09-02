package service.commodity;

import customPojo.CommodityJson;
import customPojo.CommodityTreeJson;
import interfaces.commodity.CommodityInterface;
import mapper.commodity.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Commodity;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommodityService implements CommodityInterface {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public int insertCommodity(Commodity commodity) {
        return commodityMapper.insertCommodity(commodity);
    }

    @Override
    public int deleteById(int commodityId) {
        return commodityMapper.deleteById(commodityId);
    }

    @Override
    public int updateById(Commodity commodity) {
        return commodityMapper.updateById(commodity);
    }

    @Override
    public List<Commodity> selectAll(String commodityId) {
        return commodityMapper.selectAll(commodityId);
    }

    @Override
    public List<CommodityJson> selectAllByJson(String commodityId) {
        List<CommodityJson> commodityJsons = commodityMapper.selectAllByJson(commodityId);
        for (int i = 0, len = commodityJsons.size(); i < len; i++) {
            List<CommodityJson> commodityJsons1 = commodityMapper.selectAllByJson(commodityJsons.get(i).getCommodityId());
            commodityJsons.get(i).setCommodityList(commodityJsons1);
            for (int j = 0, len1 = commodityJsons1.size(); j < len1; j++) {
                commodityJsons.get(i).getCommodityList().get(j).setCommodityList(commodityMapper.selectAllByJson(commodityJsons1.get(j).getCommodityId()));
            }
        }
        return commodityJsons;
    }

    @Override
    public List<CommodityTreeJson> selectAllByTree(String commodityId) {
        List<CommodityTreeJson> commodityTreeJsons = commodityMapper.selectAllByTree("");
        for (int i = 0, len = commodityTreeJsons.size(); i < len; i++) {
            List<CommodityTreeJson> commodityJsons1 = commodityMapper.selectAllByTree(commodityTreeJsons.get(i).getId());
            commodityTreeJsons.get(i).setChildren(commodityJsons1);
            for (int j = 0, len1 = commodityJsons1.size(); j < len1; j++) {
                commodityTreeJsons.get(i).getChildren().get(j).setChildren(commodityMapper.selectAllByTree(commodityJsons1.get(j).getId()));
            }
        }
        return commodityTreeJsons;
    }

    @Override
    public List<Commodity> selectOne() {
        return commodityMapper.selectOne();
    }

    @Override
    public List<Commodity> selectByOne(String one) {
        return commodityMapper.selectByOne(one);
    }
}
