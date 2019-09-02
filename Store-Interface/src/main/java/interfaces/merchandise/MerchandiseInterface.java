package interfaces.merchandise;

import customPojo.MerchandiseBySearch;
import org.apache.ibatis.annotations.Param;
import pojo.Merchandise;

import java.util.List;

public interface MerchandiseInterface {

    /**
     * 查询所有
     */
    List<Merchandise> selectAll();

    /**
     * 添加信息
     * @param merchandise
     * @return
     */
    int insertMerchandise(Merchandise merchandise);

    /**
     * 根据mId删除信息
     * @param mId
     * @return
     */
    int deleteById(int mId);

    /**
     * 根据mId修改信息
     * @param merchandise
     * @return
     */
    int upudateById(Merchandise merchandise);

    /**
     * 在发布商品时候验证该商品编号是否存在
     * @param merchandiseId
     * @return
     */
    int selectId(String merchandiseId);

    /**
     * 查询爆款前6条
     * @return
     */
    List<Merchandise> selectFaddish(int limit);

    /**
     * 根据分类查询
     * @param commodityId
     * @return
     */
    List<Merchandise> selectByIndex(String commodityId);

    /**
     * 查询商品总数
     * @return
     */
    int selectMerchandiseCount();

    /**
     * 分页+随机查询商品数据
     * 爆款数据
     * @return
     */
    List<Merchandise> selectHot(int start,int count);


    /**
     * 分页+销量最高查询商品数据
     * @param start
     * @param count
     * @return
     */
    List<Merchandise> selectHeightSellCount(int start,int count);


    /**
     * 分页+一口价最低 查询商品数据
     * @param start
     * @param count
     * @return
     */
    List<Merchandise> selectLowPrice(int start,int count);

    List<MerchandiseBySearch> selectBySearch(@Param("merchandiseCommodity")String merchandiseCommodity,
                                             @Param("merchandiseTitle")String merchandiseTitle,
                                             @Param("merchandiseBrandId")String merchandiseBrandId,
                                             @Param("merchandiseColor")String merchandiseColor,
                                             @Param("merchandiseSize")String merchandiseSize,
                                             @Param("page")int page);

    List<MerchandiseBySearch> selectBySearchCount(@Param("merchandiseCommodity")String merchandiseCommodity,
                            @Param("merchandiseTitle")String merchandiseTitle,
                            @Param("merchandiseBrandId")String merchandiseBrandId,
                            @Param("merchandiseColor")String merchandiseColor,
                            @Param("merchandiseSize")String merchandiseSize);
}
