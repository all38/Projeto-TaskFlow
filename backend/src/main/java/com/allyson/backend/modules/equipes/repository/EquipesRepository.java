package com.allyson.backend.modules.equipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allyson.backend.modules.equipes.model.EquipesModel;

public interface EquipesRepository extends JpaRepository<EquipesModel, Integer> {

    Optional<EquipesModel> findByFrontendId(UUID frontendId);

    boolean existsByFrontendId(UUID frontendId);

    @Query("SELECT t FROM EquipesModel t JOIN FETCH t.criador JOIN FETCH t.responsavel")
    List<EquipesModel> findAllComUsuarios();
}