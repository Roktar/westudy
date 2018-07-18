package bitcamp.java106.pms.service;

import bitcamp.java106.pms.domain.Member;

public interface MemberService {
    void insert(Member member);
    Member selectOne(String id);
    boolean isExist(String id, String password);
    Member selectOneSimpleCase(String id);
}
