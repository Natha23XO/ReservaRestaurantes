package com.cibertec.appwebrestaurante.entity.dto;

import com.cibertec.appwebrestaurante.entity.Estado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private Integer numeroOrden;
    private Integer cantidadComensales;
    private Integer idHorario;
    private Integer idSucursal;
    private Integer idUsuario;
    private Integer idMesa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaReserva;
    private Estado estado;
}
