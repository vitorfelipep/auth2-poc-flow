package com.example.springsecurityapi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/foods")
public class FoodResources {

    @GetMapping
    public ResponseEntity<List<String>> foods(String teste) {
        return ResponseEntity.ok(Arrays.asList("Macarrão com alho e frango", "Contra-filé com fritas", "Pato ao molho branco"));
    }

}
