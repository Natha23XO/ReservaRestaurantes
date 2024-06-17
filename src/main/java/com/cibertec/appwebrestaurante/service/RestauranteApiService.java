package com.cibertec.appwebrestaurante.service;

import com.cibertec.appwebrestaurante.entity.Restaurante;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RestauranteApiService {
    private final RestTemplate restTemplate;
    private final Storage storage;
    private final String apiUrl;

    @Autowired
    public RestauranteApiService(RestTemplate restTemplate, @Value("${api.base.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.storage = StorageOptions.getDefaultInstance().getService();
    }
    public List<Restaurante> obtenerRestaurantes() {
        ResponseEntity<List<Restaurante>> response = restTemplate.exchange(
                apiUrl + "/restaurantes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Restaurante>>() {}
        );
        return response.getBody();
    }

    public Restaurante obtenerRestauranteID(String id) {
        ResponseEntity<Restaurante> response = restTemplate.exchange(
                apiUrl + "/restaurante/"+id,
                HttpMethod.GET,
                null,
                Restaurante.class
        );
        return response.getBody();
    }


    public String guardarRestaurante(Restaurante restaurante, MultipartFile file) throws IOException {
        String fotoUrl = uploadPhoto(file);
        restaurante.setFoto(fotoUrl);
        return restTemplate.postForObject(
                apiUrl + "/restaurante",
                restaurante,
                String.class
        );
    }
    public String uploadPhoto(MultipartFile file) throws IOException {
        // Configurar las cabeceras de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Configurar el cuerpo de la solicitud como un objeto MultiValueMap
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("foto", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename(); // Nombre original del archivo
            }
        });

        // Configurar la solicitud HTTP con la cabecera y el cuerpo
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Realizar la solicitud POST y obtener la respuesta
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl + "/upload",
                HttpMethod.POST,
                requestEntity,
                String.class);

        // Manejar la respuesta
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // Devuelve la URL de la foto subida
        } else {
            throw new RuntimeException("Error al subir la foto: " + responseEntity.getStatusCode());
        }
    }


}
