package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.MemberMessage;

public interface MemberMessageService {
    //List<MemberMessage> list();
    MemberMessage get(int no);
    int add(MemberMessage memberMessage);
    int delete(int no);
    List<MemberMessage> sendList(int senderNo);
    List<MemberMessage> receiveList(int receiverNo);
    int find(String id);
}