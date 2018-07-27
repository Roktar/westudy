package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.MemberMessage;
import bitcamp.java106.pms.service.MemberMessageService;

@RestController
@RequestMapping("/memberMessage")
public class MemberMessageController {
    MemberMessageService memberMessageService;

    public MemberMessageController(MemberMessageService memberMessageService) {
        this.memberMessageService = memberMessageService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
     public void add(MemberMessage memberMessage) throws Exception {
             memberMessageService.add(memberMessage);
     }
     
     @RequestMapping("delete")
     //@ResponseStatus(HttpStatus.OK) // 응답 상태 코드 값의 기본은 "200(OK)" 이다.
     public void delete(
             @RequestParam("no") int no) throws Exception {
        memberMessageService.delete(no);
     }
     
     @RequestMapping("list")
     public Object list() {
         return memberMessageService.list();
     }

     @RequestMapping("list")
     public Object list() {
         return memberMessageService.list();
     }
          
     @RequestMapping("{no}")
     public MemberMessage view(@PathVariable int no) throws Exception {
         return memberMessageService.get(no);
     }

}
