package main.java.bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import bitcamp.java106.pms.dao.MemberDao;
import main.java.bitcamp.java106.pms.domain.Member;
import main.java.bitcamp.java106.pms.service.MemberService;

public class MemberServiceImpl implements MemberService {

    
    MemberDao memberDao;
    
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> list(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        return memberDao.selectList(params);
    }

    @Override
    public Member get(int no) {
        return memberDao.selectOne(no);
    }

    @Override
    public boolean isExist(int no, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int add(Member member) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Member member) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(int no) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
