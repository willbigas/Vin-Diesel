package br.com.vindiesel.model;

import br.com.vindiesel.uteis.Texto;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
@Entity
public class Destinatario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = Texto.REGEX_CPF_AND_CNPJ, message = Texto.CAMPO_CPF_CNPJ)
    private String codigoPessoa;
    @Valid
    @OneToOne
    private Endereco endereco;

    public Destinatario() {
    }

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

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
        return "Destinatario{" + "id=" + id + ", nome=" + nome + ", codigoPessoa=" + codigoPessoa + ", endereco=" + endereco + '}';
    }

}
