package bitcamp.java106.pms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bitcamp.java106.pms.service.KakaoService;

@Service
public class KakaoServiceImpl implements KakaoService {
    
    public <T> T me(String accessToken, Class<T> type) {
        // 클라이언트가 보낸 액세스 토큰을 가지고 
        // 페이스북 서버에 로그인 사용자 정보를 요청한다.
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            return restTemplate.getForObject(
                    "https://kapi.kakao.com/v1/user/me?access_token={v1}&fields={v2}",
                    type,
                    accessToken, "kaccount_email, properties");
        } catch (Exception e) {
            throw new RuntimeException(
                    "카카오톡 API 실행 오류!", 
                    e);
        }
    }
    
}