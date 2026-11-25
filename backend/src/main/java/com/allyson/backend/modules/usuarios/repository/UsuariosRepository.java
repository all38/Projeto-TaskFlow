package com.allyson.backend.modules.usuarios.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allyson.backend.modules.usuarios.model.UsuariosModel;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Integer> {
    boolean existsByEmail(String email);
    Optional<UsuariosModel> findByEmail(String email);
    Optional<UsuariosModel> findByFrontendId(UUID frontendId);
    boolean existsByFrontendId(UUID usuarioId);
}
