/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ADJ-PC
 */
public class Entrada {

    private Integer id;
    @NotNull
    private LocalDateTime dataEntrada;
    @Valid
    private Fornecedor fornecedor;
    @Valid
    private List<ItemEntrada> itensEntrada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<ItemEntrada> getItensEntrada() {
        return itensEntrada;
    }

    public void setItensEntrada(List<ItemEntrada> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", dataEntrada=" + dataEntrada + ", fornecedor=" + fornecedor + ", itensEntrada=" + itensEntrada + '}';
    }

}
