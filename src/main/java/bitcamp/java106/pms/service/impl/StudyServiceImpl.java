package bitcamp.java106.pms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudyDao;
import bitcamp.java106.pms.domain.HashTag;
import bitcamp.java106.pms.domain.Study;
import bitcamp.java106.pms.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {

    StudyDao studyDao;
      
    public StudyServiceImpl(StudyDao studyDao) {
        this.studyDao = studyDao;
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


    @Override
    public List<Study> list(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<HashTag> listTag(int no) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int addTag(String[] tag) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public int getLimitOne() {
        // TODO Auto-generated method stub
        return 0;
    }
}
