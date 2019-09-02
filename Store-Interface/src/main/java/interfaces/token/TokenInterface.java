package interfaces.token;

import pojo.Token;

public interface TokenInterface {

    /**
     * 查询token
     * @return
     */
    Token selectToken();

    /**
     * 修改token
     * @param token
     * @return
     */
    int updateToken(String token);
}
