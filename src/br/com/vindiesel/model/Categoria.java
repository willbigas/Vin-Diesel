package br.com.vindiesel.model;

import br.com.vindiesel.uteis.Texto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author William
 */
public class Categoria {

    @NotNull(message = Texto.CATEGORIA_NULL)
    private Integer id;
    @NotBlank(message = Texto.CATEGORIA_NOME)
    @Size(min = 5, max = 50, message = Texto.CATEGORIA_NOME_TAMANHO)
    private String nome;
    @NotNull(message = Texto.CATEGORIA_ATIVO_NULO)
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
