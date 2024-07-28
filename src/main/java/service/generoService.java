package service;

import dao.GeneroDAO;
import model.Genero;

public class generoService {
    public boolean cadastoGenero(Genero genero) {
        GeneroDAO generoDao = new GeneroDAO();
        return generoDao.cadastrarGenero(genero);
    }

    public Boolean excluirGenero(Genero genero) {
        return new GeneroDAO().excluirGenero(genero);
    }

    public Boolean editarGenero(Genero genero) {
        return new GeneroDAO().editarGenero(genero);
    }

}
