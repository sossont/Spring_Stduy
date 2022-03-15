package jpabook.jpashop.domain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("Home Controller");
        return "Hi";
    }
}
