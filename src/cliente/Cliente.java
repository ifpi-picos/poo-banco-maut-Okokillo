package cliente;

import conta.ContaCorrente;
import conta.ContaPoupanca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private List<ContaPoupanca> contasPoupancas;
    private List<ContaCorrente> contasCorrentes;
    private String nome;
    private final String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contasPoupancas = new ArrayList<>();
        this.contasCorrentes = new ArrayList<>();
    }

    public Cliente create() {
        return new Cliente(nome, cpf, dataNascimento, endereco);
    }

    public List<ContaPoupanca> getContasPoupancas() {
        return contasPoupancas;
    }

    public List<ContaCorrente> getContasCorrentes() {
        return contasCorrentes;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        if (nome.length() > 3) {
            this.nome = nome;
        } else {
            System.out.println("Nome inválido");
        }
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}