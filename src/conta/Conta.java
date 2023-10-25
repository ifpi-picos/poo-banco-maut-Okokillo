package conta;

import cliente.Cliente;
import transacoes.Transacoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private List<Transacoes> transacoes;
    private static int numberAccount = 1;
    private int numero;
    private int agencia;
    private double saldo;
    private Cliente cliente;

    public Conta(int agencia, Cliente cliente) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.numero = numberAccount++;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public void deposita(double valor) {
        this.saldo += valor;
        addTransacoes(valor, "Depósito");
    }

    public boolean saca(double valor) {
        if (valor < this.saldo) {
            this.saldo -= valor;
            addTransacoes(valor, "Saque");
            return true;
        } else {
            return false;
        }
    }

    public void transfere(double valor, Conta conta) {
        if (this.saca(valor)) {
            conta.deposita(valor);
            addTransacoes(valor, "Transferência");
        }
    }

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