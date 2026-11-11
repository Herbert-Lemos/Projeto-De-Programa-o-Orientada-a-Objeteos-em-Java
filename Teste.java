import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pessoa> listaUsuarios = new ArrayList<>();

        int opcoes;
        do {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1 - Cadastrar Novo Usuario");
            System.out.println("2 - Entrar com Cadastro (Login por E-mail)");
            System.out.println("3 - SAIR");
            System.out.print("Escolha uma das opcoes: ");

            opcoes = lerInt(scanner);
            scanner.nextLine();

            switch (opcoes) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    
                    String email;
                    while (true) {
                        System.out.print("Digite o e-mail (deve conter @ e .): ");
                        email = scanner.nextLine();
                        if (Pessoa.validarEmail(email)) {
                            break;
                        }
                        System.out.println("E-mail invalido! Tente novamente.");
                    }
                    
                    listaUsuarios.add(new Pessoa(nome, email));
                    System.out.println("Usuario cadastrado com sucesso!");
                    break;

                case 2:
                    if (listaUsuarios.isEmpty()) {
                        System.out.println("\nNenhum usuario cadastrado ainda. Cadastre-se primeiro!");
                        break;
                    }
                    System.out.print("Digite o e-mail cadastrado para entrar: ");
                    String emailLogin = scanner.nextLine();
                    
                    Pessoa usuarioLogado = null;
                    for (Pessoa p : listaUsuarios) {
                        if (p.email.equalsIgnoreCase(emailLogin)) {
                            usuarioLogado = p;
                            break;
                        }
                    }

                    if (usuarioLogado != null) {
                        System.out.println("\nBem-vindo(a), " + usuarioLogado.nome + "!");
                        ListaCompras listaDAO = new ListaCompras(usuarioLogado);
                        menu(scanner, listaDAO);
                    } else {
                        System.out.println("E-mail nao encontrado nos cadastros.");
                    }
                    break;

                case 3:
                    System.out.println("Sistema Encerrado.");
                    break;

                default:
                    System.out.println("Opcao Invalida! Digite novamente.");
            }

        } while (opcoes != 3);

        scanner.close();
    }

    public static void menu(Scanner scanner, ListaCompras listaDAO) {
        int opcao;
        do {
            System.out.println("\n===== GERENCIAR LISTA DE COMPRAS =====");
            System.out.println("1 - Cadastrar Item");
            System.out.println("2 - Listar Itens");
            System.out.println("3 - Editar Item (Apenas Valor e Quantidade)");
            System.out.println("4 - Excluir Item");
            System.out.println("5 - Marcar/Desmarcar Comprado");
            System.out.println("6 - Ver Resumo Financeiro e Parcelas");
            System.out.println("7 - Limpar Lista Inteira");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInt(scanner);
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do item: ");
                    String nome = scanner.nextLine();
                    System.out.print("Valor unitario: R$");
                    double valor = lerDouble(scanner);
                    System.out.print("Quantidade: ");
                    int qtd = lerInt(scanner);
                    scanner.nextLine();
                    
                    listaDAO.cadastrar(new Item(0, nome, valor, qtd));
                    break;

                case 2:
                    System.out.println("\n--- ITENS NA LISTA ---");
                    List<Item> itens = listaDAO.listar();
                    if (itens.isEmpty()) {
                        System.out.println("A lista esta vazia.");
                    } else {
                        for (Item item : itens) {
                            item.exibirItem();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do item que deseja editar: ");
                    int idEditar = lerInt(scanner);
                    System.out.print("Novo valor unitario (R$): ");
                    double novoValor = lerDouble(scanner);
                    System.out.print("Nova quantidade: ");
                    int novaQtd = lerInt(scanner);
                    scanner.nextLine();

                    listaDAO.editar(idEditar, novoValor, novaQtd);
                    break;

                case 4:
                    System.out.print("Digite o ID do item que deseja excluir: ");
                    int idExcluir = lerInt(scanner);
                    scanner.nextLine();
                    listaDAO.excluir(idExcluir);
                    break;

                case 5:
                    System.out.print("Digite o ID do item para marcar/desmarcar: ");
                    int idMarcar = lerInt(scanner);
                    scanner.nextLine();
                    listaDAO.marcarComprado(idMarcar);
                    break;

                case 6:
                    System.out.print("Deseja simular em quantas parcelas (1 para a vista)? ");
                    int parcelas = lerInt(scanner);
                    scanner.nextLine();
                    listaDAO.exibirResumoFinanceiro(parcelas);
                    break;

                case 7:
                    listaDAO.limparLista();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao Invalida!");
            }
        } while (opcao != 0);
    }

    public static int lerInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada Invalida! Digite um numero inteiro: ");
                scanner.nextLine();
            }
        }
    }

    public static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Entrada Invalida! Digite um numero decimal (ex: 10,5): ");
                scanner.nextLine();
            }
        }
    }
}