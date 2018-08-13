package bitcamp.java106.pms.service;


import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Review;

public interface ReviewService {
	int add(Review review);
	List<Review> selectList(int pageNo, int pageSize, String category);
	List<Review> selectListAll();
	List<Review> count();
	Review countOne(String category);
	int delete(int no);
	int update(Review review);
	Review getMyReview(int memNo, int studyNo);
    Review get(int no);

}
