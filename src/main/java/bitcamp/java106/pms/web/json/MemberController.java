package bitcamp.java106.pms.web.json;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired ServletContext sc;
    
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
    
    @RequestMapping("{no}")
    public Member view(@PathVariable int no) throws Exception {
        return memberService.get(no);
    }

    @RequestMapping("change/{no}")
    public Object changePassword(@PathVariable int no, String nowPassword, String newPassword) {
        return memberService.changePassword(no, nowPassword, newPassword);
    }

    @PostMapping("photoUpload")
    public Object photoUpload(
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
            String thumbnailPath = path.getCanonicalPath() + "_300x300";
            System.out.println(thumbnailPath);
            Thumbnails.of(path)
                      .size(300, 300)
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
    
    @RequestMapping("delete")
	public void delete (
			@RequestParam("no") int no) throws Exception{
		memberService.delete(no);
	}
	
	@RequestMapping("list")
	 public Object list() {
        return memberService.list();
    }
	
    
    @PostMapping("uploadPhoto")
    public int upload01(int memberNo, int tel, String city, String county, MultipartFile files) {
        
        HashMap<String,Object> jsonData = new HashMap<>();
        
        String filesDir = sc.getRealPath("/img");
        
        String filename = UUID.randomUUID().toString();
        jsonData.put("profile", filename);
        jsonData.put("no", memberNo);
        jsonData.put("tel", "0"+ tel);
        jsonData.put("city", city);
        jsonData.put("county", county);
        try {
            File path = new File(filesDir + "/" + filename);
            files.transferTo(path);
            
            String mainthumPho = path.getCanonicalPath() + "_350x350";
            Thumbnails.of(path)
            .size(350, 350)
            .outputFormat("jpg")
            .toFile(mainthumPho);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberService.uploadPhoto(jsonData);
        return 1; 
        
    }
    
    @PostMapping("updateProfile")
    public int updateProfile(String memNo, String tel, String city, String county) throws Exception {
        
        Map<String,Object> map = new HashMap<>();
        map.put("no", memNo);
        map.put("tel", tel);
        map.put("city", city);
        map.put("county", county);
        memberService.uploadExcludePhoto(map);
        return 1;
        
    }
    
	@RequestMapping("checkId")
	public int checkId(@RequestParam("email") String email) {
		return memberService.checkId(email);
	}
	
    @PostMapping("upd_itr")
    public Object updateInterest(@RequestBody String qs, HttpSession session) {
        return memberService.updateInterest( qs.split("&"), ((Member)session.getAttribute("loginUser")).getNo() );
    }
}
