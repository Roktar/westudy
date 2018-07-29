package bitcamp.java106.pms.dao;

import java.util.List;


import bitcamp.java106.pms.domain.Review;

public interface ReviewDao {
	int insert(Review review);
    List<Review> selectList(String category);
    List<Review> count(); 
}
