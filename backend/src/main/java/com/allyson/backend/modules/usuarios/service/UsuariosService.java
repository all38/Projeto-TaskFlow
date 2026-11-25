package com.allyson.backend.modules.usuarios.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.allyson.backend.modules.usuarios.dto.UsuariosRequest;
import com.allyson.backend.modules.usuarios.dto.UsuariosResponse;
import com.allyson.backend.modules.usuarios.model.UsuariosModel;
import com.allyson.backend.modules.usuarios.repository.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuariosService(UsuariosRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuariosResponse criar(UsuariosRequest req) {
        String email = req.email().trim().toLowerCase();

        if (repository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        UsuariosModel contas = new UsuariosModel();
        contas.setNome(req.nome().trim());
        contas.setEmail(email);
        contas.setSenhaHash(passwordEncoder.encode(req.senha()));
        contas.setBackendId(UUID.randomUUID().toString());

        return UsuariosResponse.from(repository.save(contas)); // INSERT
    }

    public List<UsuariosResponse> listar() {
        return repository.findAll().stream()
                .map(UsuariosResponse::from)
                .toList();
    }

    public UsuariosResponse buscar(UUID frontendId) {
        return repository.findByFrontendId(frontendId)
                .map(UsuariosResponse::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
