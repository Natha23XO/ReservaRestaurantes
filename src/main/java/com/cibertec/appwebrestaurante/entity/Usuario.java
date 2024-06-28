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
public class Usuario {
    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    private String dni;
    private Date fechaNac;
    private String email;
    private String username;
    private String password;
    private Rol rol;
}
