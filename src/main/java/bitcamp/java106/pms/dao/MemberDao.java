package main.java.bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.domain.TestBoard;

public interface MemberDao {
    List<Member> selectList(Map<String, Object> params);
}

