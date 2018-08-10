package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberService {
	StudyJoinedMember selectOne(int no);
	
	//동현
	List<StudyJoinedMember> list(int no);
	int exclude(Map<String, Object> paramMap);
	int add(StudyJoinedMember studyJoinedMember, int mnumber);
	List<StudyJoinedMember> gradeList(Map<String, Object> paramMap);
	
	//하은
	List<StudyJoinedMember> get(int no);
	int update(StudyJoinedMember studyJoinedMember);
	StudyJoinedMember getLeader(int no);
	List<StudyJoinedMember> selectListMember(int no);
	int count(int no);

}
