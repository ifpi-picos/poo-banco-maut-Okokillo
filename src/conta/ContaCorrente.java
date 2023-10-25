package conta;

import cliente.Cliente;
import notificacoes.Notificacoes;

public class ContaCorrente extends Conta {

    private double chequeEspecial;

    public ContaCorrente(int agencia, Cliente cliente, Notificacoes notificacoes, double chequeEspecial) {
        super(agencia, cliente, notificacoes);
        this.chequeEspecial = chequeEspecial;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public void saque(double valor) {
        if (valor > super.getSaldo()) {
            super.saque(valor + valor * 0.1);
        } else {
            super.saque(valor);
        }
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
