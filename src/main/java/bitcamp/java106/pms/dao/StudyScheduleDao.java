package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudySchedule;

public interface StudyScheduleDao {
    List<StudySchedule> selectList(Map<String, Object> map);
    //List<TestBase> selectListWithSchedules(Map<String, Object> map);
    StudySchedule selectOne(String title);
    StudySchedule selectOneRecent();
    void insert(StudySchedule tb);
    void delete(int no);
}
