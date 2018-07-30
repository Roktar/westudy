package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Study;
import bitcamp.java106.pms.service.StudyService;

@RestController
@RequestMapping("/study")
public class StudyController {
    
    StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
     public void add(Study study) throws Exception {
             studyService.add(study);
     }
     
     @RequestMapping("delete")
     //@ResponseStatus(HttpStatus.OK) // 응답 상태 코드 값의 기본은 "200(OK)" 이다.
     public void delete(
             @RequestParam("no") int no) throws Exception {
        studyService.delete(no);
     }
     
     @RequestMapping("list")
     public Object list() {
         return studyService.list();
     }
     
     @RequestMapping("update")
     @ResponseStatus(HttpStatus.OK)
     public void update(Study study) throws Exception {
         studyService.update(study);
     }
     
     @RequestMapping("{no}")
     public Study view(@PathVariable int no) throws Exception {
         return studyService.get(no);
     }

}
