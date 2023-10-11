public class Conta {
    private int numeroConta = 1;
    private String numberAgency;
    private double saldo;
    private Cliente cliente;

    public Conta(String numberAgency, double saldo, Cliente cliente) {
        this.numeroConta = numeroConta++;
        this.numberAgency = numberAgency;
        this.saldo = saldo;
        this.cliente = cliente;
    }
}
