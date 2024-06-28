package com.cibertec.appwebrestaurante.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private Integer idSucursal;
    private String nombreSucursal;
    private String direccion;
    private Double lat;
    private Double longitud;
    private String foto;
}
