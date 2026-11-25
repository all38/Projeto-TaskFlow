package com.allyson.backend.modules.equipes.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allyson.backend.modules.equipes.dto.EquipesRequest;
import com.allyson.backend.modules.equipes.dto.EquipesResponse;
import com.allyson.backend.modules.equipes.model.EquipesModel;
import com.allyson.backend.modules.equipes.repository.EquipesRepository;
import com.allyson.backend.modules.usuarios.model.UsuariosModel;
import com.allyson.backend.modules.usuarios.repository.UsuariosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipesService {

    private final EquipesRepository equipesRepository;
    private final UsuariosRepository usuariosRepository;

    public EquipesService(EquipesRepository equipesRepository, UsuariosRepository usuariosRepository) {
        this.equipesRepository = equipesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
    public EquipesResponse criar(EquipesRequest request, Integer idUsuarioLogado) {
        UsuariosModel criador = usuariosRepository.findById(idUsuarioLogado)
                .orElseThrow(() -> new EntityNotFoundException("Criador não encontrado"));

        UsuariosModel responsavel = usuariosRepository.findByFrontendId(request.responsavelId())
                .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));

        EquipesModel equipe = new EquipesModel();
        equipe.setBackendId(UUID.randomUUID().toString());
        equipe.setNomeEquipe(request.nomeEquipe());
        equipe.setDescricaoEquipe(request.descricaoEquipe());
        equipe.setCriador(criador);
        equipe.setResponsavel(responsavel);

        return EquipesResponse.from(equipesRepository.save(equipe));
    }

    @Transactional(readOnly = true)
    public List<EquipesResponse> listar() {
        return equipesRepository.findAllComUsuarios().stream()
                .map(EquipesResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public EquipesResponse buscarPorFrontendId(UUID frontendId) {
        return EquipesResponse.from(buscarEntidade(frontendId));
    }

    @Transactional
    public EquipesResponse atualizar(UUID frontendId, EquipesRequest request) {
        EquipesModel equipe = buscarEntidade(frontendId);

        UsuariosModel responsavel = usuariosRepository.findByFrontendId(request.responsavelId())
                .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));

        equipe.setNomeEquipe(request.nomeEquipe());
        equipe.setDescricaoEquipe(request.descricaoEquipe());
        equipe.setResponsavel(responsavel);

        return EquipesResponse.from(equipe); // não precisa de save(): a entidade está gerenciada
    }

    @Transactional
    public void desativar(UUID frontendId) {
        buscarEntidade(frontendId).setAtivo(false);
    }

    private EquipesModel buscarEntidade(UUID frontendId) {
        return equipesRepository.findByFrontendId(frontendId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada"));
    }
}