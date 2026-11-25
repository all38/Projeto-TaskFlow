package com.allyson.backend.modules.equipes_usuarios.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse;
import com.allyson.backend.modules.equipes_usuarios.model.EquipesUsuariosModel;

public interface EquipesUsuariosRepository extends JpaRepository<EquipesUsuariosModel, Integer> {

    Optional<EquipesUsuariosModel> findByFrontendId(UUID frontendId);

    List<EquipesUsuariosModel> findByEquipe_Id(Integer equipeId);

    List<EquipesUsuariosModel> findByUsuario_Id(Integer usuarioId);

    boolean existsByEquipe_IdAndUsuario_Id(Integer equipeId, Integer usuarioId);

    @Query("""
            SELECT new com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse(
                eu.frontendId, eu.nivelAcessoTime, e.frontendId, e.nomeEquipe, e.ativo,
                e.dataCriacao, criador.nome, criador.email, resp.nome, resp.email, u.nome, u.email)
            FROM EquipesUsuariosModel eu
            JOIN eu.equipe e
            JOIN e.criador criador
            JOIN e.responsavel resp
            JOIN eu.usuario u
            """)
    List<EquipesUsuariosDetalheResponse> buscarTodasEquipesUsuarios();

    @Query("""
            SELECT new com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse(
                eu.frontendId, eu.nivelAcessoTime, e.frontendId, e.nomeEquipe, e.ativo,
                e.dataCriacao, criador.nome, criador.email, resp.nome, resp.email, u.nome, u.email)
            FROM EquipesUsuariosModel eu
            JOIN eu.equipe e
            JOIN e.criador criador
            JOIN e.responsavel resp
            JOIN eu.usuario u
            WHERE e.frontendId = :equipeId
            """)
    List<EquipesUsuariosDetalheResponse> buscarDetalhesPorEquipe(@Param("equipeId") UUID equipeId);

    @Query("""
            SELECT new com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse(
                eu.frontendId, eu.nivelAcessoTime, e.frontendId, e.nomeEquipe, e.ativo,
                e.dataCriacao, criador.nome, criador.email, resp.nome, resp.email, u.nome, u.email)
            FROM EquipesUsuariosModel eu
            JOIN eu.equipe e
            JOIN e.criador criador
            JOIN e.responsavel resp
            JOIN eu.usuario u
            WHERE u.frontendId = :usuarioId
            """)
    List<EquipesUsuariosDetalheResponse> buscarDetalhesPorUsuario(@Param("usuarioId") UUID usuarioId);
}