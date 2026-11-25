package com.allyson.backend.modules.equipes.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.allyson.backend.modules.equipes.model.EquipesModel;

public record EquipesResponse(
        Integer id, // ID
        UUID frontendId, // Frontend_ID
        String nomeEquipe, // Nome_Equipe
        String descricaoEquipe, // Descricao_Equipe
        Integer idCriador, // ID_Criador
        Integer idResponsavel, // ID_Responsavel
        LocalDateTime dataCriacao, // Data_Criacao
        boolean ativo // Ativo
) {

    public static EquipesResponse from(EquipesModel equipe) {
        return new EquipesResponse(
                equipe.getId(),
                equipe.getFrontendId(),
                equipe.getNomeEquipe(),
                equipe.getDescricaoEquipe(),
                equipe.getCriador().getId(),
                equipe.getResponsavel().getId(),
                equipe.getDataCriacao(),
                equipe.isAtivo());
    }
}
