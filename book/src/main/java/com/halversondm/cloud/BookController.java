package com.halversondm.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = "text/plain")
    public String available() {
        return "Spring in action";
    }

    @RequestMapping(value = "/checked-out", method = RequestMethod.GET, produces = "text/plain")
    public String checkedOut() {
        return "Spring Boot in Action";
    }
}
