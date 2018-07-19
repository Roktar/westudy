package bitcamp.java106.pms.web.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    MemberService memberService;
    
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @RequestMapping("add")
    public void insert(@RequestBody String qs ) {
        System.out.println(qs);
        
        String[] seperateParameter = qs.split("&");
        Map<String, String> seperateKeyValue = new HashMap<>();
        
        int idx = 0;
        try {
            for(String param : seperateParameter) {
                String[] keyVal = param.split("=");
                keyVal[0] = URLDecoder.decode(keyVal[0], "UTF-8");
                keyVal[1] = URLDecoder.decode(keyVal[1], "UTF-8");
                
                if(keyVal[0].startsWith("interests")) 
                    keyVal[0] = keyVal[0].substring(11) + (idx++);
                
                seperateKeyValue.put(keyVal[0], keyVal[1]);
            }
            
            System.out.println(seperateKeyValue);
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        memberService.insert(seperateKeyValue);
    }
    
    @RequestMapping("{id}")
    public Member selectOne(String id) {
        return memberService.selectOne(id);
    }
}
