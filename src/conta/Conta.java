package conta;

import cliente.Cliente;
import transacoes.Transacoes;
import notificacoes.Notificacoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private List<Transacoes> transacoes;
    private static int numberAccount = 1;
    private int numero;
    private int agencia;
    private double saldo;
    private Cliente cliente;
    private Notificacoes notificacoes;

    public Conta(int agencia, Cliente cliente, Notificacoes notificacoes) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.notificacoes = notificacoes;
        this.numero = numberAccount++;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public void deposita(double valor) {
        this.saldo += valor;
        addTransacoes(valor, "Depósito");
        this.notificacoes.enviaNotificacao("deposito", valor);
    }

    public void saque(double valor){
        this.saldo -=  valor;
        addTransacoes(valor, "saque");
        this.notificacoes.enviaNotificacao("saque", valor);
    }

    public abstract void transfere(double valor, Conta destino);

    public abstract void deposito(double valor);

    public void verExtrato() {
        for (Transacoes t : this.transacoes) {
            System.out.println(t);
        }
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void addTransacoes(double valor, String tipo) {
        Transacoes transacao = new Transacoes(LocalDate.now(), valor, tipo);
        this.transacoes.add(transacao);
    }

    public void setNotificacao(Notificacoes notificacoes) {
        this.notificacoes = notificacoes;
    }

    public int qtdTransacoes() {
        return this.transacoes.size();
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome(){
        return cliente.getNome();
    }

    public int getNumero() {
        return numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCpf() {
        return cliente.getCpf();
    }
}