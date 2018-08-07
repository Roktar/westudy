package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.StudyInfo;
import bitcamp.java106.pms.service.StudyInfoService;

@RestController
@RequestMapping("/studyInfo")
public class StudyInfoController {
    
    StudyInfoService studyInfoService;

    public StudyInfoController(StudyInfoService studyInfoService) {
        this.studyInfoService = studyInfoService;
    }

    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(StudyInfo studyInfo) throws Exception {
        studyInfoService.add(studyInfo);
    }
    
    @RequestMapping("addTag")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(String tag) throws Exception {
        System.out.println(tag);

        String[] tags = tag.split(",");
        studyInfoService.addTag(tags);
    }

    @RequestMapping("listSearch")
    public Object listSearch(@RequestParam("city") String city, 
                            @RequestParam("county") String county,
                            @RequestParam("category") String category,
                            @RequestParam("hashtag") String hashtag) {
        System.out.println("controller===>" + city + "," + county + "," + category + "," + hashtag);
        
        return studyInfoService.listSearch(city,county,category,hashtag);
        
    }
    
    @RequestMapping("delete")
    //@ResponseStatus(HttpStatus.OK) // 응답 상태 코드 값의 default는 "200(OK)" 이다.
    public void delete(@RequestParam("no") int no) throws Exception {
        studyInfoService.delete(no);
    }
    
    @RequestMapping("list{page}")
    public Object list(
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="4") int pageSize) {
        
        return studyInfoService.list(pageNo, pageSize);
    }
    
    @RequestMapping("listTag/{no}")
    public Object listTag(@PathVariable int no) {
        System.out.println(no);
        return studyInfoService.listTag(no);
    }
    
    @RequestMapping("update")
    @ResponseStatus(HttpStatus.OK)
    public void update(StudyInfo studyInfo) throws Exception {
        studyInfoService.update(studyInfo);
    }
    
    @RequestMapping("{no}")
    public StudyInfo view(@PathVariable("no") int no) throws Exception {
        return studyInfoService.get(no);
    }
    
    @RequestMapping("one")
    public int one() throws Exception {
        return studyInfoService.getLimitOne();
    }
    
}

// ver 55 - JSON 데이터를 출력하는 페이지 컨트롤러 추가


