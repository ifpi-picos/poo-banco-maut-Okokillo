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
            System.out.println("2 - Acessar cliente");
            System.out.println("3 - Menu do servidor");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    newEndereco(enderecos, input);
                    newCliente(input, clientes, enderecos);
                    break;

                case 2:
                    loginClient(input, clientes);
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

    public static void newCliente(Scanner input, List<Cliente> clientes, List<Endereco> enderecos) {
        System.out.print("Digite o nome do cliente: ");
        String nome = input.nextLine();
        System.out.print("Digite o cpf do cliente: ");
        String cpf = input.nextLine();
        System.out.print("Digite a data de nascimento do cliente (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(input.nextLine());

        int last = enderecos.size() - 1;

        Endereco endereco = null;

        if (last >= 0) {
            endereco = enderecos.get(last);
        } else {
            endereco = null;
        }

        Cliente cliente = new Cliente(nome, cpf, dataNascimento, endereco);

        clientes.add(cliente);
    }

    public static void newEndereco(List<Endereco> enderecos, Scanner input) {
        System.out.print("Digite o nome da rua: ");
        String rua = input.nextLine();
        System.out.print("Digite o nome do bairro: ");
        String bairro = input.nextLine();
        System.out.print("Digite o nome da cidade: ");
        String cidade = input.nextLine();
        System.out.print("Digite o nome do estado: ");
        String estado = input.nextLine();
        System.out.print("Digite o nome do cep: ");
        String cep = input.nextLine();
        System.out.print("Digite o nome do numero: ");
        int numero = input.nextInt();
        input.nextLine();

        Endereco endereco = new Endereco(rua, bairro, cidade, estado, cep, numero);

        enderecos.add(endereco);
    }

    public static void loginClient(Scanner input, List<Cliente> clientes) {
        System.out.println("Digite o nome do cliente: ");
        String name = input.nextLine();
    
        Cliente client = clientes.stream()
                .filter(cliente -> cliente.getNome().equals(name))
                .findFirst()
                .orElse(null);
    
        if (client != null) {
            System.out.println("Digite seu CPF: ");
            String cpf = input.nextLine();
    
            if (client.getCpf().equals(cpf)) {
                System.out.println("Bem-vindo(a), " + client.getNome());
            } else {
                System.out.println("CPF incorreto.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuCliente() {
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Ver extrato");
        System.out.println("0 - Sair");


    }
}
