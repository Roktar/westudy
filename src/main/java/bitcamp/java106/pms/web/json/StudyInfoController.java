package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import bitcamp.java106.pms.service.StudyInfoService;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/studyInfo")
public class StudyInfoController {
    
    StudyInfoService studyInfoService;
    @Autowired ServletContext sc;

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
}

// ver 55 - JSON 데이터를 출력하는 페이지 컨트롤러 추가


