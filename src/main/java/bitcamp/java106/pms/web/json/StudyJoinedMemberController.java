package bitcamp.java106.pms.web.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	ServletContext sc;

	public StudyJoinedMemberController(StudyJoinedMemberService studyJoinedMemberService) {
		this.studyJoinedMemberService = studyJoinedMemberService;
	}

	@RequestMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(StudyJoinedMember studyJoinedMember, int mnumber) throws Exception {
		studyJoinedMemberService.add(studyJoinedMember, mnumber);
	}

	@RequestMapping("exclude")
	public boolean update(@RequestParam("memNo") int memNo, @RequestParam("no") int no) throws Exception {

		boolean success = true;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memNo", memNo);
		paramMap.put("no", no);

		try {
			studyJoinedMemberService.exclude(paramMap);
			System.out.println("DB정송 성공");
		} catch (Exception e) {
			System.out.println("DB에러 발생");
			success = false;
			return success;
		}
		return success;

	}

	@RequestMapping("list")
	public List<StudyJoinedMember> selectList(int no) {
		List<StudyJoinedMember> list = studyJoinedMemberService.list(no);
		return list;
	}

	@RequestMapping("one")
	public Object selectOne(@RequestParam("no") int no) {
		return studyJoinedMemberService.selectOne(no);
	}

	@RequestMapping("gradeList")
	public List<StudyJoinedMember> gradeList(@RequestParam(value = "memNo", defaultValue = "1") int memNo,
			@RequestParam(value = "grade", defaultValue = "1") int grade,
			@RequestParam(value = "no", defaultValue = "1") int no) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memNo", memNo);
		paramMap.put("no", no);
		paramMap.put("grade", grade);

		System.out.println("memNo : " + memNo);
		System.out.println("no : " + no);
		System.out.println("grade : " + grade);

		List<StudyJoinedMember> gradeList = studyJoinedMemberService.gradeList(paramMap);
		System.out.println("gradeList컨트롤러");
		return gradeList;
	}

	@RequestMapping("{no}")
	public List<StudyJoinedMember> view(@PathVariable int no) throws Exception {
		return studyJoinedMemberService.get(no);
	}

	// 하은
	@RequestMapping("update") // 탈퇴
	@ResponseStatus(HttpStatus.OK)
	public void update(StudyJoinedMember studyJoinedMember) throws Exception {
		studyJoinedMemberService.update(studyJoinedMember);
	}

	@RequestMapping("leaderList")
	public StudyJoinedMember getLeader(@RequestParam("no") int no) {
		return studyJoinedMemberService.getLeader(no);
	}

	@RequestMapping("listMember")
	public List<StudyJoinedMember> selectListMember(int no) {
		List<StudyJoinedMember> list = studyJoinedMemberService.selectListMember(no);
		return list;
	}

	@RequestMapping("count")
	public int count(@RequestParam("no") int no) {
		return studyJoinedMemberService.count(no);
	}

	
	@RequestMapping("interList")
	public List<StudyJoinedMember> interList(
			@RequestParam(value="memNo", defaultValue="1") int memNo,
			@RequestParam(value="studyNo", defaultValue="1") int studyNo) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memNo", memNo);
		paramMap.put("studyNo", studyNo);
		
		List<StudyJoinedMember> interList = studyJoinedMemberService.interList(paramMap);
		System.out.println("interList 컨트롤러");
		return interList;
	}
}
