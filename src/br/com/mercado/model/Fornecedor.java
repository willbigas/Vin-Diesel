package br.com.mercado.model;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Fornecedor {

    private Integer id;
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @Valid
    private Endereco Endereco;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(Endereco Endereco) {
        this.Endereco = Endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
         return nome;
    }

}
