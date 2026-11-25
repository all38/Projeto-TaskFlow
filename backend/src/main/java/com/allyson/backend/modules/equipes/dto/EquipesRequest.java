package com.allyson.backend.modules.equipes.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EquipesRequest(

        @NotBlank(message = "O nome da equipe é obrigatório") @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres") String nomeEquipe,

        @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres") String descricaoEquipe,

        @NotNull(message = "O responsável é obrigatório") UUID responsavelId) {
}
