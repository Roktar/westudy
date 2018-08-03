package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.StudyScheduleDetail;

public interface StudyScheduleDetailDao {
    List<StudyScheduleDetail> selectListWithNo(int no);
    void insert(StudyScheduleDetail ta);
    void delete(int no);
}
