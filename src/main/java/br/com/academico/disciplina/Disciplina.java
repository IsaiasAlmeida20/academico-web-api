package br.com.academico.disciplina;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "disciplinas")
@Table(name = "disciplinas")
public class Disciplina implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplina_generator")
	@SequenceGenerator(name = "disciplina_generator", sequenceName = "public.disciplinas_id_seq", allocationSize = 1)
	private Long id;

    @Column(name = "nome_disciplina")
    private String nomeDisciplina;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Disciplina() {
    }

    public Disciplina(String nomeDisciplina, Integer cargaHoraria) {
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Disciplina: " + this.getNomeDisciplina() + "\n";
        detalhes += "Carga Horaria " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

   
}
