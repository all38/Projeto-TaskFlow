package com.allyson.backend.modules.equipes.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.allyson.backend.modules.usuarios.model.UsuariosModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teams")
public class EquipesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Frontend_ID", nullable = false, unique = true, updatable = false)
    private UUID frontendId;

    @Column(name = "Backend_ID", nullable = false, length = 255)
    private String backendId;

    @Column(name = "Nome_Equipe", nullable = false, length = 50)
    private String nomeEquipe;

    @Column(name = "Descricao_Equipe", length = 200)
    private String descricaoEquipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Criador", nullable = false, foreignKey = @ForeignKey(name = "FK_Teams_Users_Criador"))
    private UsuariosModel criador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Responsavel", nullable = false, foreignKey = @ForeignKey(name = "FK_Teams_Users_Responsavel"))
    private UsuariosModel responsavel;

    @Column(name = "Data_Criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "Ativo", nullable = false)
    private boolean ativo = true;

    // Construtor vazio exigido pelo JPA
    public EquipesModel() {
    }

    // Preenche os defaults que no banco são NEWID() e SYSDATETIME()
    @PrePersist
    void prePersist() {
        if (frontendId == null) {
            frontendId = UUID.randomUUID();
        }
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    // Getters e setters

    public Integer getId() {
        return id;
    }

    public UUID getFrontendId() {
        return frontendId;
    }

    public String getBackendId() {
        return backendId;
    }

    public void setBackendId(String backendId) {
        this.backendId = backendId;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getDescricaoEquipe() {
        return descricaoEquipe;
    }

    public void setDescricaoEquipe(String descricaoEquipe) {
        this.descricaoEquipe = descricaoEquipe;
    }

    public UsuariosModel getCriador() {
        return criador;
    }

    public void setCriador(UsuariosModel criador) {
        this.criador = criador;
    }

    public UsuariosModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuariosModel responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}