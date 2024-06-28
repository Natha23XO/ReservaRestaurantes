package com.cibertec.appwebrestaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mesa {
    private Integer idMesa;
    private Integer cantSillas;
    private Sucursal sucursal;
}
