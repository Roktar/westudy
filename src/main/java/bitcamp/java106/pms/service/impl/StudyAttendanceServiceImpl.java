// 업무로직 구현체 - 고객사 마다 다른 구현을 할 수 있다.
package main.java.bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudyAttendanceDao;
import main.java.bitcamp.java106.pms.domain.StudyAttendance;
import main.java.bitcamp.java106.pms.service.StudyAttendanceService;

@Service
public class StudyAttendanceServiceImpl implements StudyAttendanceService {
    
    StudyAttendanceDao studyAttendanceDao;
    
    public StudyAttendanceServiceImpl(StudyAttendanceDao studyAttendanceDao) {
        this.studyAttendanceDao = studyAttendanceDao;
    }
    
    @Override
    public List<StudyAttendance> list(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        return studyAttendanceDao.selectList(params);
    }
    
    @Override
    public StudyAttendance get(int no) {
        return studyAttendanceDao.selectOne(no);
    }
    
    @Override
    public int add(StudyAttendance studyAttendance) {
        return studyAttendanceDao.insert(studyAttendance);
    }
    
    @Override
    public int update(StudyAttendance studyAttendance) {
        return studyAttendanceDao.update(studyAttendance);
    }
    
    @Override
    public int delete(int no) {
        return studyAttendanceDao.delete(no);
    }
}

//ver 53 - 클래스 추가






