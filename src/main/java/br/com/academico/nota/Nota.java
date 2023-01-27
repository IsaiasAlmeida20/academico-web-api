package br.com.academico.nota;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "notas")
@Table(name = "notas")
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_generator")
	@SequenceGenerator(name = "nota_generator", sequenceName = "public.notas_id_seq", allocationSize = 1)
	private Long id;
    private Double valor;
    private Integer peso;

    @Column(name = "matricula_aluno")
    private Long matricula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public Nota() {
    }

    public Nota(Double valor, Integer peso) {
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Nota [peso=" + peso + ", valor=" + valor + "]";
    }

}