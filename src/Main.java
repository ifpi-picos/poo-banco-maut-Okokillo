import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cliente.Cliente;
import cliente.Endereco;
import conta.ContaCorrente;
import conta.ContaPoupanca;
import notificacoes.NotificacoesEmail;
import notificacoes.NotificacoesSMS;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        List<Cliente> clientes = new ArrayList<Cliente>();
        List<ContaPoupanca> contasPoupancas = new ArrayList<ContaPoupanca>();
        List<ContaCorrente> contasCorrente = new ArrayList<ContaCorrente>();

        while (true) {
            clearScreen(0);
            System.out.println("-----------------------------------");
            System.out.println("---------- MENU PRINCIPAL ---------");
            System.out.println("-----------------------------------");
            System.out.println("----- 1 -> Cadastrar Cliente ------");
            System.out.println("----- 2 -> Logar Cliente ----------");
            System.out.println("----- 0 -> Sair -------------------");
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
                    verDados(clientes, enderecos);
                    clearScreen(5);
                    break;

                case 2:
                    loginClient(scanner, clientes, contasPoupancas, contasCorrente, enderecos);
                    break;

                case 0:
                    System.out.println("Saindo...");
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
        String rua = scanner.nextLine();
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

        Endereco endereco = new Endereco(rua, bairro, cidade, estado, numero);

        enderecos.add(endereco);
    }

    public static void loginClient(Scanner scanner, List<Cliente> clientes, List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, List<Endereco> enderecos) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("------------ LOGIN ----------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o cpf do cliente: ");
        String cpf = scanner.nextLine();
        System.out.println("-----------------------------------");
        System.out.println("------------- AGUARDE -------------");
        System.out.println("-----------------------------------");
        clearScreen(2);

        Cliente client = clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (client != null) {
            System.out.print("Digite sua data de nascimento: ");
            LocalDate dn = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            clearScreen(2);

            if (client.getDataNascimento().equals(dn)) {
                clearScreen(0);
                System.out.println("-----------------------------------");
                System.out.println("------------ BEM-VINDO ------------");
                System.out.println("-----------------------------------");
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, client);
            } else {
                System.out.println("Dados incorretos.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void menuCliente(Scanner scanner, List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, List<Cliente> clientes, List<Endereco> enderecos, Cliente cliente) {
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Ver extrato");
        System.out.println("5 - Criar conta");
        System.out.println("6 - Editar dados");
        System.out.println("7 - Visualizar dados do cliente");
        System.out.println("0 - Sair da conta");
        System.out.println("-----------------------------------");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                depositar(contasPoupancas, contasCorrente, scanner);
                clearScreen(2);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 2:
                saca(contasPoupancas, contasCorrente, scanner, clientes);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 3:
                transferir(contasPoupancas, contasCorrente, scanner, clientes);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 4:
                verExtrato(contasPoupancas, contasCorrente, scanner, clientes);
                clearScreen(10);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 5:
                newConta(cliente, contasPoupancas, contasCorrente, scanner);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 6:
                editarDados(clientes, enderecos, scanner);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 7:
                visualizarDados(contasPoupancas, contasCorrente, clientes, enderecos, scanner);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;

            case 0:
                clearScreen(2);
                System.out.println("Deslogando...");
                clearScreen(2);
                break;

            default:
                clearScreen(1);
                System.out.println("Opção inválida");
                clearScreen(1);
                menuCliente(scanner, contasPoupancas, contasCorrente, clientes, enderecos, cliente);
                break;
        }
    }

    public static void newConta(Cliente cliente, List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, Scanner scanner) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("------------ NOVA CONTA -----------");
        System.out.println("-----------------------------------");
        System.out.print("Você deseja criar uma nova conta? (s/n): ");
        String opcao = scanner.next();
        if (opcao.equals("s")) {
            System.out.print("Digite o número da agência: ");
            int agencia = scanner.nextInt();
            System.out.println("-----------------------------------");
            System.out.println("Escolha o tipo da conta (Poupança ou Corrente): ");
            System.out.println("1 - Poupança");
            System.out.println("2 - Corrente");
            System.out.println("Digite a opção desejada: ");
            int tipo = scanner.nextInt();
            if (tipo == 1) {
                ContaPoupanca conta = new ContaPoupanca(agencia, cliente, new NotificacoesSMS());
                contasPoupancas.add(conta);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Número da conta: " + conta.getNumero());
                System.out.println("Saldo: " + conta.getSaldo());
                clearScreen(4);
            } else if (tipo == 2) {
                ContaCorrente conta = new ContaCorrente(agencia, cliente, new NotificacoesEmail(), 0.0);
                contasCorrente.add(conta);
                System.out.println("Conta criada com sucesso!");
                System.out.println("Número da conta: " + conta.getNumero());
                System.out.println("Saldo: " + conta.getSaldo());
                clearScreen(4);
            } else {
                System.out.println("Opção inválida.");
                clearScreen(1);
            }
        } else {
            System.out.println("Operação cancelada.");
            System.out.println("-----------------------------------");
            System.out.println("------------- AGUARDE -------------");
            System.out.println("-----------------------------------");
            clearScreen(2);
        }
    }

    public static void depositar(List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, Scanner scanner) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("----------- DEPÓSITO --------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o número da conta: ");
        int numero = scanner.nextInt();
        
        ContaPoupanca contaPoupanca = contasPoupancas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElse(null);
        
        ContaCorrente contaCorrente = contasCorrente.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElse(null);

        if (contaPoupanca != null) {
            System.out.print("Digite o valor do depósito: ");
            double valor = scanner.nextDouble();
            contaPoupanca.deposito(valor);
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Saldo atual: " + contaPoupanca.getSaldo());
        } else if (contaCorrente != null) {
            System.out.print("Digite o valor do depósito: ");
            double valor = scanner.nextDouble();
            contaCorrente.deposito(valor);
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Saldo atual: " + contaCorrente.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void saca(List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, Scanner scanner, List<Cliente> clientes) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("-------------- SAQUE --------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o cpf do cliente: ");
        String cpf = scanner.next();

        Cliente client = clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (client != null) {
            System.out.print("Digite o número da conta: ");
            int numero = scanner.nextInt();
            
            ContaPoupanca contaPoupanca = contasPoupancas.stream()
                    .filter(c -> c.getNumero() == numero)
                    .findFirst()
                    .orElse(null);
            
            ContaCorrente contaCorrente = contasCorrente.stream()
                    .filter(c -> c.getNumero() == numero)
                    .findFirst()
                    .orElse(null);

            if (contaPoupanca != null) {
                Cliente titular = contaPoupanca.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    System.out.print("Digite o valor do saque: ");
                    double valor = scanner.nextDouble();
                    contaPoupanca.saque(valor);
                    System.out.println("Saque realizado com sucesso!");
                    System.out.println("Saldo atual: " + contaPoupanca.getSaldo());
                } else {
                    System.out.println("CPF inválido para o titular da conta.");
                }
            } else if (contaCorrente != null) {
                Cliente titular = contaCorrente.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    System.out.print("Digite o valor do saque: ");
                    double valor = scanner.nextDouble();
                    contaCorrente.saque(valor);
                    System.out.println("Saque realizado com sucesso!");
                    System.out.println("Saldo atual: " + contaCorrente.getSaldo());
                } else {
                    System.out.println("CPF inválido para o titular da conta.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        }
    }

    public static void transferir(List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, Scanner scanner, List<Cliente> clientes) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("----------- TRANSFERIR ------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();

        ContaPoupanca contaPoupancaOrigem = contasPoupancas.stream()
                .filter(c -> c.getNumero() == numeroOrigem)
                .findFirst()
                .orElse(null);

        ContaCorrente contaCorrenteOrigem = contasCorrente.stream().filter(c -> c.getNumero() == numeroOrigem)
                .findFirst()
                .orElse(null);

        if (contaPoupancaOrigem != null) {
            System.out.print("Digite o CPF do cliente associado a conta de origem: ");
            String cpf = scanner.next();

            Cliente client = clientes.stream()
                    .filter(cliente -> cliente.getCpf().equals(cpf))
                    .findFirst()
                    .orElse(null);
            
            if (client != null) {
                Cliente titular = contaPoupancaOrigem.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    System.out.print("Digite o número da conta de destino: ");
                    int numeroDestino = scanner.nextInt();
                    
                    ContaPoupanca contaPoupancaDestino = contasPoupancas.stream()
                            .filter(c -> c.getNumero() == numeroDestino)
                            .findFirst()
                            .orElse(null);
                    
                    ContaCorrente contaCorrenteDestino = contasCorrente.stream()
                            .filter(c -> c.getNumero() == numeroDestino)
                            .findFirst()
                            .orElse(null);

                    if (contaPoupancaDestino != null) {
                        System.out.print("Digite o valor da transferência: ");
                        double valor = scanner.nextDouble();
                        contaPoupancaOrigem.transfere(valor, contaPoupancaDestino);
                        System.out.println("Transferência realizada com sucesso!");
                        System.out.println("Saldo atual: " + contaPoupancaOrigem.getSaldo());
                    } else if (contaCorrenteDestino != null) {
                        System.out.print("Digite o valor da transferência: ");
                        double valor = scanner.nextDouble();
                        contaPoupancaOrigem.transfere(valor, contaCorrenteDestino);
                        System.out.println("Transferência realizada com sucesso!");
                        System.out.println("Saldo atual: " + contaPoupancaOrigem.getSaldo());
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }

                } else {
                    System.out.println("CPF inválido para o titular da conta.");
                    clearScreen(2);
                }
            } else {
                System.out.println("Cliente não encontrado.");
                clearScreen(2);
            }
        } else if (contaCorrenteOrigem != null) {
            System.out.print("Digite o CPF do cliente associado a conta de origem: ");
            String cpf = scanner.next();

            Cliente client = clientes.stream()
                    .filter(cliente -> cliente.getCpf().equals(cpf))
                    .findFirst()
                    .orElse(null);
            
            if (client != null) {
                Cliente titular = contaCorrenteOrigem.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    System.out.print("Digite o número da conta de destino: ");
                    int numeroDestino = scanner.nextInt();
                    
                    ContaPoupanca contaPoupancaDestino = contasPoupancas.stream()
                            .filter(c -> c.getNumero() == numeroDestino)
                            .findFirst()
                            .orElse(null);
                    
                    ContaCorrente contaCorrenteDestino = contasCorrente.stream()
                            .filter(c -> c.getNumero() == numeroDestino)
                            .findFirst()
                            .orElse(null);

                    if (contaPoupancaDestino != null) {
                        System.out.print("Digite o valor da transferência: ");
                        double valor = scanner.nextDouble();
                        contaCorrenteOrigem.transfere(valor, contaPoupancaDestino);
                        System.out.println("Transferência realizada com sucesso!");
                        System.out.println("Saldo atual: " + contaCorrenteOrigem.getSaldo());
                    } else if (contaCorrenteDestino != null) {
                        System.out.print("Digite o valor da transferência: ");
                        double valor = scanner.nextDouble();
                        contaCorrenteOrigem.transfere(valor, contaCorrenteDestino);
                        System.out.println("Transferência realizada com sucesso!");
                        System.out.println("Saldo atual: " + contaCorrenteOrigem.getSaldo());
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                }
            }
        } else {
            System.out.println("Conta de origem não encontrada.");
            clearScreen(2);
        }
    }

    public static void verExtrato(List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, Scanner scanner, List<Cliente> clientes) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("------------ EXTRATO --------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o cpf do cliente: ");
        String cpf = scanner.next();

        Cliente client = clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (client != null) {
            System.out.print("Digite o número da conta: ");
            int numero = scanner.nextInt();
            
            ContaPoupanca contaPoupanca = contasPoupancas.stream()
                    .filter(c -> c.getNumero() == numero)
                    .findFirst()
                    .orElse(null);
            
            ContaCorrente contaCorrente = contasCorrente.stream()
                    .filter(c -> c.getNumero() == numero)
                    .findFirst()
                    .orElse(null);

            if (contaPoupanca != null) {
                Cliente titular = contaPoupanca.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    contaPoupanca.verExtrato();
                } else {
                    System.out.println("CPF inválido para o titular da conta.");
                }
            } else if (contaCorrente != null) {
                Cliente titular = contaCorrente.getCliente();
                if (titular.getCpf().equals(cpf)) {
                    contaCorrente.verExtrato();
                } else {
                    System.out.println("CPF inválido para o titular da conta.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        }
    }

    public static void verDados(List<Cliente> clientes, List<Endereco> enderecos) {
        System.out.println("-----------------------------------");
        Cliente client = clientes.get(clientes.size() - 1);
        Endereco endereco = enderecos.get(enderecos.size() - 1);
        System.out.println("Nome: " + client.getNome());
        System.out.println("CPF: " + client.getCpf());
        System.out.println("Data de nascimento: " + client.getDataNascimento());
        System.out.println("Endereço: " + endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getEstado());
        System.out.println("-----------------------------------");
        System.out.println("------------- AGUARDE -------------");
        System.out.println("-----------------------------------");
    }

    public static void visualizarDados(List<ContaPoupanca> contasPoupancas, List<ContaCorrente> contasCorrente, List<Cliente> clientes, List<Endereco> enderecos, Scanner scanner) {
        clearScreen(0);
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        boolean found = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                found = true;
                clearScreen(0);
                System.out.println("-----------------------------------");
                System.out.println("Dados do cliente:");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF: " + cliente.getCpf());
                System.out.println("Data de nascimento: " + cliente.getDataNascimento());
                System.out.println("-----------------------------------");
                Endereco endereco = cliente.getEndereco();
                System.out.println("Endereço: " + endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getEstado());
                System.out.println("-----------------------------------");
                System.out.println("Contas corrente do cliente " + cliente.getNome() + ":");
                for (ContaCorrente contaCorrente : contasCorrente) {
                    if (contaCorrente.getCliente().getCpf().equals(cpf)) {
                        System.out.println("Número da conta: " + contaCorrente.getNumero());
                        System.out.println("Saldo: " + contaCorrente.getSaldo());
                        System.out.println("-----------------------------------");
                    }
                }
                System.out.println("-----------------------------------");
                System.out.println("Contas poupança do cliente " + cliente.getNome() + ":");
                for (ContaPoupanca contaPoupanca : contasPoupancas) {
                    if (contaPoupanca.getCliente().getCpf().equals(cpf)) {
                        System.out.println("Número da conta: " + contaPoupanca.getNumero());
                        System.out.println("Saldo: " + contaPoupanca.getSaldo());
                        System.out.println("-----------------------------------");
                    }
                }
            }
            System.out.print("Aperte ENTER para prosseguir...");
            scanner.nextLine();
            clearScreen(2);
        }
        if (!found) {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void editarDados(List<Cliente> clientes, List<Endereco> enderecos, Scanner scanner) {
        clearScreen(0);
        System.out.println("-----------------------------------");
        System.out.println("------------ EDITAR ---------------");
        System.out.println("-----------------------------------");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.next();

        Cliente client = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findAny().orElse(null);

        if (client != null) {
            System.out.println("1 - Editar nome");
            System.out.println("2 - Editar data de nascimento");
            System.out.println("3 - Editar endereço");
            System.out.println("0 - Sair");
            System.out.println("Digite a opção desejada: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String nome = scanner.next();
                    client.setNome(nome);
                    System.out.println("Nome alterado com sucesso!");
                    clearScreen(2);
                    break;

                case 2:
                    System.out.print("Digite a nova data de nascimento: ");
                    LocalDate dn = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    client.setDataNascimento(dn);
                    System.out.println("Data de nascimento alterada com sucesso!");
                    clearScreen(2);
                    break;

                case 3:
                    System.out.print("Digite o novo nome da rua: ");
                    String rua = scanner.next();
                    System.out.print("Digite o novo número: ");
                    String numero = scanner.next();
                    System.out.print("Digite o novo nome do bairro: ");
                    String bairro = scanner.next();
                    System.out.print("Digite o novo nome da cidade: ");
                    String cidade = scanner.next();
                    System.out.print("Digite o novo nome do estado: ");
                    String estado = scanner.next();
                    Endereco endereco = new Endereco(rua, bairro, cidade, estado, numero);
                    client.setEndereco(endereco);
                    System.out.println("Endereço alterado com sucesso!");
                    clearScreen(2);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    clearScreen(2);
                    break;

                default:
                    System.out.println("Opção inválida");
                    clearScreen(2);
                    break;
            }

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