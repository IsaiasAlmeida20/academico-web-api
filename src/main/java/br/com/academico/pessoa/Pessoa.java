package br.com.academico.pessoa;

import java.io.Serializable;

import br.com.academico.endereco.Endereco;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private int matricula;
    private String nome;
    private String sobrenome;
    private int idade;
    private String naturalidade;
    private char sexo;
    private String cpf;
    private Endereco endereco;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, int idade, String naturalidade, char sexo, String cpf) {
        this.matricula = gerarMatricula();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.naturalidade = naturalidade;
        this.sexo = sexo;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Matrícula: " + this.getMatricula() + " \n";
        detalhes += "Nome: " + this.getNome() + " \n";
        detalhes += "Sobrenome: " + this.getSobrenome() + " \n";
        detalhes += "Nome Completo: " + this.getNomeCompleto() + " \n";
        detalhes += "Idade: " + this.getIdade() + " \n";
        detalhes += "Naturalidade: " + this.getNaturalidade() + " \n";
        detalhes += "Sexo: " + this.getSexo() + " \n";
        detalhes += "CPF: " + this.getCpf() + " \n";
        detalhes += this.getEndereco();
        return detalhes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) obj;
            return this.matricula == pessoa.getMatricula();
        }
        return false;
    }

    public abstract int gerarMatricula();

}