package bitcamp.java106.pms.service;


import bitcamp.java106.pms.domain.Review;

public interface ReviewService {
    
    Review get(int no);
    int add(Review review);
  
}
