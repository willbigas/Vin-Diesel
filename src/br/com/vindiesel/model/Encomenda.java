package br.com.vindiesel.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Encomenda {

    private Integer id;
    @NotBlank
    private String codigoRastreio;
    @DecimalMin(value = "0.00")
    @DecimalMax("999999999.00")
    private Double peso;
    @Valid
    private Dimensao dimensao;

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

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }
    
    

}
