package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String param) {
        // null 이면 에러를 던지고 null 이 아니면 사용되어짐.
        if (param == null || param.trim().length() == 0) throw new IllegalArgumentException();
        return helloService.sayHello(param);
    }
}
