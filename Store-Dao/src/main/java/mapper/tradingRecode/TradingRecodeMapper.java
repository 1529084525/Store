package mapper.tradingRecode;

import customPojo.TradingRecodeByThree;
import org.apache.ibatis.annotations.Param;
import pojo.TradingRecode;

import java.util.List;

public interface TradingRecodeMapper {

    /**
     * 添加一条消息
     * @param tradingRecode
     * @return
     */
    int insertRecode(TradingRecode tradingRecode);

    /**
     * 根据手机号码查询
     * @param phone
     * @return
     */
    List<TradingRecode> selectByPhone(@Param("phone") String phone,@Param("page")int page);

    /**
     * 根据手机号多表链接查询
     * @param phone
     * @param page
     * @return
     */
    List<TradingRecodeByThree> selectAllByPhone(@Param("phone") String phone,@Param("page")int page);

    /**
     * 根据手机号码查询数量
     * @param phone
     * @return
     */
    int selectCountByPhone(String phone);
}
