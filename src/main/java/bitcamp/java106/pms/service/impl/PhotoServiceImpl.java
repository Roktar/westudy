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
    public void update(Photo photo) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public List<Photo> listByGroup(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Photo> listByDate(String nowDate, String preDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("nowDate", nowDate);
        params.put("preDate", preDate);
        
        return photoDao.listByDate(params);
    }

    @Override
    public Photo listByOne(int no) {
        return photoDao.listByOne(no);
    }

    
    @Override
    public void delete(int no) {
        photoDao.delete(no);
        
    }



    

}
