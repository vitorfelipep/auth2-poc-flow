package com.example.springsecurityapi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tests")
public class TestResources {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Opa!!! Acesso permitido!!");
    }

}
