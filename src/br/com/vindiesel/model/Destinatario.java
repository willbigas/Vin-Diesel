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
public class Destinatario extends Pessoa {

    @NotBlank
    @Size(min = 8, max = 25)
    @Pattern(regexp = Texto.REGEX_CPF_AND_CNPJ, message = Texto.CAMPO_CPF_CNPJ)
    private String codigoPessoa;


    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }


    @Override
    public String toString() {
        return "Destinatario{" + "id=" + id + ", nome=" + nome + ", codigoPessoa=" + codigoPessoa + ", endereco=" + endereco + '}';
    }

}
