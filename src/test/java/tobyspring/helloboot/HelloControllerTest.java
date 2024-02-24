package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void hellController() {
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("test");

        Assertions.assertThat(ret).isEqualTo("test");
    }

    @Test
    void fails() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalAccessError.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalAccessError.class);

    }


}
