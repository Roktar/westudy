package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Photo;

public interface PhotoService {
    List<Photo> listByGroup(Map<String, Object> map);
    List<Photo> listByDate(String nowDate, String preDate, String studyNo);
    Photo listByOne(Map<String,Object> params);
    int insert(Photo[] photo);
    void delete(int no);
    
}
