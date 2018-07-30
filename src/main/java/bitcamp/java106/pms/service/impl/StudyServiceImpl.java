package bitcamp.java106.pms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudyDao;
import bitcamp.java106.pms.domain.Study;
import bitcamp.java106.pms.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {

    StudyDao studyDao;
      
    public StudyServiceImpl(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @Override
    public List<Study> list() {
        return studyDao.selectList();
    }

    @Override
    public Study get(int no) {
        Study study = studyDao.selectOne(no);
        return study;
    }

    @Override
    public int add(Study study) {
        return studyDao.insert(study);
    }

    @Override
    public int update(Study study) {
        return studyDao.update(study);
    }

    @Override
    public int delete(int no) {
        return studyDao.delete(no);
    }
}
