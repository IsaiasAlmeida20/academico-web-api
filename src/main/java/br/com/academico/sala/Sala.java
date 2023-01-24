package br.com.academico.sala;

import java.io.Serializable;

public class Sala implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private int id;
    private int numeroSala;
    private int capacidadeAlunos;
    private boolean possuiArcondicionado;
    private boolean quadroBranco;
    private boolean laboratorio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroSala() {
        return numeroSala;
    }
    
    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidadeAlunos() {
        return capacidadeAlunos;
    }

    public void setCapacidadeAlunos(int capacidadeAlunos) {
        this.capacidadeAlunos = capacidadeAlunos;
    }

    public boolean isPossuiArcondicionado() {
        return possuiArcondicionado;
    }

    public void setPossuiArcondicionado(boolean possuiArcondicionado) {
        this.possuiArcondicionado = possuiArcondicionado;
    }

    public boolean isQuadroBranco() {
        return quadroBranco;
    }

    public void setQuadroBranco(boolean quadroBranco) {
        this.quadroBranco = quadroBranco;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Sala() {
    }

    public Sala(int numeroSala, int capacidadeAlunos, boolean possuiArcondicionado, boolean quadroBranco,
            boolean laboratorio) {
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
