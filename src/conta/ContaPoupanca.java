package conta;

import cliente.Cliente;

public class ContaPoupanca extends Conta {
    private double rendimento;
    private double saldo = 0.0;

    public ContaPoupanca(int agencia, Cliente cliente, double rendimento) {
        super(agencia, cliente);
        this.rendimento = rendimento;
        this.saldo = 0.0;
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
}
