
package bd;

import javax.ejb.Stateless;

/**
 *
 * @author tassyosantana
 */
@Stateless
public class Categoria {
    private Integer id;
    private String nome;
    private Float valor;
    private String dataAgendada;
    private String tipo;
    private Integer frequencia_id;

    public Categoria(){

    }

    public Integer getId() {
        return this.id;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(String dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFrequencia_id() {
        return frequencia_id;
    }

    public void setFrequencia_id(Integer frequencia_id) {
        this.frequencia_id = frequencia_id;
    }
  
}
