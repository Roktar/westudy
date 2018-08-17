package bitcamp.java106.pms.web.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.StudySchedule;
import bitcamp.java106.pms.service.StudyScheduleService;

@RestController
@RequestMapping("/schedule")
public class StudyScheduleController {
    
    StudyScheduleService scheduleService;
    
    public StudyScheduleController(StudyScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody String qs) { 
        
        String[] seperateParameter = qs.split("&");
        Map<String, String> seperateKeyValue = new HashMap<>();

        System.out.println(qs);
        
        try {
            for(String param : seperateParameter) {
                String[] keyVal = param.split("=");
                System.out.println(keyVal.toString());
                keyVal[0] = URLDecoder.decode(keyVal[0], "UTF-8");
                keyVal[1] = URLDecoder.decode(keyVal[1], "UTF-8");
                
                if(keyVal[0].startsWith("schedules")) 
                    keyVal[0] = keyVal[0].substring(12) + keyVal[0].substring(10, 11);
                
                seperateKeyValue.put(keyVal[0], keyVal[1]);
            }
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scheduleService.insert(seperateKeyValue);
    }
    
    @RequestMapping("delete")
    public void delete(int no) throws Exception {
        scheduleService.delete(no);
    }
    
/*    @RequestMapping(value="list{page}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<StudySchedule> list(@PathVariable String page, @MatrixVariable(defaultValue="1") int pageNo, @MatrixVariable(defaultValue="3") int pageSize) {      
        System.out.println("list 메소드 호출");
        return scheduleService.list(pageNo, pageSize);
    }*/
    
    //@RequestMapping(value="listdetail/{no}", produces=MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("listdetail/{no}")
    //@ResponseBody
    public List<StudySchedule> listWithSchedules(@PathVariable int no) {  
        System.out.println("listWithSchedules 메소드 호출");
        return scheduleService.getSchedules(no);
    }
    
    @RequestMapping("recent")
    public StudySchedule getAddedRecent(@RequestParam(value="studyNo", defaultValue="0") int no) {
        System.out.println("recent ->" + no);
        return scheduleService.getRecent(no);
    }
}



