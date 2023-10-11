import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private int numberAccount = 1;
    private int numero;
    private int agencia;
    private double saldo;
    private String tipo;
    private Cliente cliente;
    private List<Transacao> transacoes;

    public Conta(Cliente cliente, String tipo) {
        this.cliente = cliente;
        this.numero = numberAccount++;
        this.saldo = 0.0;
        this.tipo = tipo;
        this.transacoes = new ArrayList<>();
    }

    public Conta create() {
        return new Conta(cliente, tipo);
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor < this.saldo) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void verExtrato(List<Transacao> transacaos) {
        for(Transacao transacao : transacaos) {
            System.out.println(transacao);
            System.out.println("Saldo atual: " + this.saldo);
        }
    }

    public void addTransacoes(double valor, String tipo) {
        Transacao transacao = new Transacao(LocalDate.now(), valor, tipo);
        this.transacoes.add(transacao);
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
}
