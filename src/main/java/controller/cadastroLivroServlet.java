package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genero;
import model.Livro;
import service.livroService;

import java.io.IOException;

@WebServlet("cadastroLivro")
public class cadastroLivroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        float preco = Float.parseFloat(req.getParameter("preco"));
        int genero = Integer.parseInt(req.getParameter("genero"));
        String opcao = req.getParameter("opcao");
        System.out.println("OPCAO: " + opcao);
        Livro livro;

        if(genero == 0){
            RequestDispatcher rd = null;
            if(opcao.equals("cadastrar")){
                rd = req.getRequestDispatcher("WEB-INF/pages/cadastroLivro.jsp");
            }
            else if (opcao.equals("editar")){
                rd = req.getRequestDispatcher("WEB-INF/pages/edicaoLivro.jsp");
            }
            req.setAttribute("erroCadEd", "Gênero não inserido. Escolha um gênero ou cadastre um.");
            rd.forward(req, resp);
        }

        if (opcao.equals("cadastrar")){
            livro = new Livro(titulo, autor, preco, genero);

            if (new livroService().cadastroLivro(livro)){
                System.out.println("Controller: Livro cadastrado com sucesso");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                rd.forward(req, resp);
            }
            else{
                System.out.println("Controller: cadastro falhou");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/cadastroLivro.jsp");
                req.setAttribute("erroCadastro", "ERRO AO CADASTRAR LIVRO");
                rd.forward(req, resp);
            }
        }
        if (opcao.equals("editar")){
            int id = Integer.parseInt(req.getParameter("id"));
            livro = new Livro(id, titulo, autor, preco, genero);

            if (new livroService().edicaoLivro(livro)){
                System.out.println("Edicao realizada com sucesso");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                rd.forward(req, resp);
            }
            else{
                System.out.println("Controller: edicao falhou");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/edicaoLivro.jsp");
                rd.forward(req, resp);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        String redirect = req.getParameter("redirect");
        RequestDispatcher rd = null;

        if (redirect!=null){
            if (redirect.equals("cadastroLivro")){
                rd = req.getRequestDispatcher("WEB-INF/pages/cadastroLivro.jsp");
            }
            else if (redirect.equals("dashboard")){
                rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
            }
        }

        if (opcao != null ){
            if (opcao.equals("excluir")){
                int id = Integer.parseInt(req.getParameter("id"));

                Livro livro = new Livro(id);

                if (new livroService().excluir(livro)){
                    req.setAttribute("sucessoExcluirLivro", "Livro excluido com sucesso.");
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");}
                else{
                    req.setAttribute("erroExcluirLivro", "Erro ao excluir livro. O livro possivelmente ja foi adquirido por um usuario.");
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                }
            }

            if (opcao.equals("editar")){
                System.out.println("Opcao é editar");
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("id", id);

                rd = req.getRequestDispatcher("WEB-INF/pages/edicaoLivro.jsp");
            }

            if (opcao.equals("remover")){
                int id = Integer.parseInt(req.getParameter("id"));

                Livro livro = new Livro(id);

                if (new livroService().removerEstoque(livro)){
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                }
                else{
                    req.setAttribute("erroExcluirLivro", "Erro ao excluir livro. O livro possivelmente ja foi adquirido por um usuario.");
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                }
            }

            if (opcao.equals("disponibilizar")){
                int id = Integer.parseInt(req.getParameter("id"));

                Livro livro = new Livro(id);

                if (new livroService().disponibilizar(livro)){
                    req.setAttribute("sucessoDisponibilizarLivro", "Livro dispobilizado com sucesso.");
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                }
                else{
                    req.setAttribute("erroDisponibilizarLivro", "Erro ao disponibilizar o livro");
                    rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                }
            }
        }
        else{
            rd = req.getRequestDispatcher("WEB-INF/pages/cadastroLivro.jsp");
        }
        rd.forward(req, resp);
    }
}
