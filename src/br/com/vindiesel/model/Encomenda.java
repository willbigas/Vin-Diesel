package br.com.vindiesel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String codigoRastreio;
    @DecimalMin(value = "0.00")
    @DecimalMax("999999999.00")
    @Column(columnDefinition = "Decimal(10,2)")
    private Double peso;
    @Valid
    @OneToOne
    @JoinColumn(name = "dimensao_id")
    private Dimensao dimensao;
    @DecimalMin(value = "0.00")
    @DecimalMax("999999999.00")
    @Column(columnDefinition = "Decimal(10,2)")
    private Double valorNotaFiscal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public Double getValorNotaFiscal() {
        return valorNotaFiscal;
    }

    public void setValorNotaFiscal(Double valorNotaFiscal) {
        this.valorNotaFiscal = valorNotaFiscal;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public String toString() {
        return codigoRastreio;
    }

}
