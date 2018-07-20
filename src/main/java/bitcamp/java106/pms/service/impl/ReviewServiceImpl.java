package bitcamp.java106.pms.service.impl;

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
	public void add(Review review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Review get(int no) {
		// TODO Auto-generated method stub
		return null;
	}
    
   
}
