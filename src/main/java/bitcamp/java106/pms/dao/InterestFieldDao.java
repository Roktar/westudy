package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.InterestField;

public interface InterestFieldDao {
    void insert(InterestField inf);
    void delete(int no);
    List<InterestField> selectList(int memNo);
    
}
