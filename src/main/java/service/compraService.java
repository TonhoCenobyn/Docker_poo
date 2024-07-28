package service;

import dao.CompraDAO;
import model.Livro;
import model.Usuario;

import java.util.ArrayList;

public class compraService {

    public Boolean cadastrarCompra(Usuario usuario, ArrayList<Livro> carrinho) {
        return new CompraDAO().inserirCompra(usuario, carrinho);
    }
}
