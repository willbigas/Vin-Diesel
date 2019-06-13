package br.com.vindiesel.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Produto {

    private Integer id;
    @NotBlank
    private String nome;
    @Digits(integer = 10, fraction = 0)
    private Integer codBarras;
    @DecimalMin("0.00")
    @DecimalMax("999999999.00")
    private Double valor;
    @Digits(integer = 10, fraction = 0)
    private Integer quantidade;
    @Valid
    private Categoria categoria;
    @Valid
    private Fornecedor fornecedor;
    @NotNull
    private Boolean ativo;

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

    public Integer getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Integer codBarras) {
        this.codBarras = codBarras;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", codBarras=" + codBarras + ", valor=" + valor + ", quantidade=" + quantidade + ", categoria=" + categoria + ", fornecedor=" + fornecedor + '}';
    }

}
