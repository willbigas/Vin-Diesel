package br.com.vindiesel.model;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author william.mauro
 */
public class Entrega {

    private Integer id;
    @DecimalMin(value = "0.00")
    @DecimalMax("999999999.00")
    private Double valorTotal;
    @NotNull
    private LocalDateTime dataCadastro;
    private LocalDateTime dataEntrega;
    @NotNull
    private Boolean entregue;
    @Valid
    private Remetente remetente;
    @Valid
    private Destinatario destinatario;
    @Valid
    private Encomenda encomenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }

    public Remetente getRemetente() {
        return remetente;
    }

    public void setRemetente(Remetente remetente) {
        this.remetente = remetente;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    @Override
    public String toString() {
        return "Entrega{" + "id=" + id + ", valorTotal=" + valorTotal + ", dataCadastro=" + dataCadastro + ", dataEntrega=" + dataEntrega + ", entregue=" + entregue + ", remetente=" + remetente + ", destinatario=" + destinatario + ", encomenda=" + encomenda + '}';
    }

}
