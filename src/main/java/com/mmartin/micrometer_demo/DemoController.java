package com.mmartin.micrometer_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/spans")
    public String spans() {
        return "Here is your span";
    }
}
