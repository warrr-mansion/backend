package com.warrr.zipflex.global.health;

import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<Void> checkHealthStatus() {

        return new ResponseEntity<>(OK);
    }

}
