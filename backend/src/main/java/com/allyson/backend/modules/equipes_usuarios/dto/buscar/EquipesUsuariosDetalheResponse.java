package com.allyson.backend.modules.equipes_usuarios.dto.buscar;

import java.time.LocalDateTime;
import java.util.UUID;

public record EquipesUsuariosDetalheResponse(
        UUID equipeUsuarioId,
        Integer nivelAcesso,
        UUID equipeId,
        String nomeEquipe,
        boolean equipeAtiva,
        LocalDateTime dataCriacaoEquipe,
        String nomeCriador,
        String emailCriador,
        String nomeResponsavel,
        String emailResponsavel,
        String nomeUsuario,
        String emailUsuario) {
}
