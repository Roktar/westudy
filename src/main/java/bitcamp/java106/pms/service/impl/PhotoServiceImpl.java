package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.PhotoDao;
import bitcamp.java106.pms.domain.Photo;
import bitcamp.java106.pms.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{
    
    PhotoDao photoDao;
    
    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
        
    }

    @Override
    public int insert(Photo[] photo) {
        
        for(int i=0; i< photo.length; i++) {
            photoDao.insert(photo[i]);
        }
        return 1;
    }

    @Override
    public List<Photo> listByGroup(String studyNo) {
        
        return photoDao.listByGroup(studyNo);
    }

    @Override
    public List<Photo> listByDate(String nowDate, String preDate, String studyNo) {
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("nowDate", nowDate);
            params.put("preDate", preDate);
            params.put("studyNo", studyNo);
            
            return photoDao.listByDate(params);
        }catch(Exception e){
            return null;
        }
        
    }

    @Override
    public Photo listByOne(Map<String,Object> params) {
        return photoDao.listByOne(params);
    }

    
    @Override
    public void delete(int no) {
        photoDao.delete(no);
        
    }



    

}
