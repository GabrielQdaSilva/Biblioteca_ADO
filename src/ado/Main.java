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

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("ISBN: ");
                    String isbnIncluir = scanner.nextLine();

                    System.out.print("Título: ");
                    String tituloIncluir = scanner.nextLine();

                    System.out.print("Autor: ");
                    String autorIncluir = scanner.nextLine();

                    System.out.print("Ano de publicação: ");
                    int anoIncluir = scanner.nextInt();
                    scanner.nextLine();

                    vetor.adiciona(new Livro(isbnIncluir, tituloIncluir, autorIncluir, anoIncluir));
                    System.out.println("Livro incluído com sucesso!");
                    break;

                case 2:
                    System.out.print("Informe o ISBN do livro que deseja pesquisar: ");
                    String isbnPesquisar = scanner.nextLine();

                    int posicaoPesquisar = vetor.busca(new Livro(isbnPesquisar, null, null, 0));

                    if (posicaoPesquisar == -1) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        System.out.println("Livro encontrado: " + vetor.busca(posicaoPesquisar));
                    }
                    break;

                case 3:
                    System.out.print("Informe o ISBN do livro que deseja alterar: ");
                    String isbnAlterar = scanner.nextLine();

                    int posicaoAlterar = vetor.busca(new Livro(isbnAlterar, null, null, 0));

                    if (posicaoAlterar == -1) {
                        System.out.println("Livro não encontrado.");
                        break;
                    }

                    System.out.print("Novo título: ");
                    String tituloAlterar = scanner.nextLine();

                    System.out.print("Novo autor: ");
                    String autorAlterar = scanner.nextLine();

                    System.out.print("Novo ano de publicação: ");
                    int anoAlterar = scanner.nextInt();
                    scanner.nextLine();

                    vetor.remove(posicaoAlterar);
                    vetor.adiciona(posicaoAlterar, new Livro(isbnAlterar, tituloAlterar, autorAlterar, anoAlterar));
                    System.out.println("Livro alterado com sucesso!");
                    break;

                case 4:
                    System.out.print("Informe o ISBN do livro que deseja excluir: ");
                    String isbnExcluir = scanner.nextLine();

                    vetor.remove(new Livro(isbnExcluir, null, null, 0));
                    System.out.println("Livro excluído com sucesso!");
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
}