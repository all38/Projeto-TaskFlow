package com.allyson.backend.modules.equipes_usuarios.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.allyson.backend.modules.equipes.model.EquipesModel;
import com.allyson.backend.modules.equipes.repository.EquipesRepository;
import com.allyson.backend.modules.equipes_usuarios.dto.EquipesUsuariosRequest;
import com.allyson.backend.modules.equipes_usuarios.dto.EquipesUsuariosResponse;
import com.allyson.backend.modules.equipes_usuarios.dto.buscar.EquipesUsuariosDetalheResponse;
import com.allyson.backend.modules.equipes_usuarios.model.EquipesUsuariosModel;
import com.allyson.backend.modules.equipes_usuarios.repository.EquipesUsuariosRepository;
import com.allyson.backend.modules.usuarios.model.UsuariosModel;
import com.allyson.backend.modules.usuarios.repository.UsuariosRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipesUsuariosService {

    private final EquipesUsuariosRepository equipesUsuariosRepository;
    private final EquipesRepository equipesRepository;
    private final UsuariosRepository usuariosRepository;

    public EquipesUsuariosService(EquipesUsuariosRepository equipesUsuariosRepository,
            EquipesRepository equipesRepository,
            UsuariosRepository usuariosRepository) {
        this.equipesUsuariosRepository = equipesUsuariosRepository;
        this.equipesRepository = equipesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    /**
     * @param criadorFrontendId UUID do usuário autenticado (vem do token/sessão,
     *                          nunca do body da requisição).
     */
    @Transactional
    public EquipesUsuariosResponse criar(EquipesUsuariosRequest request, UUID criadorFrontendId) {

        UsuariosModel criador = usuariosRepository.findByFrontendId(criadorFrontendId)
                .orElseThrow(() -> new EntityNotFoundException("Criador não encontrado"));

        UsuariosModel usuario = usuariosRepository.findByFrontendId(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        EquipesModel equipe = equipesRepository.findByFrontendId(request.equipeId())
                .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada"));

        if (equipesUsuariosRepository.existsByEquipe_IdAndUsuario_Id(equipe.getId(), usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já pertence a esta equipe");
        }

        EquipesUsuariosModel equipeUsuarios = new EquipesUsuariosModel();
        equipeUsuarios.setCriador(criador);
        equipeUsuarios.setEquipe(equipe);
        equipeUsuarios.setUsuario(usuario);
        equipeUsuarios.setNivelAcessoTime(request.nivelAcesso());

        return EquipesUsuariosResponse.from(equipesUsuariosRepository.save(equipeUsuarios));
    }

    @Transactional(readOnly = true)
    public List<EquipesUsuariosDetalheResponse> buscarTodasEquipesUsuarios(){
        return equipesUsuariosRepository.buscarTodasEquipesUsuarios();
    }

    // Para pesquisa detalhada
    @Transactional(readOnly = true)
    public List<EquipesUsuariosDetalheResponse> listarPorEquipe(UUID equipeId) {
        if (!equipesRepository.existsByFrontendId(equipeId)) {
            throw new EntityNotFoundException("Equipe não encontrada");
        }
        return equipesUsuariosRepository.buscarDetalhesPorEquipe(equipeId);
    }

    @Transactional(readOnly = true)
    public List<EquipesUsuariosDetalheResponse> listarPorUsuario(UUID usuarioId) {
        if (!usuariosRepository.existsByFrontendId(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return equipesUsuariosRepository.buscarDetalhesPorUsuario(usuarioId);
    }
}