package com.allyson.backend.modules.usuarios.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Frontend_ID", nullable = false, unique = true, updatable = false)
    private UUID frontendId;

    @Column(name = "Backend_ID", nullable = false, length = 255)
    private String backendId;

    @Column(name = "Nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "Senha_Hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(name = "Ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "Data_Criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    void antesDeSalvar() {
        if (frontendId == null) frontendId = UUID.randomUUID();
        if (dataCriacao == null) dataCriacao = LocalDateTime.now();
    }
}