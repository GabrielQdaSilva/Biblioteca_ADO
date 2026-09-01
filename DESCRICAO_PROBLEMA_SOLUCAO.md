# Atividade de Programação Java — Aula 02/03
## Manipulação de Arrays e Estruturas de Dados

---

## 1. Descrição do Problema (Situação)

Uma pequena empresa de tecnologia desenvolveu um protótipo de **Pokédex digital** para gerenciar informações de Pokémon. O sistema deve permitir que um atendente cadastre, consulte, altere e remova Pokémon de uma base de dados local, **sem utilizar coleções prontas da API Java** (como `ArrayList`, `List`, `Set`, etc.).

A solução deve ser baseada **exclusivamente em arrays (vetores) simples**, com gerenciamento manual de capacidade e redimensionamento automático quando o vetor enche.

### Requisitos funcionais:
- **Incluir** novo Pokémon (nome, tipo) — ID gerado automaticamente e único
- **Pesquisar** Pokémon por ID exato ou por nome (busca parcial, case-insensitive)
- **Alterar** dados de um Pokémon existente (nome e/ou tipo)
- **Excluir** Pokémon da base (com confirmação)
- **Listar** todos os Pokémon cadastrados
- Interface de usuário via **menu interativo no console**

### Restrições técnicas:
- Não usar `java.util.ArrayList`, `java.util.List`, `java.util.Vector` ou qualquer coleção pronta
- Implementar **vetor próprio** com redimensionamento manual (dobro da capacidade)
- Encapsulamento adequado (`private` + getters/setters)
- Tratamento de posições inválidas sem exceções verificadas (retorno `null`/`boolean`)

---

## 2. Solução Implementada

### 2.1. Estrutura de Pacotes
```
ado/
├── Pokemon.java        // Entidade (dados do Pokémon)
├── VetorPokemon.java   // Vetor manual redimensionável (estrutura de dados)
├── PokedexMenu.java    // Aplicação principal (menu interativo)
└── Main.java           // Teste unitário simples das classes
```

---

### 2.2. Classe `Pokemon` (Entidade)

**Atributos (encapsulados):**
- `id` (int) — identificador único, gerado automaticamente
- `nome` (String) — nome do Pokémon
- `tipo` (String) — tipo principal (ex.: "Elétrico", "Fogo", "Água")

**Métodos principais:**
- Construtores (padrão e com parâmetros)
- Getters e Setters para todos os atributos
- `toString()` → formato **`#025 Pikachu(Elétrico)`** (ID com 3 dígitos, zeros à esquerda)
- `equals(Object)` e `hashCode()` **baseados apenas no `id`** — essencial para busca/remoção semântica no vetor

---

### 2.3. Classe `VetorPokemon` (Estrutura de Dados — Núcleo da Atividade)

Implementa um **vetor dinâmico de `Pokemon`** usando array `Pokemon[]` bruto.

**Campos:**
- `private Pokemon[] atributos` — array interno
- `private int tamanho` — quantidade de elementos **válidos** (não confundir com `atributos.length`)

**Métodos públicos:**

| Método | Descrição |
|--------|-----------|
| `VetorPokemon(int capacidade)` | Construtor — inicializa array com capacidade dada, `tamanho = 0` |
| `void adiciona(Pokemon p)` | Adiciona no final; redimensiona se cheio (`tamanho == length`) |
| `boolean adicionaInicio(int pos, Pokemon p)` | Insere na posição `pos` (0 ≤ pos ≤ tamanho); desloca elementos à direita |
| `Pokemon busca(int posicao)` | Retorna elemento na posição ou `null` se inválida |
| `int busca(Pokemon p)` | Retorna índice do elemento (usa `.equals()`) ou `-1` |
| `boolean remove(int posicao)` | Remove por posição; desloca elementos à esquerda; retorna `true` se ok |
| `boolean remove(Pokemon p)` | Remove por objeto; delega para `remove(int)` após busca |
| `int tamanho()` | Retorna quantidade de elementos válidos |
| `String toString()` | Representação `[#001 Bulbasaur(Grama), #004 Charmander(Fogo)]` |
| `Pokemon buscaPorId(int id)` | Busca linear por ID — retorna `Pokemon` ou `null` |
| `int buscaPorIdPosicao(int id)` | Retorna índice do ID ou `-1` |
| `boolean existeId(int id)` | Verifica se ID já existe na estrutura |
| `Pokemon buscaPorPosicao(int posicao)` | Alias seguro de `busca(int)` para o menu |

**Redimensionamento (`aumentaCapacidade`):**
- Disparado automaticamente quando `tamanho == atributos.length`
- Cria novo array `Pokemon[]` com **dobro da capacidade**
- Copia **apenas os `tamanho` elementos válidos** (índices `0` a `tamanho-1`)
- Substitui referência interna

