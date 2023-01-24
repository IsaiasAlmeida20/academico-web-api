package br.com.academico.sala;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "salas")
@Table(name = "salas")
public class Sala implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_generator")
	@SequenceGenerator(name = "sala_generator", sequenceName = "public.salas_id_seq", allocationSize = 1)
	private Long id;
    
    @Column(name = "numero_sala")
    private Integer numeroSala;
    
    @Column(name = "capacidade_alunos")
    private Integer capacidadeAlunos;
    
    @Column(name = "possui_arcondicionado")
    private Boolean possuiArcondicionado;
    
    @Column(name = "quadro_branco")
    private Boolean quadroBranco;
    
    @Column(name = "laboratorio")
    private Boolean laboratorio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }
    
    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Integer getCapacidadeAlunos() {
        return capacidadeAlunos;
    }

    public void setCapacidadeAlunos(Integer capacidadeAlunos) {
        this.capacidadeAlunos = capacidadeAlunos;
    }

    public Boolean isPossuiArcondicionado() {
        return possuiArcondicionado;
    }

    public void setPossuiArcondicionado(Boolean possuiArcondicionado) {
        this.possuiArcondicionado = possuiArcondicionado;
    }

    public Boolean isQuadroBranco() {
        return quadroBranco;
    }

    public void setQuadroBranco(Boolean quadroBranco) {
        this.quadroBranco = quadroBranco;
    }

    public Boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Sala() {
    }

    public Sala(Integer numeroSala, Integer capacidadeAlunos, Boolean possuiArcondicionado, Boolean quadroBranco,
    		Boolean laboratorio) {
        this.numeroSala = numeroSala;
        this.capacidadeAlunos = capacidadeAlunos;
        this.possuiArcondicionado = possuiArcondicionado;
        this.quadroBranco = quadroBranco;
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        String detalhes = " ";
        detalhes += "Capacidade de Alunos: " + this.getCapacidadeAlunos() + "\n";
        detalhes += "Laboratorio: " + this.isLaboratorio() + "\n";
        detalhes += "Número da sala: " + this.getNumeroSala() + "\n";
        detalhes += "Possui Ar-condionado? " + this.isPossuiArcondicionado() + "\n";
        detalhes += "O quadro é branco? " + this.isQuadroBranco() + "\n";
        return detalhes;
    }
   
}
