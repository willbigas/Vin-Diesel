package br.com.vindiesel.model;

import br.com.vindiesel.uteis.Texto;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Destinatario {

    private Integer id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, max = 21)
    @Pattern(regexp = "^([0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}|[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2})$", message = Texto.CAMPO_CPF_CNPJ)
    private String codigoPessoa;
    @Valid
    private Endereco endereco;

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

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Destinatario{" + "id=" + id + ", nome=" + nome + ", codigoPessoa=" + codigoPessoa + ", endereco=" + endereco + '}';
    }

}
