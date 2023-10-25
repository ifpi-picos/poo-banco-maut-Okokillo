package conta;

import cliente.Cliente;

public class ContaPoupanca extends Conta {

    private double rendimento;
    private double saldo;

    public ContaPoupanca(int agencia, Cliente cliente) {
        super(agencia, cliente);
        this.rendimento = 10;
        this.saldo = super.getSaldo();
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

    public double getRendimento() {
        return rendimento;
    }

    public void rendimento() {
        this.saldo += this.saldo * (this.rendimento / 100);
    }
}
