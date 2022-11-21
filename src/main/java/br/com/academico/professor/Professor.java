package br.com.academico.professor;

import java.util.Random;

import br.com.academico.evento.IEvento;
import br.com.academico.pessoa.Pessoa;
import br.com.academico.projeto.IProjeto;

public class Professor extends Pessoa implements IProjeto, IEvento {

    private double salario;
    private int cargaHoraria;

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor() {
        super();
        this.setMatricula(this.gerarMatricula());
    }

    public Professor(String nome, String sobrenome, int idade, String naturalidade, char sexo, String cpf, double salario, int cargaHoraria) {
        super(nome, sobrenome, idade, naturalidade, sexo, cpf);
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
        this.setMatricula(this.gerarMatricula());
    }

    @Override
    public int gerarMatricula() {
        Random gerador = new Random();
        int min = 10000000;
        int max = 99999999;
        return gerador.nextInt(max - min + 1) + min;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Salário: " + this.getSalario() + " \n";
        detalhes += "Carga Horária " + this.getCargaHoraria() + " \n";
        return detalhes;
    }

    @Override
    public void submeterProjetoExtensao() {
        System.out.println("1- Professor escolhe a area de atuação da extensão");
        System.out.println("2- Professor escreve o projeto de extensão");
        System.out.println("3- Professor seleciona alunos para o projeto, se necessário");
        System.out.println("4- Projeto é avaliado pela setor de extensão da instituição");
    }

    @Override
    public void submeterProjetoPesquisa() {
        System.out.println("1- Professor escolhe a area de pesquisa");
        System.out.println("2- Professor escreve o projeto de pesquisa");
        System.out.println("3- Professor apresenta documentação para concorrer ao finciamento da pesquisa");
        System.out.println("4- Professor seleciona alunos para o projeto, se necessário");
        System.out.println("5- Projeto é avaliado pela setor de pesquisa da instituição");
    }

    @Override
    public void inscrever() {
        System.out.println("1 -Inscrição de professor no evento");
        System.out.println("2- Gerar boleto de pagamento para o Professor");
    }

}