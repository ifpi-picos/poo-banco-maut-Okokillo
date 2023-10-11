import java.util.ArrayList;
import java.util.List;

public class Endereco {
    List<Endereco> endereco = new ArrayList<>();

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;

    public Endereco(String rua, String bairro, String cidade, String estado, String cep, int numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
    }

    public Endereco copyWith() {
        return new Endereco(rua, bairro, cidade, estado, cep, numero);
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        if (estado.length() == 2) {
            this.estado = estado;
        } else {
            System.out.println("Estado inválido");
        }
    }

    public void setCep(String cep) {
        if (cep.length() == 8) {
            this.cep = cep;
        } else {
            System.out.println("CEP inválido");
        }
    }
}
