package bitcamp.java106.pms.service;

public interface KakaoService {

    <T> T me(String accessToken, Class<T> clazz);
    
}