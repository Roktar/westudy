package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.TestBase;

public interface TestBaseDao {
    List<TestBase> selectList(Map<String, Object> map);
    List<TestBase> selectListWithSchedules(Map<String, Object> map);
    void insert(TestBase tb);
    void delete(int no);
}
