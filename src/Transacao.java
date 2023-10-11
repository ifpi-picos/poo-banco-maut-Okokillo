import java.time.LocalDate;

public class Transacao {
    LocalDate data;
    double valor;
    String tipo;

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