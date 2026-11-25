package com.allyson.backend.modules.equipes_usuarios.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.allyson.backend.modules.equipes.model.EquipesModel;
import com.allyson.backend.modules.usuarios.model.UsuariosModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Teams_Users", uniqueConstraints = @UniqueConstraint(columnNames = { "ID_Time", "ID_Usuario" }))
public class EquipesUsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Frontend_ID", nullable = false, unique = true, updatable = false)
    private UUID frontendId;

    @Column(name = "Backend_ID", nullable = false, length = 255, updatable = false)
    private String backendId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Criador", nullable = false, updatable = false)
    private UsuariosModel criador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Time", nullable = false, updatable = false)
    private EquipesModel equipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Usuario", nullable = false, updatable = false)
    private UsuariosModel usuario;

    @Column(name = "Nivel_Acesso_Time", nullable = false)
    private Integer nivelAcessoTime;

    @Column(name = "Ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "Data_Criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public EquipesUsuariosModel() {
    }

    @PrePersist
    protected void prePersist() {
        if (frontendId == null)
            frontendId = UUID.randomUUID();
        if (backendId == null)
            backendId = UUID.randomUUID().toString();
        if (dataCriacao == null)
            dataCriacao = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public UUID getFrontendId() {
        return frontendId;
    }

    public String getBackendId() {
        return backendId;
    }

    public UsuariosModel getCriador() {
        return criador;
    }

    public void setCriador(UsuariosModel criador) {
        this.criador = criador;
    }

    public EquipesModel getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipesModel equipe) {
        this.equipe = equipe;
    }

    public UsuariosModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosModel usuario) {
        this.usuario = usuario;
    }

    public Integer getNivelAcessoTime() {
        return nivelAcessoTime;
    }

    public void setNivelAcessoTime(Integer nivelAcessoTime) {
        this.nivelAcessoTime = nivelAcessoTime;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}