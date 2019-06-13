/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 *
 * @author william.mauro
 */
public class ItemEntrada {

    private Integer id;
    @DecimalMin("0.00")
    @DecimalMax("999999999.00")
    private Double valorProduto;
    @Digits(integer = 10, fraction = 0)
    private Integer quantidade;
    @Digits(integer = 10, fraction = 0)
    private Integer numeroLote;
    @Valid
    private Entrada entrada;
    @Valid
    private Produto produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(Integer numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemEntrada{" + "id=" + id + ", valorProduto=" + valorProduto + ", quantidade=" + quantidade + ", numeroLote=" + numeroLote + ", entrada=" + entrada + ", produto=" + produto + '}';
    }

}
