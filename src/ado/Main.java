package ado;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static VetorPokemon pokedex = new VetorPokemon(5);
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random(); // Random mantido!

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        System.out.println("=== BEM-VINDO A POKEDEX ===");

        while (!exit) {
            System.out.println("\n--- MENU DA POKEDEX ---");
            System.out.println("1 - Incluir novo Pokemon");
            System.out.println("2 - Pesquisar Pokemon");
            System.out.println("3 - Alterar dados de Pokemon");
            System.out.println("4 - Excluir Pokemon");
            System.out.println("5 - Listar todos os Pokemon");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opcao: ");
            String input = scanner.nextLine();

            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Opcao invalida.");
                continue;
            }

            // Switch tradicional mantido
            switch (choice) {
                case 1:
                    includePokemon();
                    break;
                case 2:
                    searchPokemon();
                    break;
                case 3:
                    alterPokemon();
                    break;
                case 4:
                    excludePokemon();
                    break;
                case 5:
                    listPokemon();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Escolha entre 1 e 6.");
            }
        }

        System.out.println("Encerrando a Pokedex... Obrigado por usar!");
        scanner.close();
    }

    private static void includePokemon() throws Exception {
        System.out.println("\n--- INCLUIR NOVO POKEMON ---");

        // Geração do ID automático mantida
        int id = generateUniqueRandomId();
        if (id == -1) {
            System.out.println("Nao foi possivel gerar um ID unico.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome nao pode estar vazio.");
            return;
        }

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        if (tipo.trim().isEmpty()) {
            System.out.println("Tipo nao pode estar vazio.");
            return;
        }

        Pokemon novoPokemon = new Pokemon(nome, tipo, id);
        pokedex.adiciona(novoPokemon);
        System.out.println("Pokemon incluido com sucesso! ID gerado: " + id);
    }

    private static int generateUniqueRandomId() {
        for (int attempt = 0; attempt < 100; attempt++) {
            int candidateId = random.nextInt(999) + 1;
            if (!pokedex.existeId(candidateId)) {
                return candidateId;
            }
        }
        return -1; // Retorna -1 se não conseguir achar um ID livre após 100 tentativas
    }

    private static void searchPokemon() throws Exception {
        if (pokedex.tamanho() == 0) {
            System.out.println("\nA Pokedex esta vazia.");
            return;
        }

        System.out.println("\n--- PESQUISAR POKEMON ---");
        System.out.println("1 - Por posicao no vetor");
        System.out.println("2 - Por ID");
        System.out.print("Escolha uma opcao: ");

        int searchType = Integer.parseInt(scanner.nextLine());

        if (searchType == 1) {
            System.out.print("Digite a posicao (0 a " + (pokedex.tamanho() - 1) + "): ");
            int posicao = Integer.parseInt(scanner.nextLine());
            Pokemon p = pokedex.busca(posicao);
            System.out.println("Pokemon encontrado: " + p.toString());

        } else if (searchType == 2) {
            System.out.print("Digite o ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Pokemon p = pokedex.buscaPorId(id);
            if (p != null) {
                System.out.println("Pokemon encontrado: " + p.toString());
            } else {
                System.out.println("Nenhum Pokemon encontrado com ID " + id);
            }
        } else {
            System.out.println("Opcao invalida.");
        }
    }

    private static void alterPokemon() throws Exception {
        if (pokedex.tamanho() == 0) {
            System.out.println("\nA Pokedex esta vazia.");
            return;
        }

        System.out.println("\n--- ALTERAR POKEMON ---");
        System.out.print("Digite o ID do Pokemon que deseja alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Pokemon pokemonParaAlterar = pokedex.buscaPorId(id);

        if (pokemonParaAlterar == null) {
            System.out.println("Pokemon nao encontrado.");
            return;
        }

        System.out.println("\nPokemon selecionado: " + pokemonParaAlterar.toString());

        System.out.print("Novo nome (ou aperte Enter para manter '" + pokemonParaAlterar.getNome() + "'): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            pokemonParaAlterar.setNome(novoNome);
        }

        System.out.print("Novo tipo (ou aperte Enter para manter '" + pokemonParaAlterar.getTipo() + "'): ");
        String novoTipo = scanner.nextLine();
        if (!novoTipo.trim().isEmpty()) {
            pokemonParaAlterar.setTipo(novoTipo);
        }

        System.out.println("Pokemon alterado com sucesso! Novo: " + pokemonParaAlterar.toString());
    }

    private static void excludePokemon() throws Exception {
        if (pokedex.tamanho() == 0) {
            System.out.println("\nA Pokedex esta vazia.");
            return;
        }

        System.out.println("\n--- EXCLUIR POKEMON ---");
        System.out.print("Digite o ID do Pokemon a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        int posicao = pokedex.buscaPorIdPosicao(id);

        if (posicao == -1) {
            System.out.println("Nenhum Pokemon encontrado com ID " + id);
            return;
        }

        pokedex.remove(posicao);
        System.out.println("Excluido com sucesso!");
    }

    private static void listPokemon() {
        if (pokedex.tamanho() == 0) {
            System.out.println("\nA Pokedex esta vazia.");
            return;
        }

        System.out.println("\n--- LISTA COMPLETA ---");
        System.out.println("Total: " + pokedex.tamanho());
        for (int i = 0; i < pokedex.tamanho(); i++) {
            Pokemon p = pokedex.buscaPorPosicao(i);
            if (p != null) {
                System.out.println("[" + i + "] " + p.toString());
            }
        }
    }
}