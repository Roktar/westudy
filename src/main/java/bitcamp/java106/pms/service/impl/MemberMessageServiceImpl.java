package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.MemberMessageDao;
import bitcamp.java106.pms.domain.Member;
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
    public MemberMessage get(int no) {
        return  memberMessageDao.selectOne(no);
    }

    @Override
    public Object delete(int no, int type) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("no", no);
    	params.put("type", (type == 0 ? "receive" : "send"));
    	
        return (memberMessageDao.delete(params) > 0 ? "success" : "fail");
    }

    @Override
    public int find(String id) {
        return memberDao.find(id);
    }

    @Override
    public int add(int senderNo, int receiverNo, String content) {
        
        Map<String, Object> param = new HashMap<>();
        Member sender = new Member();
        sender.setNo(senderNo);
        Member receiver = new Member();
        receiver.setNo(receiverNo);
        
        param.put("sender", sender);
        param.put("receiver", receiver);
        param.put("content", content);
        
        return memberMessageDao.add(param);
    }

    @Override
    public List<MemberMessage> sendList(int senderNo, int page, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        
        Member sender = new Member();
        sender.setNo(senderNo);
        
        params.put("sender", sender);
        params.put("startRowNo", (page -1) * pageSize);
        params.put("pageSize", pageSize);
        
        return memberMessageDao.selectListSend(params);
    }
    
    @Override
    public List<MemberMessage> receiveList(int receiverNo, int page, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        
        Member receiver = new Member();
        receiver.setNo(receiverNo);
        
        params.put("receiver", receiver);
        params.put("startRowNo", (page -1) * pageSize);
        params.put("pageSize", pageSize);
        
        return memberMessageDao.selectListReceive(params);
    }
}
