package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyJoinedMember;

public interface StudyJoinedMemberDao {
	List<StudyJoinedMember> selectList();
	int delete(int no);
	StudyJoinedMember selectOne(int no);
	int insert(Map<String,Object> params);
}
