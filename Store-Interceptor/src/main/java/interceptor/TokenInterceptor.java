package interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器(处理token验证)
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        ImmutableMap<String, Integer> code = ImmutableMap.of("code", -1);
        if (token == null) {
            response.getWriter().print(JSON.toJSON(code));
            return false;
        }
        try {
            TokenUtil.parseJWT(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(JSON.toJSON(code));
            return false;
        }
    }
}
