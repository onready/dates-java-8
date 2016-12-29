package com.onready.dates.domain;

import java.time.LocalDateTime;


public class Colectivo {
    
    private int linea;
    private LocalDateTime horaDeArribo;

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public LocalDateTime getHoraDeArribo() {
        return horaDeArribo;
    }

    public void setHoraDeArribo(LocalDateTime horaDeArribo) {
        this.horaDeArribo = horaDeArribo;
    }
    
    

}
