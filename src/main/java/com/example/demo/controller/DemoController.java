package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remove")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> removeLastOnly(@RequestParam String word) {
        if (word == null || word.length() < 2) {
            return ResponseEntity.badRequest().body("Input must be at least 2 characters long.");
        } else if (word.length() == 2) {
            return ResponseEntity.ok("");
        } else {
                String modified = word.substring(1, word.length() - 1);
            return ResponseEntity.ok(modified);
        }

    }
}
