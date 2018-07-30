package bitcamp.java106.pms.web.advice;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Time;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

// 해당 클래스의 내용을 적용시킬 클래스를 한정한다.
// 이 클래스는 xml파일에 정의되어있는 클래스가 아니다.
//@ControllerAdvice(assignableTypes= {TestBaseController.class})
public class GlobalBindingInitializer {
    // 컨트롤러 어드바이스 메소드를 등록하면 각 페이지 컨트롤러마다 따로 등록할 필요가 없다.
    @InitBinder
    public void customEditor(WebDataBinder binder) {
        System.out.println(binder);
        binder.registerCustomEditor(java.sql.Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                this.setValue(Date.valueOf(text));
            }
        });
        
        binder.registerCustomEditor(java.sql.Time.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                this.setValue( Time.valueOf(text) );
            }
        });
        
        binder.registerCustomEditor(java.util.ArrayList.class, new PropertyEditorSupport() {
            @Override
            public void setValue(Object value) {
                System.out.println("b" + value);
                super.setValue(value);
            }
        });
    }
}