import java.time.LocalDate;

public class Transacao {
    private LocalDate data;
    private double valor;
    private String tipo;

    public Transacao(LocalDate data, double valor, String tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao: " +
                "Data =" + data +
                " | Valor =" + valor +
                " | Tipo ='" + tipo + '\'' +
                '}';
    }
}