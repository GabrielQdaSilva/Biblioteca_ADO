package ado;

public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro() {
    }

    public Livro(String isbn, String titulo, String autor, int anoPublicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Livro)) {
            return false;
        }
        Livro outro = (Livro) obj;
        return this.isbn != null && this.isbn.equals(outro.isbn);
    }

    @Override
    public int hashCode() {
        return this.isbn == null ? 0 : this.isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Livro{" + "isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", anoPublicacao="
                + anoPublicacao + '}';
    }
}