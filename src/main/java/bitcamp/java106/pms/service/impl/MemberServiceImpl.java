package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.InterestFieldDao;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.InterestField;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.mail.MailHandler;
import bitcamp.java106.pms.mail.TempKeyGenerator;
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
    public Member get(int id) {
        return memberDao.selectOne(id);
    }
    
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
        
        String personalAuthCode = new TempKeyGenerator().getKey(50, false);
        
        Map<String, Object> authInfo = new HashMap<>();
        authInfo.put("no", refid);
        authInfo.put("authCode", personalAuthCode);
        emailDao.createAuthKey(authInfo);

        if(refid > 0) {
            for(Entry<String, String> entry : params.entrySet()) {
                InterestField itr = new InterestField();
                itr.setNo(refid);
                itr.setCategory(entry.getValue());
                interestFieldDao.insert(itr);
            }
        }
        try {
            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject("[Westudy] 서비스 이메일 인증 요청 메일입니다.");
            sendMail.setText( new StringBuffer().append("<h1>서비스 이용을 위해 아래 링크를 클릭하여 메일 인증을 해주시기 바랍니다.</h1>")
                                                .append("<a href='http://westudy.java106.com:8888/westudy/json/auth/email/"+personalAuthCode)
                                                .append("' target='_blank'> 이메일 인증하기 </a>")
                                                .toString());
            sendMail.setTo(member.getEmail());
            sendMail.send();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object changePassword(int no, String nowPassword, String newPassword) {
        // TODO Auto-generated method stub
        System.out.println("service run");
        
        Map<String, Object> validateParams = new HashMap<>();
        validateParams.put("no", no);
        validateParams.put("password", nowPassword);
        
        Member member = memberDao.validation(validateParams);
        System.out.println(member);
        
        if(member == null)
            return "fail";
        
        Map<String, Object> params = new HashMap<>();
        params.put("no", no);
        params.put("newPassword", newPassword);
        
        return memberDao.changePassword(params);
    }

    @Override
    public int update(Member member) {
        return memberDao.update(member);
    }

    public void createAuthKey(String email, String authCode) throws Exception {
        Member member = new Member();
        member.setEmail(email);
        member.setAuthCode(authCode);
    }
    
    @Override
	public List<Member> list() {
		return memberDao.selectList();
	}
    @Override
	public int delete(int no) {
		return memberDao.delete(no);
	}
}
