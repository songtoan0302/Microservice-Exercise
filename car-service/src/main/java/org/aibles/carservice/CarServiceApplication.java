package org.aibles.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @RequestMapping("/car/hello")
	public ResponseEntity hello(@RequestParam("id") int id){
		return ResponseEntity.ok("Hello:   "+id);
	}
    @RequestMapping("/car/balo")
    public ResponseEntity balo(){
        return ResponseEntity.ok("balo:   ");
    }
}
