package main.java.bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import main.java.bitcamp.java106.pms.domain.Member;

public interface MemberDao {
    int delete(int no);
    List<Member> selectList(Map<String,Object> params);
    int insert(Member member);
    int update(Member member);
    Member selectOne(int no);
    Member selectOneWithPassword(Map<String,Object> params);
    int count(Map<String,Object> params);
}

