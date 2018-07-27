package bitcamp.java106.pms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.MemberMessageDao;
import bitcamp.java106.pms.domain.MemberMessage;
import bitcamp.java106.pms.service.MemberMessageService;

@Service
public class MemberMessageServiceImpl implements MemberMessageService {

    MemberDao memberDao;
    MemberMessageDao memberMessageDao;
    
    public MemberMessageServiceImpl(MemberDao memberDao, MemberMessageDao memberMessageDao) {
        this.memberDao = memberDao;
        this.memberMessageDao = memberMessageDao;
    }

    @Override
    public List<MemberMessage> list() {
        return memberMessageDao.selectList();
    }

    @Override
    public MemberMessage get(int no) {
        MemberMessage memberMessage = memberMessageDao.selectOne(no);
        return memberMessage;
    }

    @Override
    public int add(MemberMessage memberMessage) {
        return memberMessageDao.insert(memberMessage);
    }

    @Override
    public int delete(int no) {
        return memberMessageDao.delete(no);
    }

}
