/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model;

import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author william.mauro
 */
public class Despesa {

    private Integer id;
    @NotNull
    private LocalDateTime dataCadastro;
    private LocalDateTime dataPagamento;
    @NotNull
    private Date dataVencimento;

    private Double valorPago;
    @DecimalMin("0.00")
    @DecimalMax("999999999.00")
    private Double valorPagoRestante;
    private Boolean pago;
    @NotNull
    private Integer codEntrada;
    @Valid
    private TipoDespesa tipoDespesa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getValorPagoRestante() {
        return valorPagoRestante;
    }

    public void setValorPagoRestante(Double valorPagoRestante) {
        this.valorPagoRestante = valorPagoRestante;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Integer getCodEntrada() {
        return codEntrada;
    }

    public void setCodEntrada(Integer codEntrada) {
        this.codEntrada = codEntrada;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    @Override
    public String toString() {
        return "Despesa{" + "id=" + id + ", dataCadastro=" + dataCadastro + ", dataPagamento=" + dataPagamento + ", dataVencimento=" + dataVencimento + ", valorPago=" + valorPago + ", codEntrada=" + codEntrada + ", tipoDespesa=" + tipoDespesa + '}';
    }

}
