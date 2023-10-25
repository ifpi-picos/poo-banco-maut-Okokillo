package conta;

import cliente.Cliente;

public class ContaCorrente extends Conta{

    private double chequeEspecial;

    public ContaCorrente(int agencia, Cliente cliente, double chequeEspecial) {
        super(agencia, cliente);
        this.chequeEspecial = chequeEspecial;
    }

    public void transfere(double valor, Conta conta) {
        if (this.saca(valor)) {
            conta.deposita(valor);
            addTransacoes(valor, "Transferência");
        }
    }

}
