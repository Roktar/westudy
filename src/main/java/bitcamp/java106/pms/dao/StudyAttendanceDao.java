package bitcamp.java106.pms.dao;

import java.util.Map;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.StudyAttendance;

public interface StudyAttendanceDao {
    StudyAttendance selectOne(int no);
    void insert(StudyAttendance studyAttendance);
    int count(Map<String, Object> params);
    StudyAttendance selectOneSimpleCase(int no);
}
