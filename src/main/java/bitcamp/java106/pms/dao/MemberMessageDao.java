package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.MemberMessage;

public interface MemberMessageDao {
    int delete(Map<String, Object> params);
    int insert(MemberMessage memberMessage);
    List<MemberMessage> selectListSend(Map<String, Object> params);
    List<MemberMessage> selectListReceive(Map<String, Object> params);
    MemberMessage selectOne(int no);
    int add(Map<String, Object> param);
}
