public class Endereco {
    private String logradouro;
    private String cidade;
    private String estado;
    private String bairro;
    private String cep;
    private int numero;

    public Endereco(String logradouro, String cidade, String estado, String bairro, String cep, int numero) {
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
    }

    public Endereco copyWith() {
        return new Endereco(logradouro, cidade, estado, bairro, cep, numero);
    }
}
