package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Review;
import bitcamp.java106.pms.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
    
    ReviewService reviewService;
    
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
  
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(Review testReview) throws Exception{
    	reviewService.add(testReview);
    }
    
    
    @RequestMapping("{no}")
    public Review view(@PathVariable int no) throws Exception {
        return reviewService.get(no);
    }
}
