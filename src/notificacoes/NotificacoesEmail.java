package notificacoes;

public class NotificacoesEmail implements Notificacoes {
    @Override
    public void enviaNotificacao(String tipo, double valor) {
        // corpo/implementação do método
        System.out.println("enviando notificacao por email, " + tipo + ", Valor: " + valor);
    }
}
