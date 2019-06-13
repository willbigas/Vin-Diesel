/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author william.mauro
 */
public class TipoUsuario {

    private Integer id;
    @NotBlank
    private String nome;
    @NotNull
    private Boolean ativo;
    @Digits(integer = 1, fraction = 0)
    private Integer tipoPermissao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getTipoPermissao() {
        return tipoPermissao;
    }

    public void setTipoPermissao(Integer tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }

    @Override
    public String toString() {
        return nome;
    }

}
