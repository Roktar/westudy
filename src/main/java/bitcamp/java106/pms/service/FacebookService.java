package bitcamp.java106.pms.service;

public interface FacebookService {
    <T> T fb(String accessToken, Class<T> type);
}
