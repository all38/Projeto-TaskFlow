package com.allyson.backend.modules.equipes.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allyson.backend.modules.equipes.dto.EquipesRequest;
import com.allyson.backend.modules.equipes.dto.EquipesResponse;
import com.allyson.backend.modules.equipes.service.EquipesService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/equipes")
public class EquipesController {

    private final EquipesService equipesService;

    public EquipesController(EquipesService equipesService) {
        this.equipesService = equipesService;
    }

    @PostMapping
    public ResponseEntity<EquipesResponse> criar(@RequestBody @Valid EquipesRequest request) {
        // Provisório: troque pelo ID do usuário autenticado quando tiver login
        Integer idUsuarioLogado = 1;

        EquipesResponse response = equipesService.criar(request, idUsuarioLogado);

        URI location = URI.create("/times/" + response.frontendId());
        return ResponseEntity.created(location).body(response); // 201 Created
    }
    
    @GetMapping
    public ResponseEntity<List<EquipesResponse>> listar() {
        return ResponseEntity.ok(equipesService.listar());
    }

    @GetMapping("/{frontendId}")
    public ResponseEntity<EquipesResponse> buscarPorId(@PathVariable UUID frontendId) {
        return ResponseEntity.ok(equipesService.buscarPorFrontendId(frontendId));
    }

    @PutMapping("/{frontendId}")
    public ResponseEntity<EquipesResponse> atualizar(
            @PathVariable UUID frontendId,
            @RequestBody @Valid EquipesRequest request) {
        return ResponseEntity.ok(equipesService.atualizar(frontendId, request));
    }

    @DeleteMapping("/{frontendId}")
    public ResponseEntity<Void> desativar(@PathVariable UUID frontendId) {
        equipesService.desativar(frontendId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}