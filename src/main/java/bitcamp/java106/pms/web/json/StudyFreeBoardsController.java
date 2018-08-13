package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java106.pms.domain.StudyFreeBoard;
import bitcamp.java106.pms.service.StudyFreeBoardService;

@RestController
@RequestMapping("/FreeBoard")
public class StudyFreeBoardsController {
    
    @Autowired ServletContext sc;
    StudyFreeBoardService studyFreeBoardService;
    
    public StudyFreeBoardsController(StudyFreeBoardService studyFreeBoardService) {
        this.studyFreeBoardService = studyFreeBoardService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public int add(String title, String content, int memNo, int studyNo, MultipartFile[] files) throws Exception {
        String filesDir = sc.getRealPath("/boardFiles");
        StudyFreeBoard[] boards = new StudyFreeBoard[files.length];
         
        for (int i = 0; i  < files.length; i++) {
            StudyFreeBoard board = new StudyFreeBoard();
            board.setTitle(title);
            board.setContent(content);
            board.setMemNo(memNo);
            board.setStudyNo(studyNo);
            board.setOriginFile(files[i].getOriginalFilename());
            String filename = UUID.randomUUID().toString();
            board.setFile(filename);
            
            try {
                File path = new File(filesDir + "/" + filename);
                files[i].transferTo(path);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            boards[i] = board;
            
        }
        return studyFreeBoardService.insert(boards);
          
    }
    
    @RequestMapping("list")
    public List<StudyFreeBoard> list(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("studyNo") String studyNo) {  
       System.out.println(pageNo);
       System.out.println(pageSize);
       return studyFreeBoardService.selectList(pageNo, pageSize, studyNo);
    }
    
    @RequestMapping("getCount")
    public int getCount(@RequestParam("studyNo") String studyNo) {  
       
       return studyFreeBoardService.getCount(studyNo);
    }
    
    
    
    @RequestMapping("{no}")
    public StudyFreeBoard view(@PathVariable int no,
                      @RequestParam("studyNo") String studyNo) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("no", no);
        params.put("studyNo", studyNo);
        
        return studyFreeBoardService.listByOne(params);
    }
    
    @RequestMapping("update")
    
    public int update(@RequestParam("no") int no,
            String title, String content, int memNo, int studyNo, MultipartFile[] files) throws Exception {
        System.out.println(no);
        System.out.println(title);
        System.out.println(memNo);
        System.out.println(studyNo);
        String filesDir = sc.getRealPath("/boardFiles");
        StudyFreeBoard[] boards = new StudyFreeBoard[files.length];
         
        for (int i = 0; i  < files.length; i++) {
            System.out.println(files[i].getOriginalFilename());
            StudyFreeBoard board = new StudyFreeBoard();
            board.setNo(no);
            board.setTitle(title);
            board.setContent(content);
            board.setMemNo(memNo);
            board.setStudyNo(studyNo);
            board.setOriginFile(files[i].getOriginalFilename());
            String filename = UUID.randomUUID().toString();
            board.setFile(filename);
            
            try {
                File path = new File(filesDir + "/" + filename);
                files[i].transferTo(path);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            boards[i] = board;
            
        }
        return studyFreeBoardService.update(boards);
          
    }
    
    @RequestMapping("delete{no}")
    public void delete(@PathVariable int no) throws Exception {
        
        studyFreeBoardService.delete(no);
    }
    
    @RequestMapping("search")
    public List<StudyFreeBoard> search(@RequestParam("title") String title,
                                       @RequestParam("studyNo") String studyNo,
                                       @RequestParam("pageNo") int pageNo,
                                       @RequestParam("pageSize") int pageSize) throws Exception{
        
        Map<String,Object> params = new HashMap<>();
        params.put("title", title);
        params.put("studyNo", studyNo);
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        return studyFreeBoardService.search(params);
        
    }
    
    @RequestMapping("getSearchCount")
    public int getSearchCount(@RequestParam("title") String title,
                              @RequestParam("studyNo") String studyNo) throws Exception {  
        Map<String,Object> params = new HashMap<>();
        params.put("title", title);
        params.put("studyNo", studyNo);
        
       return studyFreeBoardService.getSearchCount(params);
    }
}

















