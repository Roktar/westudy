package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyAwaitingMember;

public interface StudyAwaitingMemberDao {
	List<StudyAwaitingMember> selectList(int no);
	int accept(Map<String, Object> paramMap);
	int insert(Map<String, Object> paramMap);
	int add(int no);
}
