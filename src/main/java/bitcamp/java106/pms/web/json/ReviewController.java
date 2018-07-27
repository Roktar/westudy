package bitcamp.java106.pms.web.json;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.Review;
import bitcamp.java106.pms.domain.Study;
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
     public void add(Review review) throws Exception {
             reviewService.add(review);
     }
     
    
    
    @RequestMapping("{no}")
    public Review view(@PathVariable int no) throws Exception {
        return reviewService.get(no);  
    }
    
    @RequestMapping("list")
    public List<Review> list(    
            @RequestParam("category") String name) throws Exception {
    	return reviewService.selectList(name);
    }
}
