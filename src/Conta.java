import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private static int numberAccount = 1;
    private int numero;
    private int agencia;
    private double saldo;
    private Cliente cliente;
    private List<Transacao> transacoes;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numero = numberAccount++;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public void depositar(double valor) {
        this.saldo += valor;
        addTransacoes(valor, "Depósito");
    }

    public boolean sacar(double valor) {
        if (valor < this.saldo) {
            this.saldo -= valor;
            addTransacoes(valor, "Saque");
            return true;
        } else {
            return false;
        }
    }

    public void transferir(double valor, Conta conta) {
        if (this.sacar(valor)) {
            conta.depositar(valor);
            addTransacoes(valor, "Transferência");
        }
    }

    public void verExtrato() {
        for (Transacao t : this.transacoes) {
            System.out.println(t);
        }
        System.out.println("Saldo atual: " + this.saldo);
    }

    public void addTransacoes(double valor, String tipo) {
        Transacao transacao = new Transacao(LocalDate.now(), valor, tipo);
        this.transacoes.add(transacao);
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
