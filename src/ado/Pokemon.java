package ado;

public class Pokemon {
    private String nome;
    private String tipo;
    private int id;

    public Pokemon() {
    }

    public Pokemon(String nome, String tipo, int id) {
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        // Removido o String.format para ficar igual aos slides
        return "#" + id + " " + nome + " (" + tipo + ")";
    }
}