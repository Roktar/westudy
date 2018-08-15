package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java106.pms.domain.StudyInfo;
import bitcamp.java106.pms.domain.StudySurvey;
import bitcamp.java106.pms.service.StudyInfoService;
import bitcamp.java106.pms.service.StudySurveyService;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/studyInfo")
public class StudyInfoController {
    
    StudyInfoService studyInfoService;
    StudySurveyService studySurveyService;
    @Autowired ServletContext sc;

    public StudyInfoController(StudyInfoService studyInfoService, StudySurveyService studySurveyService) {
        this.studyInfoService = studyInfoService;
        this.studySurveyService = studySurveyService;
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
    
    @RequestMapping("list_search{page}")
    public Object listSearch( 
            @RequestParam(defaultValue="1") int pageNo,
            @RequestParam(defaultValue="9") int pageSize) {
        return studyInfoService.list(pageNo, pageSize);
    }
    
    @RequestMapping("list_count")
    public int listCount() {
        return studyInfoService.count();
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
    
    @PostMapping("update_info")
    @ResponseStatus(HttpStatus.OK)
    public Object updateInfo(StudyInfo studyInfo) throws Exception {
        return studyInfoService.update(studyInfo);
    }
    
    @PostMapping("update_tags")
    @ResponseStatus(HttpStatus.OK)
    public Object updateTag(@RequestBody String tags) throws Exception {
        System.out.println("tags 호출, " + tags);
        return studyInfoService.updateTag(tags.split("&"));
    }
    
    @PostMapping("photo")
    public Object photoUpload(int studyNo, MultipartFile files) {
        
        HashMap<String,Object> jsonData = new HashMap<>();
        
        String filesDir = sc.getRealPath("/img/studyImgs");
        
        String filename = UUID.randomUUID().toString();
        jsonData.put("filename", filename);
        jsonData.put("filesize", files.getSize());
        jsonData.put("originname", files.getOriginalFilename());
        try {
            File path = new File(filesDir + "/" + filename);
            files.transferTo(path);
            
            String thumbnailPath = path.getCanonicalPath() + "_300x300";
            System.out.println(thumbnailPath);
            Thumbnails.of(path)
                      .size(300, 300)
                      .outputFormat("jpg")
                      .toFile(new File(thumbnailPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return studyInfoService.setPhoto(filename, studyNo, jsonData);
    }
    
    @RequestMapping("listRandom")
 	 public List<StudyInfo> selectListRandom() {
        return studyInfoService.selectListRandom();
    }
    
    //동현
    @RequestMapping("nearList")
    public List<StudyInfo> nearList(
    		@RequestParam(value = "city", defaultValue = "1") String city,
    		@RequestParam(value = "county", defaultValue = "1") String county,
    		@RequestParam(value = "no", defaultValue = "1") int no) throws Exception {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("city", city);
    	paramMap.put("county", county);
    	paramMap.put("no", no);
    	
    	System.out.println("city : " + city);
    	System.out.println("county : " + county);
    	System.out.println("memno : " + no);
    	
    	List<StudyInfo> nearList = studyInfoService.nearList(paramMap);
    	System.out.println("nearList컨트롤러");
    	return nearList;
    }
    
    /* 설문조사 메소드 */
    @PostMapping("add/survey")
    public Object addSurvey(@RequestBody String qs, @RequestParam(value="no", defaultValue="0") int studyNo) {

        try {
            return studySurveyService.add(qs.split("&"), studyNo);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    @GetMapping("survey/list")
    public List<StudySurvey> surveyList(@RequestParam(value="no", defaultValue="0") int no) {
        return studySurveyService.list(no);
    }
    
    @PostMapping("survey/vote/radio")
    public Object vote(@RequestParam(value="studyNo", defaultValue="0") int studyNo, 
                       @RequestParam(value="memNo", defaultValue="0") int memNo, 
                       @RequestParam(value="surveyNo", defaultValue="0") int surveyNo, 
                       @RequestParam(value="itemNo", defaultValue="0") int itemNo) {
        return studySurveyService.vote(studyNo, memNo, surveyNo, itemNo);
    }
    
    @PostMapping("survey/vote/checkbox")
    public Object voteCheckbox(@RequestBody String qs) {
        return studySurveyService.vote(qs.split("&"));
    }
    
    /* 설문조사 */
}

// ver 55 - JSON 데이터를 출력하는 페이지 컨트롤러 추가


