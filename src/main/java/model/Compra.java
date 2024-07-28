package model;

import java.sql.Date;

public class Compra {
    private int id_livro;
    private int id_usuario;
    private Date data_compra;
    private Livro livro;
    //AO INVES DE TER O ATRIBUTI ID_LIVRO, TIRA ELE E DEIXA SO O OBJETO LIVRO, PRA MELHORAR O CODIGO

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Compra(int id_livro, int id_usuario, Date data_compra) {
        this.id_livro = id_livro;
        this.id_usuario = id_usuario;
        this.data_compra = data_compra;
    }

    public Compra(Livro livro, Date data_compra) { //USAR ESSE CONSTRUTOR NO HISTORICO, NAO UMA ESTRUTURA DE LIVRO
        this.livro = livro;
        this.data_compra = data_compra;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }
}
