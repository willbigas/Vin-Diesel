package br.com.vindiesel.model;

import br.com.vindiesel.uteis.Texto;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Remetente {

    private Integer id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, max = 18)
    @Pattern(regexp = "[^0-9]", message = Texto.CAMPO_CPF_CNPJ)
    private String codigoPessoa;
    @NotBlank
    private String telefone;
    @Valid
    private Endereco Endereco;

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

    @Override
    public String toString() {
        return nome;
    }

}
