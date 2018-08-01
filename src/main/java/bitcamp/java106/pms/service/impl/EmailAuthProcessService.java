package bitcamp.java106.pms.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.EmailAuthDao;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;

@Service
public class EmailAuthProcessService {

    MemberDao memberDao;
    EmailAuthDao emailDao;
    
    @Autowired ServletContext sc;
    
    public EmailAuthProcessService(MemberDao memberDao, EmailAuthDao emailDao) {
       this.memberDao = memberDao;
       this.emailDao = emailDao;
    }
    
    public void process(String authCode, HttpServletResponse response) {
        try {
            int user = emailDao.getUserNo(authCode);
            int memberNo = memberDao.selectOneByNo(user).getNo();
            Map<String, Object> params = new HashMap<>();
            params.put("no", memberNo);
            params.put("authCode", "authorized");
            memberDao.userAuth(params);
            emailDao.removeAuth(memberNo);
            response.sendRedirect( sc.getContextPath() + "/html/email/auth_ok.html" );
            return;
        } catch(Exception e) { 
            try {
                response.sendRedirect( sc.getContextPath() + "/html/email/auth_fail.html" );
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
