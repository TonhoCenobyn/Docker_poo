package model;

public class Livro {
    private int id;
    private String nome;
    private String autor;
    private float preco;
    private int genero;

    public Livro(int id, String nome, String autor, float preco, int genero) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.preco = preco;
        this.genero = genero;
    }

    public Livro(String nome, String autor, float preco, int genero) {
        this.nome = nome;
        this.autor = autor;
        this.preco = preco;
        this.genero = genero;
    }

    public Livro(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
