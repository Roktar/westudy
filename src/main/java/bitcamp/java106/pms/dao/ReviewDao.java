package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Review;

public interface ReviewDao {
    Review get(int no);
    void add(Review review);
   
}
