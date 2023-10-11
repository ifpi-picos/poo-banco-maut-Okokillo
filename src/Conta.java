public class Conta {
    private int numberAccount = 1;
    private int numero;
    private int agencia;
    private double saldo;
    private String tipo;
    private Cliente cliente;

    public Conta(Cliente cliente, String tipo) {
        this.cliente = cliente;
        this.numero = numberAccount++;
        this.saldo = 0.0;
        this.tipo = tipo;
    }

    public Conta copyWith() {
        return new Conta(cliente, tipo);
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
