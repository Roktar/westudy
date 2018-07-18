package bitcamp.java106.pms.web.json;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.TestBoard;
import bitcamp.java106.pms.service.TestBoardService;

@RestController
@RequestMapping("/testl")
public class TestBoardController {
    
    TestBoardService testService;
    
    public TestBoardController(TestBoardService testService) {
        this.testService = testService;
    }
      
    @RequestMapping(value="list/{page}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TestBoard> list(@PathVariable(required=false) String page) {
        
        int p = 1;
        int pageSize = 10;
        
        if(page != null)
            p = Integer.parseInt(page);
        
        System.out.println("board list 메소드 호출");
        return testService.selectList(p, pageSize);
    }
}

//ver 49 - 요청 핸들러의 파라미터 값 자동으로 주입받기
//ver 48 - CRUD 기능을 한 클래스에 합치기
//ver 47 - 애노테이션을 적용하여 요청 핸들러 다루기
//ver 46 - 페이지 컨트롤러를 POJO를 변경
//ver 45 - 프론트 컨트롤러 적용
//ver 42 - JSP 적용
//ver 40 - 필터 적용
//ver 39 - forward 적용
//ver 38 - redirect 적용
//ver 37 - BoardAddController 클래스를 서블릿으로 변경
//         출력 결과를 HTML로 변경
//         자동 Refresh 태그 추가
//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - BoardController에서 add() 메서드를 추출하여 클래스로 정의. 






