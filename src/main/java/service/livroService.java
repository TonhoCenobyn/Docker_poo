package service;

import dao.LivroDAO;
import dao.UsuarioDAO;
import model.Livro;
import model.Usuario;

public class livroService {
    public Boolean cadastroLivro(Livro livro){
        LivroDAO dao = new LivroDAO();
        return dao.inserirLivro(livro);
    }
    public Boolean excluir(Livro livro){
        return new LivroDAO().excluirLivro(livro);
    }

    public Boolean removerEstoque(Livro livro){
        return new LivroDAO().removerLivro(livro);
    }

    public Boolean disponibilizar(Livro livro){
        return new LivroDAO().disponibilizarLivro(livro);
    }

    public Boolean edicaoLivro(Livro livro){
        return new LivroDAO().editarLivro(livro);
    }

    public Livro buscarLivro(Livro livro) {
        System.out.println("ID NO SERVICE: " + livro.getId());
        return new LivroDAO().getLivro(livro.getId()); }
}
