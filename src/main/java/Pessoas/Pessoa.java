/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoas;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 *
 * @author Desktop
 */
public class Pessoa {
    private String nome ; 
    private String cpf;
    private String cep;
    private String telefone;
    
    private int idade;
    private String dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String cep, String telefone,String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setCep(cep);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    

    public int calcularIdade(String diaNascimento){
       LocalDate today = LocalDate.now();
        LocalDate nascimento = LocalDate.parse(diaNascimento,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int years = Period.between(nascimento, today).getYears();
        return years;
    }
        public void zerar(){
        setCep(null);
        setCpf(null);
        setDataNascimento(null);
        setNome(null);
        setTelefone(null);
    }
    
}
