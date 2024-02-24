package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?param={param}", String.class, "spring");

        // status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

        // header text/plain
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);

        // body hello spring
        Assertions.assertThat(res.getBody()).isEqualTo("*hello spring*");

    }


    @Test
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?param=", String.class);

        // status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
