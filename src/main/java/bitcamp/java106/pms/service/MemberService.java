package main.java.bitcamp.java106.pms.service;

import java.util.List;

import main.java.bitcamp.java106.pms.domain.Member;

public interface MemberService {
    List<Member> list(int pageNo, int pageSize);
    Member get(int no);
    boolean isExist(int no, String password);
    int add(Member member);
    int update(Member member);
    int delete(int no);
}
