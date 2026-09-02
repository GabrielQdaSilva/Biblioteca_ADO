package ado;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        VetorObjeto vetor = new VetorObjeto(5);

        int opcao;

        do {
            System.out.println("\n===== CADASTRO DE LIVROS =====");
            System.out.println("1 - Incluir livro");
            System.out.println("2 - Pesquisar livro (por ISBN)");
            System.out.println("3 - Alterar livro");
            System.out.println("4 - Excluir livro");
            System.out.println("5 - Listar todos os livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {

                case 1:
                    incluir(scanner, vetor);
                    break;

                case 2:
                    pesquisar(scanner, vetor);
                    break;

                case 3:
                    alterar(scanner, vetor);
                    break;

                case 4:
                    excluir(scanner, vetor);
                    break;

                case 5:
                    System.out.println("Livros cadastrados (" + vetor.tamanho() + "): " + vetor.toString());
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void incluir(Scanner scanner, VetorObjeto vetor) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Ano de publicação: ");
        int ano = lerInteiro(scanner);

        Livro livro = new Livro(isbn, titulo, autor, ano);

        if (vetor.busca(livro) > -1) {
            System.out.println("Já existe um livro cadastrado com esse ISBN.");
            return;
        }

        vetor.adiciona(livro);
        System.out.println("Livro incluído com sucesso!");
    }

    private static void pesquisar(Scanner scanner, VetorObjeto vetor) {
        System.out.print("Informe o ISBN do livro que deseja pesquisar: ");
        String isbn = scanner.nextLine();

        Livro chave = new Livro();
        chave.setIsbn(isbn);

        int posicao = vetor.busca(chave);

        if (posicao == -1) {
            System.out.println("Livro não encontrado.");
        } else {
            try {
                System.out.println("Livro encontrado: " + vetor.busca(posicao));
            } catch (Exception e) {
                System.out.println("Erro ao buscar o livro: " + e.getMessage());
            }
        }
    }

    private static void alterar(Scanner scanner, VetorObjeto vetor) {
        System.out.print("Informe o ISBN do livro que deseja alterar: ");
        String isbn = scanner.nextLine();

        Livro chave = new Livro();
        chave.setIsbn(isbn);

        int posicao = vetor.busca(chave);

        if (posicao == -1) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.print("Novo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Novo autor: ");
        String autor = scanner.nextLine();

        System.out.print("Novo ano de publicação: ");
        int ano = lerInteiro(scanner);

        Livro livroAlterado = new Livro(isbn, titulo, autor, ano);

        try {
            vetor.remove(posicao);
            vetor.adiciona(posicao, livroAlterado);
            System.out.println("Livro alterado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar o livro: " + e.getMessage());
        }
    }

    private static void excluir(Scanner scanner, VetorObjeto vetor) {
        System.out.print("Informe o ISBN do livro que deseja excluir: ");
        String isbn = scanner.nextLine();

        Livro chave = new Livro();
        chave.setIsbn(isbn);

        try {
            vetor.remove(chave);
            System.out.println("Livro excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir o livro: " + e.getMessage());
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}