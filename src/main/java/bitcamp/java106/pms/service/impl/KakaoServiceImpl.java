package bitcamp.java106.pms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bitcamp.java106.pms.service.KakaoService;

@Service
public class KakaoServiceImpl implements KakaoService {
    
    public <T> T me(String accessToken, Class<T> type) {
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