package br.com.academico.disciplina;

import java.io.Serializable;

public class Disciplina implements Serializable{

    private int id;
    private String nomeDisciplina;
    private int cargaHoraria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Disciplina() {
    }

    public Disciplina(String nomeDisciplina, int cargaHoraria) {
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        String detalhes = " ";
        detalhes += "Disciplina: " + this.getNomeDisciplina() + "\n";
        detalhes += "Carga Horaria " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

   
}
