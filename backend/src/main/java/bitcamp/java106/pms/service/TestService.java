package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.TestAdditional;
import bitcamp.java106.pms.domain.TestBase;

public interface TestService {
    List<TestBase> list(int pageNo, int pageSize);
    List<TestBase> getSchedules(int pageNo, int pageSize);
    void insert(TestBase tb);
    void delete(int no);
}
