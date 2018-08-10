package bitcamp.java106.pms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.StudyAwaitingMemberDao;
import bitcamp.java106.pms.dao.StudyInfoDao;
import bitcamp.java106.pms.dao.StudyJoinedMemberDao;
import bitcamp.java106.pms.domain.StudyAwaitingMember;
import bitcamp.java106.pms.service.StudyAwaitingMemberService;



@Service
public class StudyAwaitingMemberServiceImpl implements StudyAwaitingMemberService{
    MemberDao memberDao;
    StudyAwaitingMemberDao studyAwaitingMemberDao;
    StudyInfoDao studyInfoDao;
    StudyJoinedMemberDao studyJoinedMemberDao; 
    
	public StudyAwaitingMemberServiceImpl(MemberDao memberDao, 
			StudyAwaitingMemberDao studyAwaitingMemberDao,
			StudyInfoDao studyInfoDao,
		    StudyJoinedMemberDao studyJoinedMemberDao) {
		this.memberDao = memberDao;
		this.studyAwaitingMemberDao = studyAwaitingMemberDao;
		this.studyInfoDao = studyInfoDao;
		this.studyJoinedMemberDao = studyJoinedMemberDao;
	}
	
	@Override
	public List<StudyAwaitingMember> list(int no){
		return studyAwaitingMemberDao.selectList(no);
	}
	
	@Override
	public int accept(java.util.Map<String, Object> paramMap) {
		System.out.println("거절임플임플 :: type = [" + paramMap.get("type") + "]");
		return studyAwaitingMemberDao.accept(paramMap);
	}
	
	@Override
	public int insert(java.util.Map<String, Object> paramMap) {
		System.out.println("수락");
		return studyAwaitingMemberDao.insert(paramMap);
	}

	@Override
	public int add(java.util.Map<String, Object> paramMap) {
		System.out.println("요청");
		return studyAwaitingMemberDao.add(paramMap);
	}
}

