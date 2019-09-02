package interfaces.user;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

public interface UserInterface {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);


    /**
     * 删除用户根据id
     *
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 根据手机号获取密码
     *
     * @param phone
     * @return
     */
    User getPasswordByPhone(String phone);

    /**
     * 判断是否存在此手机号吗
     * @param phone
     * @return
     */
    int checkPhone(String phone);


    /**
     * 根据手机号码获取用户信息
     *
     * @param phone
     * @return
     */
    User selectUserByPhone(String phone);

    /**
     * 根据手机号修改部分信息
     * @param user
     * @return
     */
    int updateUserByPhone(User user);

    /**
     * 修改用户头像
     * @param phone
     * @param imageUrl
     * @return
     */
    int updatePhotoByPhone(@Param("phone") String phone, @Param("imageUrl") String imageUrl);

    /**
     * 充值密码
     *
     * @param user
     * @return
     */
    int resetPwd(User user);

    /**
     * 根据手机号码
     *
     * @param phone
     * @return
     */
    User selectPwdByPhone(String phone);

    /**
     * 根据手机号码查询用户头像
     * @param phone
     * @return
     */
    User selectPhotoByPhone(String phone);
}
