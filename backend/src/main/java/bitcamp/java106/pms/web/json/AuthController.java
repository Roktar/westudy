// 로그인 폼 출력과 사용자 인증처리 서블릿
package bitcamp.java106.pms.web.json;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.MemberService;

// 서버에서는 화면 이동과 제어를 할 수 없다.
// 즉, 서버에서는 정보를 데이터화하여 프론트에서 처리할 수 있도록 도와주는 역할을 한다.

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    MemberService memberService;
    
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping("/loginuser")
    public Member loginedUser(HttpSession session) {
        return (Member) session.getAttribute("loginUser");
    }
    
    @RequestMapping("/login")
    public Object login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            @RequestParam(value="saveId",required=false) String saveId,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws Exception {
        
        Cookie cookie = null;
        if (saveId != null) {
            cookie = new Cookie("id", id);
            cookie.setMaxAge(60 * 60 * 24 * 7);
        } else { 
            cookie = new Cookie("id", "");
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
        // 쿠키 세팅
        
        Map<String, Object> res = new HashMap<>();

        if (memberService.isExist(id, password)) { // 로그인 성공 시 세션에 해당 로그인 정보를 추가시켜둠
            session.setAttribute("loginUser", memberService.get(id));
            res.put("state", "success");
            
        } else {
            session.invalidate();
            res.put("state", "fail");
        }
        return res; 
    }
    
    @RequestMapping("/logout")
    public void logout(
            HttpServletRequest request,
            HttpSession session) throws Exception {
        
        // 세션을 꺼내 무효화시킨다.
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

//ver 50 - DAO 변경에 따른 메서드 호출 변경
//ver 49 - 요청 핸들러의 파라미터 값 자동으로 주입받기
//ver 48 - CRUD 기능을 한 클래스에 합치기
//ver 47 - 애노테이션을 적용하여 요청 핸들러 다루기
//ver 46 - 페이지 컨트롤러를 POJO를 변경
//ver 45 - 프론트 컨트롤러 적용
//ver 42 - JSP 적용
//ver 41 - 클래스 추가




