package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
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
     public int add(int sender, int receiver, String content) throws Exception {
        return memberMessageService.add(sender, receiver, content);
     }
     
     @DeleteMapping("delete{type}")
     public Object delete(@MatrixVariable("no") int no,
    		 			  @MatrixVariable("type") int type) throws Exception {
        return memberMessageService.delete(no, type);
     }
     
     @GetMapping("list/send/{no}/{pages}")
     public Object selectListSender(@PathVariable("no") int senderNo,
                                    @MatrixVariable(name="page", defaultValue="1") int page, 
                                    @MatrixVariable(name="pageSize", defaultValue="10") int pageSize) {
                  return memberMessageService.sendList(senderNo, page, pageSize);
     }
     
     @GetMapping("list/receive/{no}/{pages}")
     public Object selectListReceiver(@PathVariable("no") int receiverNo,
                                      @MatrixVariable(name="page", defaultValue="1") int page, 
                                      @MatrixVariable(name="pageSize", defaultValue="10") int pageSize) {
         return memberMessageService.receiveList(receiverNo, page, pageSize);
     }

     @RequestMapping("{no}")
     public MemberMessage view(@PathVariable int no) throws Exception {
         return memberMessageService.get(no);
     }
     
     @GetMapping("find")
     public int findUser(String id) {
         try {
             return memberMessageService.find(id);
         } catch(Exception e) {
             return -1;
         }
     }
}
