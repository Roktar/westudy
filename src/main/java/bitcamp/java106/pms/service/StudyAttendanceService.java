package bitcamp.java106.pms.service;

import bitcamp.java106.pms.domain.Member;

public interface StudyAttendanceService {
    //void insert(StudyAttendance studyAttendance);
    Member selectOne(int no);
    boolean isExist(int no, String password);
    void delete(int no);
}
