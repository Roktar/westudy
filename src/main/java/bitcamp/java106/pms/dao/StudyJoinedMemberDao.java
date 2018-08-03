package bitcamp.java106.pms.dao;

import java.util.List;



import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberDao {
	List<StudyJoinedMember> selectList();
	int delete(int no);
	StudyJoinedMember selectOne(int no);
}
