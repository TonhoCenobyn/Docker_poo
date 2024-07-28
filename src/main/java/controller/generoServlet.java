package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genero;
import model.Livro;
import service.generoService;

import java.io.IOException;

@WebServlet("genero")
public class generoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        if (opcao != null) {
            if (opcao.equals("cadastrar")) {
                String nome = req.getParameter("nome");
                Genero genero = new Genero(nome);
                if (new generoService().cadastoGenero(genero)){
                    req.setAttribute("erroCadastroGenero", "Gênero cadastrado com sucesso!");
                }
                else{
                    req.setAttribute("erroCadastroGenero", "Gênero cadastrado falhou!");
                }
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/generos.jsp");
                rd.forward(req, resp);
            }
            if (opcao.equals("editar")) {
                String nome = req.getParameter("nome");
                int id = Integer.parseInt(req.getParameter("id"));
                Genero genero = new Genero(id, nome);

                if(new generoService().editarGenero(genero)){
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/generos.jsp");
                    rd.forward(req, resp);
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        if (opcao != null){
            if (opcao.equals("excluir")){
                System.out.println("PENTROU NO DOGET");
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println("ID: " + id);
                Genero genero = new Genero(id);

                if (new generoService().excluirGenero(genero)) {
                    System.out.println("DEU CERTO NO GET");
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/generos.jsp");
                    rd.forward(req, resp);
                }
                else{
                    req.setAttribute("erroExcluirGenero", "Erro ao excluir gênero. Confira se existe algum livro atrelado ao gênero e exclua ele primeiro.");
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/generos.jsp");
                    rd.forward(req, resp);
                }
            }
            if (opcao.equals("editar")){
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("id", id);

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/edicaoGenero.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
