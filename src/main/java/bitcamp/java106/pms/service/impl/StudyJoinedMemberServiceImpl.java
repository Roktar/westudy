package bitcamp.java106.pms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.StudyJoinedMemberDao;
import bitcamp.java106.pms.domain.StudyJoinedMember;
import bitcamp.java106.pms.service.StudyJoinedMemberService;



@Service
public class StudyJoinedMemberServiceImpl implements StudyJoinedMemberService{
    MemberDao memberDao;
    StudyJoinedMemberDao studyJoinedMemberDao;
    
	public StudyJoinedMemberServiceImpl(MemberDao memberDao, 
			StudyJoinedMemberDao studyJoinedMemberDao) {
		this.memberDao = memberDao;
		this.studyJoinedMemberDao = studyJoinedMemberDao;
	}
	
	@Override
	public List<StudyJoinedMember> list(){
		return studyJoinedMemberDao.selectList();
	}
	
	@Override
	public int delete(int no) {
		return studyJoinedMemberDao.delete(no);
	}
	
	@Override
	public StudyJoinedMember selectOne(int no) {
		return studyJoinedMemberDao.selectOne(no);
	}
}

