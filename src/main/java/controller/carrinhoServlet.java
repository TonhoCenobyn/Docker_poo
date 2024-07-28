package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Livro;
import model.Usuario;
import service.compraService;
import service.livroService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("carrinho")

public class carrinhoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");

        Livro livro = null;

        if (req.getParameter("id") != null){
            int id = Integer.parseInt(req.getParameter("id"));
            livro = new livroService().buscarLivro(new Livro(id));
        }

        Object carrinhoObjeto;
        ArrayList<Livro> carrinho;

        HttpSession sessao = req.getSession(true);

        carrinhoObjeto = sessao.getAttribute("carrinho"); //PEGA O CONTEUDO DO CARRINHO NA SESSAO

        carrinho = (ArrayList<Livro>) carrinhoObjeto; //O CARRINHO DA APPLICACAO RECEBE ESSE CONTEUDO COMO UM ARRAYLIST

        if (opcao!=null){
            System.out.println(opcao);
            if (opcao.equals("adicionar")){
                carrinho.add(livro);

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboardCliente.jsp");
                rd.forward(req, resp);
                sessao.setAttribute("carrinho", carrinho);
            }

            if (opcao.equals("excluir")){
                int contador = 0;

                while (contador < carrinho.size()){
                    if (carrinho.get(contador).getId() == livro.getId()){
                        carrinho.remove(contador);
                    }
                    contador++;
                }

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/carrinho.jsp");
                sessao.setAttribute("carrinho", carrinho);
                rd.forward(req, resp);

            }

            if (opcao.equals("voltar")){
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboardCliente.jsp");
                rd.forward(req, resp);
            }
            if (opcao.equals("confirmar")){
                Usuario usuario = (Usuario) sessao.getAttribute("usuario");

                if (new compraService().cadastrarCompra(usuario, carrinho)){
                    req.setAttribute("sucessoCompra", "Compra realizada com sucesso");

                }
                else{
                    req.setAttribute("falhaCompra", "Compra falhou");
                }

                carrinho.clear();
                sessao.setAttribute("carrinho", carrinho);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboardCliente.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
