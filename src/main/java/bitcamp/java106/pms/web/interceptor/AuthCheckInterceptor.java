package bitcamp.java106.pms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import bitcamp.java106.pms.domain.Member;

// 인터셉터로 xml 파일에 등록되어있는 클래스

public class AuthCheckInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        System.out.println("인터셉터 : " + loginUser);
        
        if(loginUser == null) { // 로그인이 안된 경우 로그인 폼으로 이동
            response.sendRedirect(request.getContextPath()+ "/index.html");
            return false;
        }
            
        return true; // 로그인 된 경우 다음 인터페이스나 페이지 컨트롤러를 실행
    }
}
