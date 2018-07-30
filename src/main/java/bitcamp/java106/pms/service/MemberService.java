package bitcamp.java106.pms.service;

import java.util.Map;

import bitcamp.java106.pms.domain.Member;

public interface MemberService {
    void insert(Map<String, String> params);
    Member selectOne(String id);
    Member get(int id);
    boolean isExist(String id, String password);
    Member selectOneSimpleCase(String id);
    Object changePassword(int no, String nowPassword, String newPassword);
    int update(Member member);
} 
 