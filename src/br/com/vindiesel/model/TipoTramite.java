package br.com.vindiesel.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Will
 */
public class TipoTramite {

    private Integer id;
    @NotBlank
    private String nome;

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

    @Override
    public String toString() {
        return "TipoTramite{" + "id=" + id + ", nome=" + nome + '}';
    }
    

}
