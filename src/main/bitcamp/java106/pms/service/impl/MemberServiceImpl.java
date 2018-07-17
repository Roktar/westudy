package main.bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;

public class MemberServiceImpl {
    
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
        HashMap<String,Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);
        
        return memberDao.count(params) > 0 ? true : false;
    }
    
    @Override
    public int add(Member member) {
        return memberDao.insert(member);
    }
    
    @Override
    public int update(Member member) {
        return memberDao.update(member);
    }
    
    @Override
    public int delete(int no) {
        return memberDao.delete(no);
    }

}
