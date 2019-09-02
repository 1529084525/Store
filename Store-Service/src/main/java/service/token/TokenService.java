package service.token;

import interfaces.token.TokenInterface;
import mapper.token.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import pojo.Token;

@Component
public class TokenService implements TokenInterface {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Token selectToken() {
        return tokenMapper.selectToken();
    }

    @Override
    public int updateToken(String token) {
        return tokenMapper.updateToken(token);
    }

    public String getToken() {
        String access_token1 = stringRedisTemplate.opsForValue().get("access_token");
        if (access_token1 == null) {
            String access_token = tokenMapper.selectToken().getAccessToken();
            stringRedisTemplate.opsForValue().set("access_token", access_token);
            return access_token;
        } else {
            return access_token1;
        }
    }
}
