package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.MemberMessage;

public interface MemberMessageService {
    MemberMessage get(int no);
    int find(String id);
    int add(int sender, int receiver, String content);
    List<MemberMessage> sendList(int senderNo, int page, int pageSize);
    List<MemberMessage> receiveList(int receiverNo, int page, int pageSize);
    Object delete(int no, int type);
}