package bitcamp.java106.pms.dao;

import java.util.Map;

public interface EmailAuthDao {
    void createAuthKey(Map<String, Object> params);
    int getUserNo(String authCode);
    void removeAuth(int memberNo);
}
