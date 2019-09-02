package service.user;

import interfaces.user.UserInterface;
import mapper.burse.BurseMapper;
import mapper.integral.IntegralMapper;
import mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.Burse;
import pojo.Integral;
import pojo.User;

import java.util.List;

@Component
public class UserService implements UserInterface {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BurseMapper burseMapper;
    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * 待增加
     *
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        //添加钱包  $$$
        burseMapper.insertBurse(new Burse(user.getUserPhone()));
        //添加积分
        integralMapper.insertIntegral(new Integral(user.getUserPhone(),0.0));
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User getPasswordByPhone(String phone) {
        return userMapper.getPasswordByPhone(phone);
    }

    @Override
    public int checkPhone(String phone) {
        return userMapper.checkPhone(phone);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public int updateUserByPhone(User user) {
        String userBirthday = userMapper.selectUserByPhone(user.getUserPhone()).getUserBirthday();
        /*
        如果用户已经保存过生日,则不能修改
         */
        if (userBirthday != null)
            user.setUserBirthday(userBirthday);

        return userMapper.updateUserByPhone(user);
    }

    @Override
    public int updatePhotoByPhone(String phone, String imageUrl) {
        return userMapper.updatePhotoByPhone(phone, imageUrl);
    }

    @Override
    public int resetPwd(User user) {
        return userMapper.resetPwd(user);
    }

    @Override
    public User selectPwdByPhone(String phone) {
        return userMapper.selectPwdByPhone(phone);
    }

    @Override
    public User selectPhotoByPhone(String phone) {
        return userMapper.selectPhotoByPhone(phone);
    }
}
