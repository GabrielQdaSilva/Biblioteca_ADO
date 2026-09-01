package ado;

public class VetorPokemon {
    private Pokemon[] elementos;
    private int tamanho;

    public VetorPokemon(int capacidade) {
        this.elementos = new Pokemon[capacidade];
        this.tamanho = 0;
    }

    public void adiciona(Pokemon pokemon) throws Exception {
        if (this.tamanho == this.elementos.length) {
            this.aumentaCapacidade();
        }
        this.elementos[this.tamanho] = pokemon;
        this.tamanho++;
    }

    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < this.tamanho - 1; i++) {
            s.append(this.elementos[i]);
            s.append(", ");
        }

        if (this.tamanho > 0) {
            s.append(this.elementos[this.tamanho - 1]);
        }

        s.append("]");
        return s.toString();
    }

    public Pokemon busca(int posicao) throws Exception {
        if (posicao >= 0 && posicao < this.tamanho) {
            return this.elementos[posicao];
        } else {
            throw new Exception("Posição inválida");
        }
    }

    public int busca(Pokemon pokemon) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i].equals(pokemon)) {
                return i;
            }
        }
        return -1;
    }

    public boolean adiciona(int posicao, Pokemon pokemon) throws Exception {
        if (posicao >= 0 && posicao <= this.tamanho) {
            this.aumentaCapacidade();
            for (int i = this.tamanho - 1; i >= posicao; i--) {
                this.elementos[i + 1] = this.elementos[i];
            }
            this.elementos[posicao] = pokemon;
            this.tamanho++;
            return true;
        } else {
            throw new Exception("Posição inválida");
        }
    }

    private void aumentaCapacidade() {
        if (this.tamanho == this.elementos.length) {
            Pokemon[] novosElementos = new Pokemon[this.elementos.length * 2];
            for (int i = 0; i < this.tamanho; i++) {
                novosElementos[i] = this.elementos[i];
            }
            this.elementos = novosElementos;
        }
    }

    public void remove(int posicao) throws Exception {
        if (posicao >= 0 && posicao < this.tamanho) {
            for (int i = posicao; i < this.tamanho - 1; i++) {
                this.elementos[i] = this.elementos[i + 1];
            }
            this.elementos[this.tamanho - 1] = null;
            this.tamanho--;
        } else {
            throw new Exception("Posição inválida");
        }
    }

    public boolean remove(Pokemon pokemon) throws Exception {
        int posicao = this.busca(pokemon);
        if (posicao > -1) {
            this.remove(posicao);
            return true;
        } else {
            throw new Exception("Elemento não existe no vetor");
        }
    }

    // Métodos extras necessários para o menu funcionar
    public Pokemon buscaPorId(int id) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i].getId() == id) {
                return this.elementos[i];
            }
        }
        return null;
    }

    public int buscaPorIdPosicao(int id) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean existeId(int id) {
        return buscaPorId(id) != null;
    }

    public Pokemon buscaPorPosicao(int posicao) {
        if (posicao >= 0 && posicao < this.tamanho) {
            return this.elementos[posicao];
        }
        return null;
    }
}