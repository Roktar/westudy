package bitcamp.java106.pms.service.impl;

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
	public List<Review> selectList(String category) {
		return reviewDao.selectList(category);
	}


   
}
