package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberDao {
	List<StudyJoinedMember> selectListWithStudy(int no);
	StudyJoinedMember selectOne(int no);
	
	//동현
	List<StudyJoinedMember> selectList(int no);
	int update(int memNo);
	int insert(Map<String,Object> params);
	
	
	/*하은*/
	List<StudyJoinedMember> selectList();
    List<StudyJoinedMember> selectMyStudy(int no);
	List<StudyJoinedMember> selectListAll(String category);
}
  
   