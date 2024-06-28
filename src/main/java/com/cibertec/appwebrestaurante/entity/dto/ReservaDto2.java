package com.cibertec.appwebrestaurante.entity.dto;

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
public class ReservaDto2 extends ReservaDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", locale = JsonFormat.DEFAULT_LOCALE)
    private Date horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date horaFin;
}
