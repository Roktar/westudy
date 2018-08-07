package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Photo;

public interface PhotoDao {
    void insert(Photo photo);
    void delete(int no);
    List<Photo> listByGroup(String studyNo);
    List<Photo> listByDate(Map<String, Object> params);
    Photo listByOne(Map<String,Object> params);
}
