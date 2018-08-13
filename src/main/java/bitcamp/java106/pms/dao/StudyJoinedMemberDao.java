package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberDao {
	StudyJoinedMember selectOne(int no);
	
	//동현
	List<StudyJoinedMember> selectList(int no);
	int exclude(Map<String, Object> parammap);
	int insert(Map<String,Object> params);
	List<StudyJoinedMember> gradeList(Map<String, Object> paramMap);
	
	//하은
	int update(StudyJoinedMember studyJoinedMember); /*탈퇴*/
	List<StudyJoinedMember> selectMyStudy(int no);
	StudyJoinedMember getLeader(int no);
	List<StudyJoinedMember> selectListMember(int no); 
	int count(int no);

}
