package com.allyson.backend.modules.usuarios.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allyson.backend.modules.usuarios.dto.UsuariosRequest;
import com.allyson.backend.modules.usuarios.dto.UsuariosResponse;
import com.allyson.backend.modules.usuarios.service.UsuariosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService service;

    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuariosResponse> criar(@RequestBody @Valid UsuariosRequest req) {
        UsuariosResponse criado = service.criar(req);
        URI location = URI.create("/usuarios/" + criado.frontendId());
        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping
    public List<UsuariosResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{frontendId}")
    public UsuariosResponse buscar(@PathVariable UUID frontendId) {
        return service.buscar(frontendId);
    }
}