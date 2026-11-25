package com.allyson.backend.modules.equipes_usuarios.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record EquipesUsuariosRequest(

        @NotNull(message = "Precisa conter o ID da equipe") UUID equipeId,

        @NotNull(message = "Precisa conter o ID do usuário") UUID usuarioId,

        @NotNull(message = "Precisa conter o nível de acesso") Integer nivelAcesso

) {
}