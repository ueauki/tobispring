package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

//@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody
    public String hello(String param) {
        // null 이면 에러를 던지고 null 이 아니면 사용되어짐.
        return helloService.sayHello(Objects.requireNonNull(param));
    }
}
