package com.onready.dates.controller;

import com.onready.dates.domain.Bus;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusRestController {
    
    @GetMapping("/api/buses/")
    public Bus obtener() {
        return new Bus(37, LocalDateTime.now());
    }

}
