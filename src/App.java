import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        List<Cliente> clientes = new ArrayList<Cliente>();
        List<Conta> contas = new ArrayList<Conta>();

        while (true) {
            clearScreen(0);
            System.out.println("-----------------------------------");
            System.out.println("---------- MENU PRINCIPAL ---------");
            System.out.println("-----------------------------------");
            System.out.println("----- 1 -> Cadastrar Cliente ------");
            System.out.println("----- 2 -> Logar Cliente ----------");
            System.out.println("----- 3 -> Sair -------------------");
            System.out.println("-----------------------------------");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clearScreen(0);
                    newEndereco(enderecos, scanner);
                    clearScreen(2);
                    newCliente(scanner, clientes, enderecos);
                    clearScreen(2);
                    verDados(contas, clientes);
                    clearScreen(5);
                    break;

                case 2:
                    loginClient(scanner, clientes, contas, enderecos);
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

    public static void newCliente(Scanner scanner, List<Cliente> clientes, List<Endereco> enderecos) {
        System.out.println("-----------------------------------");
        System.out.print("--- 2ª Etapa - Dados do Cliente ---");
        System.out.print("\nDigite o nome do cliente: ");
        String name = scanner.nextLine();
        System.out.print("Digite o cpf do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a data de nascimento do cliente (dd/mm/aaaa): ");
        LocalDate dn = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("-----------------------------------");
        System.out.println("------------- AGUARDE -------------");
        System.out.println("-----------------------------------");

        Endereco endereco = enderecos.get(enderecos.size() - 1);

        Cliente cliente = new Cliente(name, cpf, dn, endereco);

        clientes.add(cliente);
    }

    public static void newEndereco(List<Endereco> enderecos, Scanner scanner) {
        System.out.println("-----------------------------------");
        System.out.print("- 1ª Etapa - Dados de localização -");
        System.out.print("\nDigite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Digite o número: ");
        String numero = scanner.nextLine();
        System.out.print("Digite o nome do bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite o nome da cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o nome do estado: ");
        String estado = scanner.nextLine();
        System.out.println("-----------------------------------");
        System.out.println("------------- AGUARDE -------------");
        System.out.println("-----------------------------------");

        Endereco endereco = new Endereco(logradouro, bairro, cidade, estado, numero);

        enderecos.add(endereco);
    }

    public static void loginClient(Scanner scanner, List<Cliente> clientes, List<Conta> contas, List<Endereco> enderecos) {
        System.out.println("-----------------------------------");
        System.out.println("------------ LOGIN ----------------");
        System.out.println("-----------------------------------");
        System.out.println("Digite o cpf do cliente: ");
        String cpf = scanner.nextLine();
        System.out.println("-----------------------------------");
        System.out.println("------------- AGUARDE -------------");
        System.out.println("-----------------------------------");
    
        Cliente client = clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    
        if (client != null) {
            System.out.println("Digite sua data de nascimento: ");
            LocalDate dn = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    
            if (client.getDataNascimento().equals(dn)) {
                System.out.println("Bem-vindo(a), " + client.getNome());
                menuCliente(scanner, contas, clientes, enderecos, client);
            } else {
                System.out.println("Dados incorretos.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuCliente(Scanner scanner, List<Conta> contas, List<Cliente> clientes, List<Endereco> enderecos, Cliente cliente) {
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Ver extrato");
        System.out.println("5 - Criar conta");
        System.out.println("6 - Editar dados");
        System.out.println("7 - Visualizar dados do cliente");
        System.out.println("8 - Visualizar dados da conta");
        System.out.println("9 - Visualizar contas");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                deposita(null, scanner);
                break;

            case 5:
                newConta(cliente, contas, scanner);
                break;

            case 8:
                verContas(contas, clientes, enderecos, scanner);
                break;
        }
    }

    public static void newConta(Cliente cliente, List<Conta> contas, Scanner scanner) {
        System.out.println("Você deseja criar uma nova conta? (S/N) => ");
        String opc = scanner.next();

        if(opc.equals("S")) {
            Conta newConta = cliente.newConta();
            contas.add(newConta);
            System.out.println("Conta criada com sucesso.\nProprietário: " + cliente.getNome() + "\nNúmero da conta: " + contas.get(-1).getNumero());
        } else {
            System.out.println("Conta não criada!");
        }
    }

    public static void deposita(Conta conta, Scanner scanner) {
        System.out.println("Digite o valor a ser depositado: ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);
    }

    public static void verDados(List<Conta> contas, List<Cliente> clientes) {
        Cliente client = clientes.get(clientes.size() - 1);
        System.out.println("Nome: " + client.getNome());
        System.out.println("CPF: " + client.getCpf());
        System.out.println("Data de nascimento: " + client.getDataNascimento());
        System.out.println("Endereço: " + client.getEndereco());
    }

    public static void verContas(List<Conta> contas, List<Cliente> clientes, List<Endereco> enderecos, Scanner input) {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = input.next();
        boolean found = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                found = true;
                System.out.println("Dados do cliente:");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF: " + cliente.getCpf());
                System.out.println("Data de nascimento: " + cliente.getDataNascimento());
                Endereco endereco = cliente.getEndereco();
                System.out.println("Endereço: " + endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getEstado());
                System.out.println("Contas do cliente " + cliente.getNome() + ":");
                for (Conta conta : contas) {
                    if (conta.getCliente().equals(cliente)) {
                        System.out.println("Número da conta: " + conta.getNumero());
                        System.out.println("Saldo: " + conta.getSaldo());
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void clearScreen(int cooldown) {
        try {
            Thread.sleep(cooldown * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
