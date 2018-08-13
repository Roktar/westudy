package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudyFreeBoardDao;
import bitcamp.java106.pms.domain.StudyFreeBoard;
import bitcamp.java106.pms.service.StudyFreeBoardService;


@Service
public class StudyFreeBoardServiceImpl implements StudyFreeBoardService{
    
    StudyFreeBoardDao studyFreeBoardDao;
    
    public StudyFreeBoardServiceImpl(StudyFreeBoardDao studyFreeBoardDao) {
        this.studyFreeBoardDao = studyFreeBoardDao;
        
    }

    @Override
    public int insert(StudyFreeBoard[] studyFreeBoards) {
        
        for(int i=0; i< studyFreeBoards.length; i++) {
            studyFreeBoardDao.insert(studyFreeBoards[i]);
        }
        return 1;
    }

    @Override
    public List<StudyFreeBoard> selectList(int pageNo, int pageSize, String studyNo) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("studyNo", studyNo);
           
        return studyFreeBoardDao.selectList(params);
    }
    
    @Override
    public int getCount(String studyNo) {
        
        return studyFreeBoardDao.getCount(studyNo);
    }
    
    @Override
    public StudyFreeBoard listByOne(Map<String,Object> params) {
        return studyFreeBoardDao.listByOne(params);
    }

    @Override
    public int update(StudyFreeBoard[] studyFreeBoards) {

        for(int i=0; i< studyFreeBoards.length; i++) {
            studyFreeBoardDao.update(studyFreeBoards[i]);
        }
        return 1;
    }
    
    @Override
    public void delete(int no) {
        studyFreeBoardDao.delete(no);
        
    }

    @Override
    public List<StudyFreeBoard> search(Map<String, Object> params) {
        
        return studyFreeBoardDao.search(params);
    }

    @Override
    public int getSearchCount(Map<String, Object> params) {

        return studyFreeBoardDao.getSearchCount(params);
    }



}