> **Nota:** Todos os métodos que poderiam lançar `Exception` por posição inválida foram alterados para retornar `null` ou `boolean`, mantendo o código no nível da Aula 02/03 (sem exceções verificadas).

---

### 2.4. Classe `PokedexMenu` (Aplicação Principal)

Menu interativo com `Scanner` (entrada via console), validação simples de entrada (apenas `String` + `Integer.parseInt` + verificações manuais — **sem `try-catch` para `InputMismatchException`**).

**Opções do menu:**
1. **Incluir** — Gera ID aleatório único (1–999), pede nome e tipo, insere no vetor
2. **Pesquisar** — Submenu: por ID exato ou por nome (busca parcial, case-insensitive)
3. **Alterar** — Localiza por posição ou ID; permite editar nome/tipo (Enter mantém valor atual)
4. **Excluir** — Localiza por posição ou ID; pede confirmação (s/N); remove se confirmado
5. **Listar** — Mostra todos com índice: `[0] #001 Bulbasaur(Grama)`
6. **Sair** — Encerra o programa

**Geração de ID aleatório único:**
```java
private static int generateUniqueRandomId() {
    for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
        int candidateId = random.nextInt(MAX_ID) + 1;
        if (!pokedex.existeId(candidateId)) return candidateId;
    }
    return -1; // falha após 100 tentativas
}
```

---

### 2.5. Classe `Main` (Teste Rápido)

Classe auxiliar para verificação isolada das classes `Pokemon` e `VetorPokemon`:
- Testa `equals()` por ID (mesmo ID = true, IDs diferentes = false)
- Testa `toString()` no formato `#025 Pikachu(Elétrico)`
- Testa `setId()` funcionando corretamente

---

## 3. Conceitos da Aula 02/03 Aplicados

| Conceito | Onde aparece na solução |
|----------|-------------------------|
| Declaração e criação de arrays | `private Pokemon[] atributos = new Pokemon[capacidade]` |
| Acesso por índice / `length` | Todos os loops `for (int i = 0; i < tamanho; i++)` |
| Percorrer com `for` tradicional | Busca, remoção, listagem, redimensionamento |
| Inserção no final | `adiciona(Pokemon)` — `atributos[tamanho] = p; tamanho++` |
| Inserção em posição | `adicionaInicio(int, Pokemon)` — deslocamento à direita |
| Remoção com deslocamento | `remove(int)` — deslocamento à esquerda + `null` no final |
| Redimensionamento manual | `aumentaCapacidade()` — novo array dobro + cópia seletiva |
| Encapsulamento | `private` campos + getters/setters em `Pokemon` e `VetorPokemon` |
| `toString()` para depuração | Ambas as classes implementam formato legível |
| `equals()`/`hashCode()` semânticos | `Pokemon` compara por `id` (não por referência) |
| Separação de responsabilidades | Entidade (`Pokemon`), Estrutura (`VetorPokemon`), Interface (`PokedexMenu`) |

---

## 4. Como Compilar e Executar

```bash
# No diretório src/
cd /home/ryoku/Pokedex/src

# Compilar todas as classes do pacote ado
javac ado/*.java

# Executar a aplicação principal (menu interativo)
java ado.PokedexMenu

# Ou executar o teste rápido
java ado.Main
```

---

## 5. Arquivos para Entrega

| Arquivo | Descrição |
|---------|-----------|
| `ado/Pokemon.java` | Entidade com encapsulamento, `equals/hashCode`, `toString` formatado |
| `ado/VetorPokemon.java` | Vetor manual redimensionável — **núcleo da atividade** |
| `ado/PokedexMenu.java` | Menu interativo completo com CRUD |
| `ado/Main.java` | Teste unitário simples |

> Todos os arquivos estão no pacote `ado` (minúsculo, conforme convenção Java).  
> Nenhum arquivo usa `ArrayList` ou coleções da API — apenas `Pokemon[]` bruto.

---

## 6. Conclusão

A solução implementa **integralmente os requisitos da atividade**:
- Situação/problema realista (Pokédex) descrita e resolvida
- Classe `VetorPokemon` com **todos os métodos de manipulação** de vetor (incluir, buscar, alterar, excluir, redimensionar)
- Classe entidade `Pokemon` com atributos, encapsulamento e métodos adequados
- Classe de teste `PokedexMenu` com **menu interativo** permitindo todas as operações CRUD
- **Zero uso de `ArrayList`** — apenas array manual com redimensionamento
- Código limpo, compilável, testado e dentro do escopo da Aula 02/03 (arrays, loops, encapsulamento, estruturas de dados básicas)

---

**Desenvolvido como atividade prática de Estruturas de Dados — Aulas 02 e 03**  
**Linguagem:** Java 17+  
**Paradigma:** Orientado a Objetos + Estruturas de Dados Lineares (Vetor)