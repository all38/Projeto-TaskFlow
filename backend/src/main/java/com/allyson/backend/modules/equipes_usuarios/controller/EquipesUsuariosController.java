package com.allyson.backend.modules.equipes_usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allyson.backend.modules.equipes_usuarios.dto.EquipesUsuariosRequest;
import com.allyson.backend.modules.equipes_usuarios.dto.EquipesUsuariosResponse;
import com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse;
import com.allyson.backend.modules.equipes_usuarios.service.EquipesUsuariosService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/equipes-usuarios")
public class EquipesUsuariosController {

    private final EquipesUsuariosService equipesUsuariosService;

    public EquipesUsuariosController(EquipesUsuariosService equipesUsuariosService) {
        this.equipesUsuariosService = equipesUsuariosService;
    }

    @PostMapping
    public ResponseEntity<EquipesUsuariosResponse> inserirUsuarioEquipe(
            @RequestBody @Valid EquipesUsuariosRequest request) {

        UUID frontendIdUser = UUID.fromString("47B1B26B-7761-4D94-BEAB-00964332010B"); // provisório

        EquipesUsuariosResponse response = equipesUsuariosService.criar(request, frontendIdUser);
        URI location = URI.create("/equipes-usuarios/" + response.frontendId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/encontrar-todos")
    public List<EquipesUsuariosDetalheResponse> getTodos() {
        return equipesUsuariosService.buscarTodasEquipesUsuarios();
    }

    @GetMapping("/equipe/{equipeId}")
    public List<EquipesUsuariosDetalheResponse> listarPorEquipe(@PathVariable UUID equipeId) {
        return equipesUsuariosService.listarPorEquipe(equipeId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<EquipesUsuariosDetalheResponse> listarPorUsuario(@PathVariable UUID usuarioId) {
        return equipesUsuariosService.listarPorUsuario(usuarioId);
    }
}
