package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(value = {
        "tacos",
        "tacos.data",
        "tacos.security",
        "tacos.domain",
        "web.api",
        "web"
})
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    // To avoid 404s when using Angular HTML 5 routing
    @Bean
    ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
        return (request, status, model) -> status == HttpStatus.NOT_FOUND
                ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
                : null;
    }

}
