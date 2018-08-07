package bitcamp.java106.pms.service;

import java.util.List;


import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberService {
	StudyJoinedMember selectOne(int no);
	List<StudyJoinedMember> selectListWithStudy(int mnumber);
	
	//동현
	List<StudyJoinedMember> list(int no);
	int update(int memNo);
	int add(StudyJoinedMember studyJoinedMember, int mnumber);
	
	/*하은*/
	List<StudyJoinedMember> list();
	List<StudyJoinedMember> get(int no);
	List<StudyJoinedMember> listAll(String category);
} 
 