package bitcamp.java106.pms.web.json;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.FacebookService;
import bitcamp.java106.pms.service.KakaoService;
import bitcamp.java106.pms.service.MemberService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    MemberService memberService;
    FacebookService facebookService;
    KakaoService kakaoService;
    
    public AuthController(MemberService memberService, FacebookService facebookService, KakaoService kakaoService) {
        this.memberService = memberService;
        this.facebookService = facebookService;
        this.kakaoService = kakaoService;
    }
    
    @GetMapping("/loginstat")
    public Member getLogin(HttpSession session) {
        return (Member) session.getAttribute("loginUser");
    }
    
    @RequestMapping("/login")
    public Object login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws Exception {
        System.out.println(id);
        System.out.println(password);
        System.out.println("id:" + id);
        System.out.println("pw:" + password);
                
        Map<String, Object> res = new HashMap<>();

        if (memberService.isExist(id, password)) {
            session.setAttribute("loginUser", memberService.selectOneSimpleCase(id));
            res.put("state", "success");
        } else {
            session.invalidate();
            res.put("state", "fail");
        }
        return res;
    }
    
    @RequestMapping("/facebookLogin")
    public Object facebookLogin(
            String accessToken, 
            HttpSession session) {
        
        try {
            @SuppressWarnings("rawtypes")
            Map userInfo = facebookService.fb(accessToken, Map.class);
            //System.out.println("facebook="+accessToken);
            //System.out.println("userInfo"+userInfo);
            Member member = memberService.get((String)userInfo.get("email"));
            if (member == null) {
                member = new Member();
                member.setEmail((String)userInfo.get("email"));
                member.setPassword("1111");
                member.setName((String)userInfo.get("name"));
                member.setTel("010-1111-1111");
                //System.out.println(member);
                memberService.add(member);
            }
            // 회원 정보를 세션에 저장하여 자동 로그인 처리를 한다.
            session.setAttribute("loginUser", member);
        
            HashMap<String,Object> res = new HashMap<>();
            res.put("status", "success");
            return res;
        
        } catch (Exception e) {
            HashMap<String,Object> res = new HashMap<>();
            res.put("status", "fail");
            return res;
        }
    }
    
    
    @RequestMapping("/kakaoLogin")
    public Object kakaoLogin(
            String accessToken, 
            HttpSession session) {

        try {
            @SuppressWarnings("rawtypes")
            Map userInfo = kakaoService.me(accessToken, Map.class);
            System.out.println("userInfo"+userInfo);
            Member member = memberService.get((String)userInfo.get("kaccount_email"));
            System.out.println("kakao "+member);
            if (member == null) {
                member = new Member();
                member.setName((String)((Map)userInfo.get("properties")).get("nickname"));
                member.setEmail((String)userInfo.get("kaccount_email"));
                member.setPassword("3333");
                member.setTel("010-3333-3333");
                memberService.add(member);
            }

            session.setAttribute("loginUser", member);

            HashMap<String,Object> res = new HashMap<>();
            res.put("status", "success");
            return res;
            
        } catch (Exception e) {
            HashMap<String,Object> res = new HashMap<>();
            res.put("status", "fail");
            res.put("exception", e.getStackTrace());
            return res;
        }
    }
    
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpSession session) throws Exception {
        session.invalidate();
    }
}

//               [웹브라우저]                                  [웹서버] 
// GET 요청: /java106-java-project/auth/login ===>
//                                                       <=== 응답: 로그인폼 
// POST 요청: /java106-java-project/auth/login ===>
//                                                       <=== 응답: redirect URL
// GET 요청: /java106-java-project ===> 
//                                                       <=== 응답: index.html
// 메인화면 출력!



