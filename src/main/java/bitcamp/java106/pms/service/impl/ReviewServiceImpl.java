package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.ReviewDao;
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
		// TODO Auto-generated method stub
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






   
}
