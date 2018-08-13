package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyAwaitingMember;

public interface StudyAwaitingMemberService {
	List<StudyAwaitingMember> list(int no);
	int accept(Map<String, Object> paramMap);
	int insert(Map<String, Object> paramMap);
	
	int add(Map<String, Object> paramMap);
} 
 