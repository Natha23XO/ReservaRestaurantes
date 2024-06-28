package com.cibertec.appwebrestaurante.controller;

import com.cibertec.appwebrestaurante.entity.Estado;
import com.cibertec.appwebrestaurante.entity.Reserva;
import com.cibertec.appwebrestaurante.entity.dto.ReservaDto2;
import com.cibertec.appwebrestaurante.service.ReservaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaApiService reservaApiService;

    @GetMapping("/home")
    public String home(Model model) {
        return "backoffice/reserva/home";
    }

    @PostMapping("/buscar")
    public String buscarPorId(Model model, @RequestParam(name = "idReserva", required = false) Integer idR){
        if(idR != null){
            Reserva r = reservaApiService.obtenerReservaPorId(idR);
            model.addAttribute("reserva",r);
        }
        return "backoffice/reserva/home";
    }

    @PostMapping("/finalizar")
    public String actualizarReserva(@RequestParam("numeroOrden") Integer reservaId,
                                    @RequestParam("idHorario") Integer idHorario) {
        ReservaDto2 r = new ReservaDto2();
        r.setEstado(Estado.FINALIZADO);
        r.setIdHorario(idHorario);
        Date horaActual = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String horaActualString = formatter.format(horaActual);
        try {
            horaActual = formatter.parse(horaActualString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        r.setHoraFin(horaActual);
        reservaApiService.actualizarReserva(reservaId, r);
        return "backoffice/reserva/home";
    }
}
