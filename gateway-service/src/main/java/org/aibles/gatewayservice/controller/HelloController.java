package org.aibles.gatewayservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author toanns
 */
@RestController
public class HelloController {
    @GetMapping
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello everyone");
    }
}
