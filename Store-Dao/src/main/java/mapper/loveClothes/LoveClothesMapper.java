package mapper.loveClothes;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import pojo.LoveClothes;

public interface LoveClothesMapper {

    /**
     * 收藏商品
     * @param userPhone
     * @param merchandiseId
     * @return
     */
    int insertLove(String userPhone,String merchandiseId);

    /**
     * 根据手机号,商品id删除收藏商品
     * @param userPhone
     * @param merchandiseId
     * @return
     */
    int deleteLoveById(@Param("userPhone") String userPhone, @Param("merchandiseId")String merchandiseId);

    /**
     * 根据手机号,商品id查id 收藏表是否存在
     * @param userPhone
     * @param merchandiseId
     * @return
     */
    Object selectLove(@Param("userPhone") String userPhone, @Param("merchandiseId")String merchandiseId);

}
