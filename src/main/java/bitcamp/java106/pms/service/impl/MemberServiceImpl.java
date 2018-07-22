package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.InterestFieldDao;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.InterestField;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
    MemberDao memberDao;
    InterestFieldDao interestFieldDao;
    
    public MemberServiceImpl(MemberDao memberDao, InterestFieldDao interestFieldDao) {
        this.memberDao = memberDao;
        this.interestFieldDao = interestFieldDao;
    }
    
    @Override
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

    @Override
    public void insert(Map<String, String> params) {
        // TODO Auto-generated method stub
        
        Member member = new Member();
        member.setEmail(params.get("email"));
        member.setPassword(params.get("password"));
        member.setName(params.get("name"));
        member.setTel(params.get("tel"));
        
        params.remove("email");
        params.remove("password");
        params.remove("name");
        params.remove("tel");
        
        memberDao.insert_basic(member);
        int refid = memberDao.selectOne(member.getEmail()).getNo(); // 기준 회원 선택
        System.out.println("");
        
        if(refid > 0) {
            for(Entry<String, String> entry : params.entrySet()) {
                InterestField itr = new InterestField();
                itr.setNo(refid);
                itr.setCategory(entry.getValue());
                interestFieldDao.insert(itr);
            }
        }
    }

    @Override
    public int update(Member member) {
        return memberDao.update(member);
    }
}
