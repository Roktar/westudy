package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudySchedule;

public interface ScheduleService {
    List<StudySchedule> list(int pageNo, int pageSize);
    List<StudySchedule> getSchedules(int pageNo, int pageSize);
    StudySchedule selectOne();
    void insert(Map<String, String> param);
    //void insert(String title, String startDate, String time, String place, String topic, String option);
    void delete(int no);
}
