package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Review;

public interface ReviewDao {
	int insert(Review review);
    List<Review> selectList(Map<String, Object> params);
    List<Review> selectListAll();
    List<Review> count();
    Review countOne(String category);
    int delete(int no); 
}
