package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.TestAdditionalDao;
import bitcamp.java106.pms.dao.TestBaseDao;
import bitcamp.java106.pms.domain.TestAdditional;
import bitcamp.java106.pms.domain.TestBase;
import bitcamp.java106.pms.service.TestService;


@Service
public class TestServiceImpl implements TestService{
    
    TestBaseDao testBaseDao;
    TestAdditionalDao testAdditionalDao;
    
    public TestServiceImpl(TestBaseDao testBaseDao, TestAdditionalDao testAdditionalDao) {
        this.testBaseDao = testBaseDao;
        this.testAdditionalDao = testAdditionalDao;
    }
    
    @Override
    public List<TestBase> list(int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNo", pageNo);
        map.put("pageSize", pageSize);
        
        List<TestBase> lists = testBaseDao.selectList(map);
        
        System.out.println(lists);
        
        return lists;
    }

    @Override
    public List<TestBase> getSchedules(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNo", pageNo);
        map.put("pageSize", pageSize);
        
        List<TestBase> list = testBaseDao.selectListWithSchedules(map);
        System.out.println(list);

        for(TestBase t : list) {
            List<TestAdditional> schedules = testAdditionalDao.selectListWithNo(t.getNo());
            System.out.println(schedules);
            t.setSchedules(schedules);
        }
        
        return list;
    }

    @Override
    public void insert(TestBase tb) {
        testBaseDao.insert(tb);
    }

    @Override
    public void delete(int no) {
        testAdditionalDao.delete(no);
        testBaseDao.delete(no);
    }
}
