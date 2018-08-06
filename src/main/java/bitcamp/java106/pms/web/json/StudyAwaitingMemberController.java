package bitcamp.java106.pms.web.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.StudyAwaitingMember;
import bitcamp.java106.pms.service.StudyAwaitingMemberService;

@RestController
@RequestMapping("/awaitingMember")
public class StudyAwaitingMemberController { 
	StudyAwaitingMemberService studyAwaitingMemberService;

    @Autowired ServletContext sc;
    
    public StudyAwaitingMemberController(StudyAwaitingMemberService studyAwaitingMemberService) {
        this.studyAwaitingMemberService = studyAwaitingMemberService;
    }
    
    @RequestMapping("list")
	 public List<StudyAwaitingMember> selectList(int no) {
		List<StudyAwaitingMember> list = studyAwaitingMemberService.list(no); 
       return list;
    }
    
    @RequestMapping("acceptRequest")
   	public boolean accept (@RequestParam("memNo") int memNo, @RequestParam("type") int type, @RequestParam("no") int no) throws Exception{
       	boolean success = true;
       	
       	Map<String, Object>  paramMap = new HashMap<String, Object>();
       	paramMap.put("memNo", memNo);
       	paramMap.put("type", type);
       	paramMap.put("no", no);
       
   		try {
   	       	studyAwaitingMemberService.accept(paramMap);
   	       	
   	       	if ( type == 2 ) {
	       		System.out.println("type == 2 이므로 인서트까지 수행");
	   			System.out.println("memNo : [" + memNo + "] , type : [" + type + "]");
				studyAwaitingMemberService.insert(paramMap);
   	       	}
   		} catch (Exception e) {
			System.out.println("회원원장 DB에러 발생");
			success = false;
			return success;
		}
       	return success;
    }
    
    @RequestMapping("add")
    public boolean add (@RequestParam("memNo") int memNo) throws Exception{
    	boolean success = true;
       
   		try {
	   			System.out.println("memNo : [" + memNo + "]");
				studyAwaitingMemberService.add(memNo);
   		} catch (Exception e) {
			System.out.println("요청 DB에러 발생");
			success = false;
			return success;
		}
       	return success;
    }
}
