package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.Study;

public interface StudyService {
    List<Study> list();
    Study get(int no);
    int add(Study study);
    int update(Study study);
    int delete(int no);
}