package com.allyson.backend.modules.usuarios.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.allyson.backend.modules.usuarios.model.UsuariosModel;

public record UsuariosResponse(
        UUID frontendId,
        String nome,
        String email,
        boolean ativo,
        LocalDateTime dataCriacao
) {
    public static UsuariosResponse from(UsuariosModel u) {
        return new UsuariosResponse(
                u.getFrontendId(), u.getNome(), u.getEmail(),
                u.isAtivo(), u.getDataCriacao());
    }
}