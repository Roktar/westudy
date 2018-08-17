package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyScheduleDetail;

public interface StudyScheduleDetailDao {
    List<StudyScheduleDetail> selectListWithNo(int i);
    List<StudyScheduleDetail> selectListWithNoMaps(Map<String, Object> params);
    void insert(StudyScheduleDetail ta);
    void delete(int no);
}
