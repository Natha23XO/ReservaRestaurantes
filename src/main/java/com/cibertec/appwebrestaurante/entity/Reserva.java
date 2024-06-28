package com.cibertec.appwebrestaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private Integer numeroOrden;
    private Integer cantidadComensales;
    private Horario horario;
    private Sucursal sucursal;
    private Usuario usuario;
    private Mesa mesa;
    private Date fechaReserva;
    private Estado estado;
}
