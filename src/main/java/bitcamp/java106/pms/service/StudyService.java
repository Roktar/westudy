package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.HashTag;
import bitcamp.java106.pms.domain.Study;
import bitcamp.java106.pms.domain.StudyInfo;

public interface StudyService {
    List<Study> list(int pageNo, int pageSize);
    List<HashTag> listTag(int no);
    Study get(int no);
    int add(Study study);
    int addTag(String[] tag);
    int update(Study studyInfo);
    int delete(int no);
    int getLimitOne();
}