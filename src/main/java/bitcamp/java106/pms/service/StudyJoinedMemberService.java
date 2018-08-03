package bitcamp.java106.pms.service;

import java.util.List;


import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberService {
	List<StudyJoinedMember> list();
	StudyJoinedMember selectOne(int no);
	int delete(int no);
	int add(StudyJoinedMember studyJoinedMember, int mnumber);
	List<StudyJoinedMember> selectListWithStudy(int mnumber);
} 
 