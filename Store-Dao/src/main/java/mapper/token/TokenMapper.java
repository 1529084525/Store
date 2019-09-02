package mapper.token;

import pojo.Token;

public interface TokenMapper {

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
