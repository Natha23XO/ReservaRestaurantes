package com.cibertec.appwebrestaurante.controller;

import com.cibertec.appwebrestaurante.entity.Restaurante;
import com.cibertec.appwebrestaurante.service.RestauranteApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class RestauranteController {

    @Autowired
    private RestauranteApiService restauranteApiService;

    @GetMapping("/restaurantes")
    public String listarRestaurantes(Model model) {
        List<Restaurante> restaurantes = restauranteApiService.obtenerRestaurantes();
        model.addAttribute("restaurantes", restaurantes);
        return "backoffice/restaurante/list-restaurantes";
    }
    @GetMapping("/restaurante/nuevo")
    public String mostrarFormularioNuevoRestaurante(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "backoffice/restaurante/agg-restaurante";
    }

    @PostMapping("/restaurante")
    public ResponseEntity<String> guardarRestaurante(@RequestParam("fotoSubir") MultipartFile file, @ModelAttribute Restaurante restaurante) {
        try {
            restauranteApiService.guardarRestaurante(restaurante,file);
            return ResponseEntity.ok("Restaurante guardado correctamente con foto");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el restaurante");
        }
    }

    @GetMapping("/restaurante/{id}")
    public String restaurante(Model model,@PathVariable String id) {
        Restaurante r = restauranteApiService.obtenerRestauranteID(id);
        String urlMaps ="https://maps.google.es/?q="+r.getLat()+","+r.getLng();
        System.out.println(urlMaps);
        model.addAttribute("restaurante",r);
        model.addAttribute("maps",urlMaps);
        return "backoffice/restaurante/item-restaurante";
    }
}
