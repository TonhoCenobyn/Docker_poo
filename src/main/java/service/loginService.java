package service;

import dao.UsuarioDAO;
import model.Usuario;

public class loginService {

    public Usuario logar(String email, String senha){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.autenticarUsuario(email, senha);
    }

    public Boolean cadastrarUsuario(String nome, String email, String senha, String telefone, int permissao){
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = new Usuario(nome, email, senha, telefone, permissao);
        return dao.inserirUsuario(u);
    }
}
