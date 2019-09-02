package interfaces.burse;

import com.mysql.cj.jdbc.ha.BalanceStrategy;
import org.apache.ibatis.annotations.Param;
import pojo.Burse;

import java.util.List;

public interface BurseInterface {
    /**
     * 查询所有钱包信息
     *
     * @return
     */
    List<Burse> selectAll();

    /**
     * 根据burseId查询信息
     *
     * @param burseId
     * @return
     */
    Burse selectById(int burseId);

    /**
     * 添加信息
     *
     * @param burse
     * @return
     */
    int insertBurse(Burse burse);

    /**
     * 根据burseId删除信息
     *
     * @param burseId
     * @return
     */
    int deleteById(int burseId);

    /**
     * 根据burseId修改信息
     *
     * @param burse
     * @return
     */
    int updateById(Burse burse);

    /**
     * 根据手机号码查询
     * @param phone
     * @return
     */
    Burse selectByPhone(String phone);

    /**
     * 操作余额
     *
     * @param phone 手机号
     * @param money 金额
     * @param type  0 - 充值 / 1 - 消费
     * @return
     */
    int operationBalance(@Param("phone") String phone, @Param("money") double money, @Param("type") int type);
}
