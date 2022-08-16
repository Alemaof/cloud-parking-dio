package dio.digitalinnovation.parking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class HelloController {

    @GetMapping
    public String hello() {
        return "This is a study project from DIO, Digital Innovation one. \n" +
                "\n" +
                "It's a parking DB using Java with Spring Boot, JPA and Security. Integrated with PostgreSQL.\n" +
                "\n" +
                "You can check the code on https://github.com/Alemaof/cloud-parking-dio\n" +
                "\n" +
                "You can check the DB on https://parking-fnf.herokuapp.com/swagger-ui.html";
    }

}
