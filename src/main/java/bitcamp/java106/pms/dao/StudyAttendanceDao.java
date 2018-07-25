package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.StudyAttendance;

public interface StudyAttendanceDao {
    List<StudyAttendance> selectList();
    int add(StudyAttendance studyAttendance);
    StudyAttendance get(int no);
    StudyAttendance selectOne(int no);
}
