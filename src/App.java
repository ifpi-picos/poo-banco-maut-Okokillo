import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        List<Cliente> clientes = new ArrayList<Cliente>();
        List<Conta> contas = new ArrayList<Conta>();

        while (true) {
            System.out.println("---------MENU---------");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    // newCliente(input, clientes, enderecos, contas);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    // public static void newCliente(Scanner input, List<Cliente> clientes, List<Endereco> enderecos, List<Conta> contas) {
    //     System.out.print("Digite o nome do cliente: ");
    //     String nome = input.nextLine();
    //     System.out.print("Digite o cpf do cliente: ");
    //     String cpf = input.nextLine();
    //     System.out.print("Digite a data de nascimento do cliente (YYYY-MM-DD): ");
    //     LocalDate dataNascimento = LocalDate.parse(input.nextLine());

    //     Cliente cliente = new Cliente(nome, cpf, dataNascimento);

    //     newEndereco(enderecos, input);

    //     Endereco endereco = enderecos.get(enderecos.size() - 1);

    //     cliente.setEndereco(endereco);

    //     clientes.add(cliente);

    //     Conta conta = new Conta(cliente, endereco);

    //     contas.add(conta);

    //     System.out.println("Cliente cadastrado com sucesso!");

    //     System.out.println("Dados do cliente: " + cliente.toString());
    // }

    // public static void newEndereco(List<Endereco> enderecos, Scanner input) {
    //     System.out.print("Digite o nome da rua: ");
    //     String rua = input.nextLine();
    //     System.out.print("Digite o nome do bairro: ");
    //     String bairro = input.nextLine();
    //     System.out.print("Digite o nome da cidade: ");
    //     String cidade = input.nextLine();
    //     System.out.print("Digite o nome do estado: ");
    //     String estado = input.nextLine();
    //     System.out.print("Digite o nome do cep: ");
    //     String cep = input.nextLine();
    //     System.out.print("Digite o nome do numero: ");
    //     int numero = input.nextInt();

    //     Endereco endereco = new Endereco(rua, bairro, cidade, estado, cep, numero);

    //     enderecos.add(endereco);
    // }
}
