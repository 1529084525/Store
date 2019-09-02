package com.xzx.hanfu.store_controller.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import customPojo.MerchandiseShoppingCar;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.MerchandiseSKU;
import pojo.ShoppingCar;
import pojo.User;
import service.burse.BurseService;
import service.grade.GradeService;
import service.merchandiseSKU.MerchandiseSKUService;
import service.shoppingCar.ShoppingCarService;
import service.user.UserService;
import springfox.documentation.annotations.ApiIgnore;
import util.*;
import util.system.SystemUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "User", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private BurseService burseService;
    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private MerchandiseSKUService merchandiseSKUService;

    @ApiOperation(notes = "用来查看所有用户信息", value = "查询用户")
    @GetMapping("SelectAll")
    public Object selectAll() {
        return userService.selectAll();
    }

    /**
     * 发送验证码
     *
     * @return
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("SendCode/{phone}")
    public Object sendCode(@PathVariable String phone) {
        //获取验证码
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        try {
            String messages = SignAliUtil.getMessages(phone, random);
            //存redis - 有效期2分钟
            stringRedisTemplate.opsForValue().set(phone, random + "", 2, TimeUnit.MINUTES);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    /**
     * 发送验证码
     *
     * @return
     */
    @ApiOperation(value = "获取验证码(根据手动给时间)")
    @GetMapping("SendCodeByTime/{phone}/{time}")
    public Object sendCodeByTime(@PathVariable String phone, @PathVariable int time) {
        //获取验证码
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        try {
            String messages = SignAliUtil.getMessages(phone, random);
            //存redis - 有效期2分钟
            stringRedisTemplate.opsForValue().set(phone + "_payment", random + "", time, TimeUnit.MINUTES);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    @ApiOperation("判断验证码是否正确(根据redis)")
    @GetMapping("CheckCodeByMsg/{phone}/{code}/{msg}")
    public Object checkCodeByMsg(@PathVariable String phone, @PathVariable String code, @PathVariable String msg) {
        String cCode = stringRedisTemplate.opsForValue().get(phone + "_" + msg);
        if (cCode == null) return "no";
        if (cCode.equals(code)) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation("判断验证码是否正确")
    @GetMapping("CheckCode/{phone}/{code}")
    public Object checkCode(@PathVariable String phone, @PathVariable String code) {
        String cCode = stringRedisTemplate.opsForValue().get(phone);
        if (cCode == null) return "no";
        if (cCode.equals(code)) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation("注册")
    @PostMapping("Register")
    public Object register(User user, String code)
            throws Exception {
        Object checkCode = checkCode(user.getUserPhone(), code);
        if (checkCode.equals("ok")) {
            //删除redis里的数据
            stringRedisTemplate.delete(user.getUserPhone());
            //加密
            user.setUserPassword(MD5Util.md5(user.getUserPassword(), MD5Util.KEY));
            userService.insertUser(user);
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("Login")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "34234",name = "userPhone",paramType = "User"),
            @ApiImplicitParam(value = "dasdas",name = "userPwd")
    })
    public Object login(@ApiIgnore User user, int type, HttpServletRequest request)
            throws Exception {
        User userByPas = userService.getPasswordByPhone(user.getUserPhone());
        if (MD5Util.verify(user.getUserPassword(), MD5Util.KEY, userByPas.getUserPassword())) {
            //生成token
            long time = 1000 * 60 * 30;//30分钟
            if (type == 1) time = 1000 * 60 * 60 * 24 * 3; //三天
            Map<String, Object> map = new HashMap<String, Object>() {{
                put("phone", user.getUserPhone());
            }};
            String jwt = TokenUtil.createJWT(map, time);
            stringRedisTemplate.opsForValue().set(user.getUserPhone() + "_token", jwt, time, TimeUnit.MILLISECONDS);
            map.put("token", jwt);
            map.put("msg", "ok");
            String so = request.getHeader("carToken");
            List<ShoppingCar> cars = shoppingCarService.selectAll(so, 1);
            for (int i = 0; i < cars.size(); i++) {
                MerchandiseSKU merchandiseSKU = merchandiseSKUService.selectByMerchandise(cars.get(i).getMerchandiseId(),
                        cars.get(i).getMerchandiseColor(), cars.get(i).getMerchandiseSize());
                cars.get(i).setUserPhone(user.getUserPhone());
                ShoppingCar car = shoppingCarService.checkExist(cars.get(i), 0);
                if (car == null)
                    shoppingCarService.insertCar(cars.get(i));
                else {
                    if ((car.getMerchandiseCount() + cars.get(i).getMerchandiseCount()) > merchandiseSKU.getMerchandiseCount()) {
                        break;
                    }
                    car.setMerchandiseCount(car.getMerchandiseCount() + cars.get(i).getMerchandiseCount());
                    car.setMerchandiseMoney(car.getMerchandiseMoney() + cars.get(i).getMerchandiseMoney());
                    shoppingCarService.updateCar(car, 0);
                }
            }
            shoppingCarService.deleteAll(so, 1);
            return map;
        } else
            return ImmutableMap.of("msg", "no");
    }

    @ApiOperation("验证是否存在此手机号")
    @GetMapping("CheckPhone/{phone}")
    public Object checkPhone(@PathVariable String phone) {
        return userService.checkPhone(phone);
    }

    /**
     * 判断是否登录(单点登录)
     *
     * @param request
     * @return code : 0/成功 1/已被登录 500/错误
     */
    @ApiOperation("判断是否登录")
    @GetMapping("CheckLogin")
    public Object checkLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            String phone = GetPhoneUtil.getPhone(request);
            String redisToken = stringRedisTemplate.opsForValue().get(phone + "_token");
            if (!token.equals(redisToken))
                return ImmutableMap.of("msg", "单点登陆", "code", "1");
            else
                return ImmutableMap.of("msg", "已经登录", "code", "0", "phone", phone);
        } catch (Exception e) {
            return ImmutableMap.of("msg", "发生错误", "code", "500");
        }
    }

    @ApiOperation("注销")
    @GetMapping("OutLogin")
    public Object outLogin(HttpServletRequest request) {
        try {
            String phone = TokenUtil.parseJWT(request.getHeader("token")).get("phone").toString();
            stringRedisTemplate.delete(phone + "_token");
            return ImmutableMap.of("msg", "ok");
        } catch (Exception e) {
            return ImmutableMap.of("msg", "no");
        }
    }

    @ApiOperation(value = "根据手机号获取用户信息", notes = "通过token获取用户手机号码")
    @GetMapping("FindUser")
    public Object findUser(HttpServletRequest request) throws Exception {
        User user = userService.selectUserByPhone(TokenUtil.parseJWT(request.getHeader("token")).get("phone").toString());
        return ImmutableMap.of("user", user, "grade", gradeService.selectGradeById(user.getGradeId()));
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户性别和出生年月日")
    @GetMapping("UpdateDetails")
    public Object updateDetails(User user, HttpServletRequest request) throws Exception {
        user.setUserPhone(GetPhoneUtil.getPhone(request));
        return userService.updateUserByPhone(user);
    }

    @ApiOperation("修改用户头像")
    @PostMapping("UpdatePhoto")
    public Object updatePhoto(MultipartFile avatar_file, HttpServletRequest request) throws Exception {
        String urlImage = FastDFSUtil.getUrlImage(avatar_file);
        if (urlImage == null)
            return ImmutableMap.of("result", -1);
        return ImmutableMap.of("data", userService.updatePhotoByPhone(GetPhoneUtil.getPhone(request), urlImage), "result", urlImage);
    }

    @ApiOperation("重置密码获取验证码")
    @PostMapping("SendCode/ResetPwd/{phone}")
    public Object sendCodeResetPwd(@PathVariable String phone) {
        Object checkPhone = checkPhone(phone);
        if (checkPhone.toString().equals("1"))
            return sendCode(phone);
        else
            return "no";
    }

    @ApiOperation("重置密码")
    @PostMapping("ResetPwd")
    public Object resetPwd(User user) throws Exception {
        //删除redis里的数据
        stringRedisTemplate.delete(user.getUserPhone());
        //加密
        user.setUserPassword(MD5Util.md5(user.getUserPassword(), MD5Util.KEY));
        return userService.resetPwd(user);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户头像,等级,余额")
    @GetMapping("FindDetails")
    public Object findDetails(HttpServletRequest request) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        User user = userService.selectUserByPhone(phone);
        return ImmutableMap.of(
                "user", user,
                "grade", gradeService.selectGradeById(user.getGradeId()),
                "burse", burseService.selectByPhone(phone)
        );
    }

    @ApiOperation("通过token获取手机号")
    @GetMapping("GetPhone")
    public Object getPhone(HttpServletRequest request) throws Exception {
        return GetPhoneUtil.getPhone(request);
    }

    @ApiOperation("判断原密码是否正确")
    @PostMapping("CheckLowPwd")
    public Object checkLowPwd(HttpServletRequest request, String pwd) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        if (MD5Util.verify(pwd, MD5Util.KEY, userService.selectPwdByPhone(phone).getUserPassword())) {
            //获取验证码
            int random = (int) ((Math.random() * 9 + 1) * 100000);
            //存redis - 有效期2分钟
            stringRedisTemplate.opsForValue().set(phone + "_modifyPwd", random + "", 5, TimeUnit.MINUTES);
            return random;
        } else {
            return -1;
        }
    }

    @ApiOperation("判断是否已经验证过密码")
    @PostMapping("CheckLowPwdNow")
    public Object checkLowPwdNow(HttpServletRequest request, String code) throws Exception {
        String phone = GetPhoneUtil.getPhone(request);
        String cCode = stringRedisTemplate.opsForValue().get(phone + "_modifyPwd");
        stringRedisTemplate.delete(phone + "_modifyPwd");
        if (cCode == null) return "no";
        if (cCode.equals(code)) {
            return "ok";
        } else {
            return "no";
        }
    }

    @ApiOperation("修改登录密码")
    @PostMapping("UpdateLoginPwd")
    public Object updateLoginPwd(HttpServletRequest request, User user) throws Exception {
        user.setUserPhone(GetPhoneUtil.getPhone(request));
        user.setUserPassword(MD5Util.md5(user.getUserPassword(), MD5Util.KEY));
        return userService.resetPwd(user);
    }
}
