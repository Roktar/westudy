package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.TestAdditional;

public interface TestAdditionalDao {
    List<TestAdditional> selectListWithNo(int no);
    void delete(int no);
}
