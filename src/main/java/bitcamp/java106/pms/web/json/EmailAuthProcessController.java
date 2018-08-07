package bitcamp.java106.pms.web.json;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.java106.pms.service.impl.EmailAuthProcessService;

@Controller
@RequestMapping("/auth/email")
public class EmailAuthProcessController {
    EmailAuthProcessService service;
    
    public EmailAuthProcessController(EmailAuthProcessService service) {
        this.service = service;
    }
    
    @RequestMapping("{authCode}")
    public void emailAuthProcess(@PathVariable String authCode, HttpServletResponse response) {
        System.out.println("authCode");
        service.process(authCode, response);
    }
}
