package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.MemberMessage;
import bitcamp.java106.pms.service.MemberMessageService;

@RestController
@RequestMapping("/message")
public class MemberMessageController {
    MemberMessageService memberMessageService;

    public MemberMessageController(MemberMessageService memberMessageService) {
        this.memberMessageService = memberMessageService;
    }
    
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
     public void add(MemberMessage memberMessage) throws Exception {
        memberMessageService.add(memberMessage);
     }
     
     @GetMapping("delete")
     public void delete(@RequestParam("no") int no) throws Exception {
        memberMessageService.delete(no);
     }
     
     @RequestMapping("list/send/{no}")
     public Object selectListSender(@PathVariable("no") int senderNo) {
         return memberMessageService.sendList(senderNo);
     }
     
     @RequestMapping("list/receive/{no}")
     public Object selectListReceiver(@PathVariable("no") int receiverNo) {
         return memberMessageService.receiveList(receiverNo);
     }

     @RequestMapping("{no}")
     public MemberMessage view(@PathVariable int no) throws Exception {
         return memberMessageService.get(no);
     }
     
     @GetMapping("find")
     public int findUser(String id) {
         return memberMessageService.find(id);
     }
}
