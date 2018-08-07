package bitcamp.java106.pms.service;


import java.util.List;

import bitcamp.java106.pms.domain.Review;

public interface ReviewService {
    Review get(int no);
    int add(Review review);
	List<Review> selectList(int pageNo, int pageSize, String category);
	List<Review> count();
	List<Review> selectListAll();
	Review countOne(String category);
	int delete(int no);
}
