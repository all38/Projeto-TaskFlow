package com.allyson.backend.modules.equipes_usuarios.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.allyson.backend.modules.equipes_usuarios.model.EquipesUsuariosModel;

public record EquipesUsuariosResponse(
        UUID frontendId,
        UUID equipeId,
        UUID usuarioId,
        UUID criadorId,
        Integer nivelAcesso,
        LocalDateTime dataCriacao,
        boolean ativo) {

    public static EquipesUsuariosResponse from(EquipesUsuariosModel e) {
        return new EquipesUsuariosResponse(
                e.getFrontendId(),
                e.getEquipe().getFrontendId(),
                e.getUsuario().getFrontendId(),
                e.getCriador().getFrontendId(),
                e.getNivelAcessoTime(),
                e.getDataCriacao(),
                e.isAtivo());
    }
}