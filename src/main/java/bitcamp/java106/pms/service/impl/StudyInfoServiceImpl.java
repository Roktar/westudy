// 업무로직 구현체 - 고객사마다 다른 구현을 할 수 있다.
package bitcamp.java106.pms.service.impl;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.HashTagDao;
import bitcamp.java106.pms.dao.StudyInfoDao;
import bitcamp.java106.pms.domain.HashTag;
import bitcamp.java106.pms.domain.StudyInfo;
import bitcamp.java106.pms.service.StudyInfoService;

@Service
public class StudyInfoServiceImpl implements StudyInfoService {

    StudyInfoDao studyInfoDao;
    HashTagDao hashTagDao;
    
    public StudyInfoServiceImpl(StudyInfoDao studyInfoDao, HashTagDao hashTagDao) {
        this.studyInfoDao = studyInfoDao;
        this.hashTagDao = hashTagDao;
    }
    
    @Override
    public List<StudyInfo> list(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        
        List<StudyInfo> lists =  studyInfoDao.selectList(params);
        
        for(StudyInfo info : lists)
            info.setTags(hashTagDao.selectList(info.getNo()));
        
        return lists;
    }
    
    @Override
    public List<StudyInfo> listSearch(String city, String county, String category, String hashtag) {
        System.out.println("service===>" + city + "," + county + "," + category + "," + hashtag);
        HashMap<String,Object> searchs = new HashMap<>();
        searchs.put("city", city);
        searchs.put("county", county);
        searchs.put("category", category);
        searchs.put("hashtag", hashtag);
        
        return studyInfoDao.selectSearchList(searchs);
    }
    
    @Override
    public List<HashTag> listTag(int no) {
        return hashTagDao.selectList(no);
    }
    

    @Override
    public StudyInfo get(int no) {
        StudyInfo info = studyInfoDao.selectOne(no);
        info.setTags(hashTagDao.selectList(no));
        return info;
    }
    
    @Override
    public int add(StudyInfo studyInfo) {
        return studyInfoDao.insert(studyInfo);
    }

    @Override
    public int addTag(String[] tag) {
        int num = studyInfoDao.selectlimitOne().getNo();
        System.out.println(num);
        for(int i = 0; i < tag.length; i++) {
            System.out.println(tag[i]);
            HashTag hashTag = new HashTag();
            hashTag.setNo(num);
            hashTag.setHashtag(tag[i]);
            hashTagDao.insert(hashTag);
        }
        return 0;
    }
    
    @Override
    public Object update(StudyInfo studyInfo) {
        return (studyInfoDao.update(studyInfo) > 0 ? "success" : "fail");
    }
    
    @Override
    public int delete(int no) {
        return studyInfoDao.delete(no);
    }

    @Override
    public int getLimitOne() {
        return studyInfoDao.selectlimitOne().getNo();
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return studyInfoDao.studyCount();
    }

    @Override
    public Object setPhoto(String fileName, int studyNo, Map<String, Object> data) {
        Map<String, Object> params = new HashMap<>();
        params.put("fileName", fileName);
        params.put("stdno", studyNo);

        try {
            Method m = studyInfoDao.getClass().getMethod("photo", Map.class);
            m.invoke(studyInfoDao, params);
        } catch(Exception e) {
            return "";
        }
        return data;
    }

    @Override
    public Object updateTag(String[] tags) {
        int no = Integer.parseInt( tags[tags.length -1].split("=")[1] );

        try {
            hashTagDao.delete(no); // 기존에 등록된 태그를 전부 지우고 새로 등록
            for(int i=0; i< tags.length -1; i++) {
                HashTag tag = new HashTag();
                tag.setNo(no);
                tag.setHashtag(URLDecoder.decode(tags[i].split("=")[1], "UTF-8"));
                hashTagDao.insert(tag);
            }
        } catch(Exception e) {
            return "fail";
        }
        return "success";
    }
    
    
    @Override
	public List<StudyInfo> selectListRandom() {
		return studyInfoDao.selectListRandom();
	}
}
