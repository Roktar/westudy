package bitcamp.java106.pms.web.json;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.StudyJoinedMember;
import bitcamp.java106.pms.service.StudyJoinedMemberService;

@RestController
@RequestMapping("/joinedmember")
public class StudyJoinedMemberController { 
   StudyJoinedMemberService studyJoinedMemberService;

    @Autowired ServletContext sc;
    
    public StudyJoinedMemberController(StudyJoinedMemberService studyJoinedMemberService) {
        this.studyJoinedMemberService = studyJoinedMemberService;
    }
    
    @RequestMapping("delete")
	public void delete (
			@RequestParam("no") int no) throws Exception{
    	studyJoinedMemberService.delete(no);
	}
	
	@RequestMapping("list")
	 public Object selectList() {
        return studyJoinedMemberService.list();
    }
	
	@RequestMapping("one")
	 public Object selectOne(
			 @RequestParam("no") int no) {
       return studyJoinedMemberService.selectOne(no);
   }
	
	
}