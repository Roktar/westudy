package bitcamp.java106.pms.web.json;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.StudyJoinedMember;
import bitcamp.java106.pms.service.StudyJoinedMemberService;

@RestController
@RequestMapping("/joinedMember")
public class StudyJoinedMemberController { 
   StudyJoinedMemberService studyJoinedMemberService;

    @Autowired ServletContext sc;
    
    public StudyJoinedMemberController(StudyJoinedMemberService studyJoinedMemberService) {
        this.studyJoinedMemberService = studyJoinedMemberService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(StudyJoinedMember studyJoinedMember, int mnumber) throws Exception {
        studyJoinedMemberService.add(studyJoinedMember, mnumber);
    }
    
   @RequestMapping("update")
   	public boolean update (
   			@RequestParam("memNo") int memNo) throws Exception{
       	
       	boolean success = false;
       	
       	int chk  = studyJoinedMemberService.update(memNo);
       	System.out.println("update 성공여부  : [" + chk + "]");
       	
       	if( chk == 1 ) {
       		success = true;
       	}
       	return success;
    }
	
	@RequestMapping("list")
	 public List<StudyJoinedMember> selectList(int no) {
		List<StudyJoinedMember> list = studyJoinedMemberService.list(no); 
        return list;
    }
	
	@RequestMapping("one")
	 public Object selectOne(
			 @RequestParam("no") int no) {
       return studyJoinedMemberService.selectOne(no);
   }
	
	@RequestMapping("listall")
	 public List<StudyJoinedMember> selectListWithStudy(
			 @RequestParam("no") int mnumber) {
      return studyJoinedMemberService.selectListWithStudy(mnumber);
  }
	
	@RequestMapping("list")
	 public Object selectList() {
       return studyJoinedMemberService.list();
   }
	
	@RequestMapping("listAll")
	 public Object selectList(
			 @RequestParam("category") String category) throws Exception {
      return studyJoinedMemberService.listAll(category);
  }
	
	@RequestMapping("listAll")
	 public Object selectList(
			 @RequestParam("category") String category) throws Exception {
      return studyJoinedMemberService.listAll(category);
  }
	
}
