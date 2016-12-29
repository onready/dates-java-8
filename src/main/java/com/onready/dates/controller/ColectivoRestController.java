package com.onready.dates.controller;

import com.onready.dates.domain.Colectivo;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColectivoRestController {
    
    @GetMapping("/api/colectivo/")
    public Colectivo obtener() {
        LocalDateTime horaDeArribo = LocalDateTime.now();
        Colectivo unColectivo = new Colectivo();
        unColectivo.setLinea(37);
        unColectivo.setHoraDeArribo(horaDeArribo);
        return unColectivo;
    }

}
