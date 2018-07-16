package bitcamp.java106.pms.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<TestBase> list(int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNo", pageNo);
        map.put("pageSize", pageSize);
        
        List<TestBase> lists = testBaseDao.selectList(map);
        
        return lists;
    }

    @Override
    @ResponseBody
    public List<TestBase> getSchedules(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNo", pageNo);
        map.put("pageSize", pageSize);
        
        List<TestBase> list = testBaseDao.selectList(map);

        for(TestBase t : list) {
            List<TestAdditional> schedules = testAdditionalDao.selectListWithNo(t.getNo());
            t.setSchedules(schedules);
        }
        
        return list;
    }

    @Override
    public void insert(Map<String, String> param) {
        
        TestBase tb = new TestBase();
        tb.setTitle(param.get("title")).setPlace(param.get("place"))
                                       .setStartDate(Date.valueOf(param.get("startDate")))
                                       .setTime(Time.valueOf(param.get("time"))).setTopic(param.get("topic"));
        
        param.remove("title");
        param.remove("place");
        param.remove("startDate");
        param.remove("time");
        param.remove("topic");
        
        testBaseDao.insert(tb);
        // 해당 날짜에 대한 전체정보 저장
        int refid = testBaseDao.selectOne(tb.getTitle()).getNo(); // 기준 일정 선택, 나중에는 스터디번호로 대체예정
        
        if(refid > 0) {
            TestAdditional[] list = new TestAdditional[ (int)param.size()/3 ];
            
            for(int i=0; i<list.length; i++)
                list[i] = new TestAdditional();
            
            for(Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                int no = Integer.parseInt(key.substring( key.length() -1 ));
                int lastProperty = key.indexOf(']');
                
                switch(key.substring(1, lastProperty)) {
                    case "content" :
                        list[no].setContent( entry.getValue() ); break;
                    case "startTime" :
                        list[no].setStartTime( Time.valueOf(entry.getValue()+":00") ); break;
                    case "endTime" :
                        list[no].setEndTime( Time.valueOf(entry.getValue()+":00") ); break;
                }
                list[no].setNo(refid);
            }
            
            for(TestAdditional ta : list)
                testAdditionalDao.insert(ta);
            // 상세 일정 저장
        }
    }

    @Override
    public void delete(int no) {
        testAdditionalDao.delete(no);
        testBaseDao.delete(no);
    }

    @Override
    public TestBase selectOne() {
        // TODO Auto-generated method stub
        return null;
    }
}
