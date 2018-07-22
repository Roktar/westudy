package bitcamp.java106.pms.web.json;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.service.MemberService;
import net.coobird.thumbnailator.Thumbnails;

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
    
    @Autowired ServletContext sc;

    
    @PostMapping("upload04")
    public Object upload04(
            MultipartFile files) {
        
        HashMap<String,Object> jsonData = new HashMap<>();
        
        String filesDir = sc.getRealPath("/files");
        
        String filename = UUID.randomUUID().toString();
        jsonData.put("filename", filename);
        jsonData.put("filesize", files.getSize());
        jsonData.put("originname", files.getOriginalFilename());
        try {
            File path = new File(filesDir + "/" + filename);
            files.transferTo(path);
            
            // 써네일 이미지 생성
            String thumbnailPath = path.getCanonicalPath() + "_50x50";
            System.out.println(thumbnailPath);
            Thumbnails.of(path)
                      .size(50, 50)
                      .outputFormat("jpg")
                      .toFile(new File(thumbnailPath));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    
    @RequestMapping("update")
    @ResponseStatus(HttpStatus.OK)
    public void update(Member member) throws Exception {
        memberService.update(member);
    }


}
