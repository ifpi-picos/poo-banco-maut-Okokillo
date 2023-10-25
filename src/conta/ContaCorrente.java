package conta;

import cliente.Cliente;

public class ContaCorrente extends Conta{

    private double chequeEspecial;

    public ContaCorrente(int agencia, Cliente cliente) {
        super(agencia, cliente);
        this.chequeEspecial = 200.0;
    }

    public void transfere(double valor, Conta conta) {
        if (this.saca(valor)) {
            conta.deposita(valor);
            addTransacoes(valor, "Transferência");
        }
    }

    public void chequeEspecial() {
        if(super.qtdTransacoes() % 10 == 0 && super.qtdTransacoes() != 0) {
            this.chequeEspecial += 20.0;
        }
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }
}
