package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void helloDecoretorTest() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret = helloDecorator.sayHello("dddd");
        Assertions.assertThat(ret).isEqualTo("*dddd*");
    }
}
