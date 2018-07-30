package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.StudyAttendance;

public interface StudyAttendanceService {
    List<StudyAttendance> list();
    StudyAttendance get(int no);
    int add(StudyAttendance studyAttendance);
}
