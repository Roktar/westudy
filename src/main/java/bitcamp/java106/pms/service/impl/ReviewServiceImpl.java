package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.ReviewDao;
import bitcamp.java106.pms.dao.StudyInfoDao;
import bitcamp.java106.pms.domain.Review;
import bitcamp.java106.pms.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
    ReviewDao reviewDao;
   

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

	@Override
	public int add(Review review) {
		return reviewDao.insert(review);
	}


	@Override
	public Review get(int no) {
		return null;
	}

	@Override
	public List<Review> selectList(int pageNo, int pageSize, String category) {
		 HashMap<String,Object> params = new HashMap<>();
	        params.put("startRowNo", (pageNo - 1) * pageSize);
	        params.put("pageSize", pageSize);
	        params.put("category", category);
	        
		return reviewDao.selectList(params);
	}

	@Override
	public List<Review> count() {
		return reviewDao.count();
	}

	@Override
	public Review countOne(String category) {
		return reviewDao.countOne(category);
	}

	@Override
	public List<Review> selectListAll() {
		return reviewDao.selectListAll();
	}

	@Override
	public int delete(int no) {
	    return reviewDao.delete(no);
	}

	@Override
	public Review getMyReview(int memNo, int studyNo) {
		HashMap<String,Object> params = new HashMap<>();
       
        params.put("memNo", memNo);
        params.put("studyNo", studyNo);
     
		return reviewDao.getMyReview(params);
	}
	

    @Override
    public int update(Review review) {
        return reviewDao.update(review);
    }
    

   
}
