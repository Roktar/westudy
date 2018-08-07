package bitcamp.java106.pms.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudyScheduleDao;
import bitcamp.java106.pms.dao.StudyScheduleDetailDao;
import bitcamp.java106.pms.domain.StudySchedule;
import bitcamp.java106.pms.domain.StudyScheduleDetail;
import bitcamp.java106.pms.service.StudyScheduleService;

@Service
public class StudyScheduleServiceImpl implements StudyScheduleService{
    
    StudyScheduleDao scheduleDao;
    StudyScheduleDetailDao detailDao;
    
    public StudyScheduleServiceImpl(StudyScheduleDao scheduleDao, StudyScheduleDetailDao detailDao) {
        this.scheduleDao = scheduleDao;
        this.detailDao = detailDao;
    }
    
    @Override
    public List<StudySchedule> list(int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNo", pageNo);
        map.put("pageSize", pageSize);
        
        List<StudySchedule> lists = scheduleDao.selectList(map);
        
        return lists;
    }

    @Override
    public List<StudySchedule> getSchedules(int no) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        map.put("studyNo", no);
                
        List<StudySchedule> list = scheduleDao.selectList(map);
        System.out.println(list);
        for(StudySchedule t : list) {
            List<StudyScheduleDetail> schedules = detailDao.selectListWithNo(t.getNo());
            System.out.println("baseNo : " + t.getNo() + ", schedules:" + schedules.toString());
            t.setSchedules(schedules);
        }
                
        return list;
    }

    @Override
    public void insert(Map<String, String> param) {
        
        StudySchedule schedule = new StudySchedule();

        schedule.setTitle(param.get("title"));
        schedule.setRefStudyNo(Integer.parseInt(param.get("refStudyNo")));
        schedule.setStartDate(Date.valueOf(param.get("startDate")));
        schedule.setTime(Time.valueOf(param.get("time")));
        schedule.setPlaceAddress(param.get("placeAddress"));
        schedule.setPlaceDetail(param.get("placeDetail"));
        schedule.setContent(param.get("content"));
        schedule.setLatitude( Double.parseDouble(param.get("latitude")));
        schedule.setLongitude( Double.parseDouble(param.get("longitude")));
        
        System.out.println(schedule);

        param.remove("title");
        param.remove("refStudyNo");
        param.remove("placeAddress");
        param.remove("placeDetail");
        param.remove("startDate");
        param.remove("time");
        param.remove("content");
        param.remove("latitude");
        param.remove("longitude");
        
        scheduleDao.insert(schedule);
        // 해당 날짜에 대한 전체정보 저장
        int refid = scheduleDao.selectOne(schedule.getTitle()).getNo(); // 기준 일정 선택, 나중에는 스터디번호로 대체예정
        
        if(refid > 0) {
            StudyScheduleDetail[] list = new StudyScheduleDetail[ (int)param.size()/3 ];
            
            for(int i=0; i<list.length; i++)
                list[i] = new StudyScheduleDetail();
            
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
            
            for(StudyScheduleDetail detail : list)
                detailDao.insert(detail);
            // 상세 일정 저장
        }
    }

    @Override
    public void delete(int no) {
        detailDao.delete(no);
        scheduleDao.delete(no);
    }

    @Override
    public StudySchedule selectOne() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudySchedule getRecent() {
        // TODO Auto-generated method stub
        StudySchedule sc = scheduleDao.selectOneRecent();
        
        List<StudyScheduleDetail> details = detailDao.selectListWithNo(sc.getNo());
        sc.setSchedules(details);
        
        return sc;
    }
}
