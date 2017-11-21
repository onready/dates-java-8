package com.onready.dates.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    
    private int line;
    private LocalDateTime arrivalTime;

}
