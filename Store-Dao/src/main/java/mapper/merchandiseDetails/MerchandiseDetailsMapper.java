package mapper.merchandiseDetails;

import customPojo.Merchandise_Details;
import org.apache.ibatis.annotations.Mapper;
import pojo.MerchandiseDetails;

@Mapper
public interface MerchandiseDetailsMapper {
    /**
     * 添加信息
     * @param merchandiseDetails
     * @return
     */
    int insertInfo(MerchandiseDetails merchandiseDetails);

    /**
     * 根据商品id查询数据
     * @param merchandiseId
     * @return
     */
    Merchandise_Details selectMerchandise_DetailsById(String merchandiseId);
}
