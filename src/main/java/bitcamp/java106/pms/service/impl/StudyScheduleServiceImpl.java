package bitcamp.java106.pms.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        int refid = schedule.getNo(); 
        System.out.println("getNo --> " + refid);
        
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
    public StudySchedule getRecent(int no) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String data = df.format(new java.util.Date());
        
        Map<String, Object> params = new HashMap<>();
        params.put("studyNo", no);
        params.put("nowDate", data);
        
        StudySchedule sc = scheduleDao.selectOneRecent(params);
        
        int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if( Integer.parseInt( data.split("-")[0] ) % 4 != 0 && 
            Integer.parseInt( data.split("-")[0] ) % 100 != 0 &&
            Integer.parseInt( data.split("-")[0] ) % 400 != 0)
            maxDays[1] = 29;
        
        int loop = 0;
        
        while(loop < 10) {
            System.out.println(loop);
            if(sc != null)
                break;
            int year = Integer.parseInt(data.split("-")[0]);
            int month = Integer.parseInt(data.split("-")[1]);
            int day = Integer.parseInt(data.split("-")[2]);
            
            if(day > maxDays[month-1]) {
                month += 1;
                day = 1;
            } else
                day++;
            
            data = "" + year + "-" + month + "-" + day;
            System.out.println(data);
            
            params.put("nowDate", data);
            sc = scheduleDao.selectOneRecent(params);
            loop++;
        }
        if(sc == null)
            return null;
        sc.setSchedules(detailDao.selectListWithNo(sc.getNo()));
        System.out.println(sc);
        
        return sc;
    }
}
