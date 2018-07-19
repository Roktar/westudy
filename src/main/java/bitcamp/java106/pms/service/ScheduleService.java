package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.TestBase;

public interface ScheduleService {
    List<TestBase> list(int pageNo, int pageSize);
    List<TestBase> getSchedules(int pageNo, int pageSize);
    TestBase selectOne();
    void insert(Map<String, String> param);
    //void insert(String title, String startDate, String time, String place, String topic, String option);
    void delete(int no);
}
