package timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.token.TokenService;
import util.AuthUtil;

/**
 * 定时器
 */
@Component
public class GetAccessTokenTimer {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 每周一刷新一次
     */
    @Scheduled(cron = "* * * * * 1")
    public void getAccessTokenTimer() {
        tokenService.updateToken(AuthUtil.getAuth());
        stringRedisTemplate.delete("access_token");
        AuthUtil.access_token = tokenService.getToken();
        System.out.println(AuthUtil.access_token);
    }


}
