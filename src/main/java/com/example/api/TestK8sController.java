package com.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestK8sController {

    @GetMapping
    String hello() {
        return "HELLO KUBE"; // git flow feature
    }

}
