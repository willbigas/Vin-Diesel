package br.com.vindiesel.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 *
 * @author William
 */
public class ItemVenda {
    
    private Integer id;
    @Digits(integer = 10, fraction = 0)
    private Integer quantidade;
    @DecimalMin(value = "0.00")
    @DecimalMax("999999999.00")
    private Double valorProduto;
    @Valid
    private Produto produto;
    @Valid
    private Venda venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
    
}
