/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model;

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ADJ-PC
 */
public class Tramite {

    private Integer id;
    private LocalDateTime dataHora;
    @NotBlank
    private String nome;
    @NotBlank
    private String observacao;
    @Valid
    private Entrega entrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    @Override
    public String toString() {
        return "Tramite{" + "id=" + id + ", dataHora=" + dataHora + ", nome=" + nome + ", observacao=" + observacao + ", entrega=" + entrega + '}';
    }

}
