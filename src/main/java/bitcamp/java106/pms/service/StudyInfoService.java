package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.HashTag;
import bitcamp.java106.pms.domain.StudyInfo;

public interface StudyInfoService {
    // 서비스 컴포넌트에서 메서드명을 지을 때는 
    // 업무 용어를 사용하라!
    List<StudyInfo> list(int pageNo, int pageSize);
    List<StudyInfo> listSearch(String city, String county, String category, String hashtag);
    List<HashTag> listTag(int no);
    StudyInfo get(int no);
    int add(StudyInfo studyInfo);
    int addTag(String[] tag);
    Object update(StudyInfo studyInfo);
    int delete(int no);
    int getLimitOne();
    Object setPhoto(String fileName, int studyNo, Map<String, Object> data);
    Object updateTag(String[] tags);
}
