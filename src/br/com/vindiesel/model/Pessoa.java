package br.com.vindiesel.model;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Will
 */
public abstract class Pessoa {

    protected Integer id;
    @NotBlank
    protected String nome;
    @Valid
    protected Endereco endereco;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + '}';
    }

}
