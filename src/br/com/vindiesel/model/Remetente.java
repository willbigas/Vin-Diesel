package br.com.vindiesel.model;

import br.com.vindiesel.uteis.Texto;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Remetente extends Pessoa {

    @Pattern(regexp = Texto.REGEX_CPF_AND_CNPJ, message = Texto.CAMPO_CPF_CNPJ)
    private String codigoPessoa;
    @NotBlank
    private String telefone;

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

    @Override
    public String toString() {
        return nome;
    }

}
