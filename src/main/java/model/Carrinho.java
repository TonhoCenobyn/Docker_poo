package model;

public class Carrinho {
    private Usuario usuario;
    private Livro livro;

    public Carrinho(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public model.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(model.Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
