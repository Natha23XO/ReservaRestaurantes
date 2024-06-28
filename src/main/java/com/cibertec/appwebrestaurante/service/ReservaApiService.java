package com.cibertec.appwebrestaurante.service;

import com.cibertec.appwebrestaurante.entity.Reserva;
import com.cibertec.appwebrestaurante.entity.dto.ReservaDto2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservaApiService {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public ReservaApiService(RestTemplate restTemplate, @Value("${api.base.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public Reserva obtenerReservaPorId(Integer id) {
        ResponseEntity<Reserva> response = restTemplate.exchange(
                apiUrl + "/reserva/web/"+id,
                HttpMethod.GET,
                null,
                Reserva.class
        );
        return response.getBody();
    }


    public ReservaDto2 actualizarReserva(Integer reservaId, ReservaDto2 updatedReserva) {
        String url = apiUrl + "/reserva/web/" + reservaId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ReservaDto2> requestEntity = new HttpEntity<>(updatedReserva, headers);

        ResponseEntity<ReservaDto2> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                ReservaDto2.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Error al actualizar la reserva");
        }
    }
}
