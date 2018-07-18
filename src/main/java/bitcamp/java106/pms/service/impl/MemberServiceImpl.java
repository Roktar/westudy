package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
    MemberDao memberDao;
    
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public void insert(Member member) {
        memberDao.insert(member);
    };
    
    public Member selectOne(String id) {
      return memberDao.selectOne(id);
    };
    
    @Override
    public boolean isExist(String id, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);
        
         return memberDao.count(params) > 0 ? true : false;
    }

    @Override
    public Member selectOneSimpleCase(String id) {
        return memberDao.selectOneSimpleCase(id);
    }
}
