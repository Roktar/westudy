package bitcamp.java106.pms.dao;

import java.util.Map;

import bitcamp.java106.pms.domain.Member;

public interface MemberDao {
    Member selectOne(String id);
    void insert_basic(Member member);
    int count(Map<String, Object> params);
    Member selectOneSimpleCase(String id);
    int update(Member member);
}
