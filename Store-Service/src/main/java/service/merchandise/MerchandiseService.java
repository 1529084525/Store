package service.merchandise;

import customPojo.MerchandiseBySearch;
import interfaces.merchandise.MerchandiseInterface;
import mapper.merchandise.MerchandiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Merchandise;

import java.util.List;

@Component
public class MerchandiseService implements MerchandiseInterface {
    @Autowired
    private MerchandiseMapper merchandiseMapper;

    @Override
    public List<Merchandise> selectAll() {
        return merchandiseMapper.selectAll();
    }

    @Override
    public int insertMerchandise(Merchandise merchandise) {
        return merchandiseMapper.insertMerchandise(merchandise);
    }

    @Override
    public int deleteById(int mId) {
        return merchandiseMapper.deleteById(mId);
    }

    @Override
    public int upudateById(Merchandise merchandise) {
        return merchandiseMapper.upudateById(merchandise);
    }

    @Override
    public List<Merchandise> selectFaddish(int limit) {
        return merchandiseMapper.selectFaddish(limit);
    }

    @Override
    public int selectId(String merchandiseId) {
        return merchandiseMapper.selectId(merchandiseId);
    }

    @Override
    public List<Merchandise> selectByIndex(String commodityId) {
        return merchandiseMapper.selectByIndex(commodityId);
    }

    @Override
    public int selectMerchandiseCount() {
        return merchandiseMapper.selectMerchandiseCount();
    }

    @Override
    public List<Merchandise> selectHot(int start, int count) {
        start = (start - 1) * 20;
        return merchandiseMapper.selectHot(start, count);
    }

    @Override
    public List<Merchandise> selectHeightSellCount(int start, int count) {
        start = (start - 1) * 20;
        return merchandiseMapper.selectHeightSellCount(start, count);
    }

    @Override
    public List<Merchandise> selectLowPrice(int start, int count) {
        start = (start - 1) * 20;
        return merchandiseMapper.selectLowPrice(start, count);
    }

    @Override
    public List<MerchandiseBySearch> selectBySearch(String merchandiseCommodity,
                                                    String merchandiseTitle, String merchandiseBrandId,
                                                    String merchandiseColor, String merchandiseSize,
                                                    int page) {
        return merchandiseMapper.selectBySearch(
                merchandiseCommodity,
                merchandiseTitle,
                merchandiseBrandId,
                merchandiseColor,
                merchandiseSize,
                page);
    }

    @Override
    public List<MerchandiseBySearch> selectBySearchCount(String merchandiseCommodity, String merchandiseTitle, String merchandiseBrandId, String merchandiseColor, String merchandiseSize) {
        return merchandiseMapper.selectBySearchCount(merchandiseCommodity, merchandiseTitle, merchandiseBrandId, merchandiseColor, merchandiseSize);
    }
}
