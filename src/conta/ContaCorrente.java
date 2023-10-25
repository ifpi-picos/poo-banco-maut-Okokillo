package conta;

import cliente.Cliente;

public class ContaCorrente extends Conta{

    private double chequeEspecial;

    public ContaCorrente(int agencia, Cliente cliente) {
        super(agencia, cliente);
        this.chequeEspecial = chequeEspecial();
    }

    public boolean saca(double valor) {
        if (valor < super.getSaldo() + this.chequeEspecial) {
            super.setSaldo(super.getSaldo() - valor);
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

    public double chequeEspecial() {
        if(super.qtdTransacoes() % 10 == 0 && super.qtdTransacoes() != 0) {
            this.chequeEspecial += 20.0;
        }
        return chequeEspecial;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }
}
