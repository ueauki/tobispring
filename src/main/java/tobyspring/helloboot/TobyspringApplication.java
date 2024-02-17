package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@SpringBootApplication
public class TobyspringApplication {

    public HelloController helloController() {
        return new HelloController();
    }

    public SimpleHelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
//        SpringApplication.run(TobyspringApplication.class, args);

        // spring container 생성
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {

            @Override
            protected void onRefresh() {
                super.onRefresh();

                // servelet container(tomcat) 생성
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };

        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();
    }
}



//            servletContext.addServlet("hello", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
//                        String name = req.getParameter("name");
//
//                        // 응답값 바인딩
//                        resp.setStatus(HttpStatus.OK.value());
//                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
//
//                        // 등록했던 bean class 를 사용할 수 있게 get
//                        HelloController helloController = applicationContext.getBean(HelloController.class);
//
//                        String ret = helloController.hello(name);
//                        resp.getWriter().println(ret);
//
//                    }
//                    else {
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//                }
//            }
//            ).addMapping("/*");
