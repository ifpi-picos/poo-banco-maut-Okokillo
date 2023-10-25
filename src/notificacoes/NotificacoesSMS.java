package notificacoes;

public class NotificacoesSMS implements Notificacoes {

    @Override
    public void enviaNotificacao(String tipo, double valor) {
        System.out.println("enviando notificacao por SMS, "+ tipo + ", Valor: "+ valor);
    }
}
