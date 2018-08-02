package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.MemberMessage;

public interface MemberMessageDao {
    int delete(int no);
    //List<MemberMessage> selectList();
    int insert(MemberMessage memberMessage);
    List<MemberMessage> selectListSend(int senderNo);
    List<MemberMessage> selectListReceive(int receiverNo);
    MemberMessage selectOne(int no);
}
