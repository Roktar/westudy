package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Photo;

public interface PhotoDao {
    void insert(Photo photo);
    void delete(int no);
    List<Photo> listByGroup(Map<String, Object> map);
    List<Photo> listByDate(Map<String, Object> map);
    Photo listByOne(int no);
}
