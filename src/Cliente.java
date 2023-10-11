import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    List<Cliente> cliente = new ArrayList<>();

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Cliente copyWith() {
        return new Cliente(nome, cpf, dataNascimento, endereco);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        if (nome.length() > 3) {
            this.nome = nome;
        } else {
            System.out.println("Nome inválido");
        }
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        if (cpf.length() == 11) {
            this.cpf = cpf;
        } else {
            System.out.println("CPF inválido");
        }
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}