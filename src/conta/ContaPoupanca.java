package conta;

import cliente.Cliente;
import notificacoes.Notificacoes;

public class ContaPoupanca extends Conta {

    private double rendimento;
    private double saldo;

    public ContaPoupanca(int agencia, Cliente cliente, Notificacoes notificacoes) {
        super(agencia, cliente, notificacoes);
        this.rendimento = 10;
        this.saldo = super.getSaldo();
    }


    public double getRendimento() {
        return rendimento;
    }

    public void rendimento() {
        this.saldo += this.saldo * (this.rendimento / 100);
    }


    @Override
    public void saque(double valor) {
        super.saque(valor + valor * 0.1);
    }

    @Override
    public void deposito(double valor) {
        super.deposita(valor);
    }

    @Override
    public void transfere(double valor, Conta destino) {
        super.saque(valor);
        destino.deposita(valor);
    }
}
