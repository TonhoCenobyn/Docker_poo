package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.loginService;

import java.io.IOException;

@WebServlet("cadastroUsuario")

public class cadastroUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String telefone = req.getParameter("telefone");
        int permissao = Integer.parseInt(req.getParameter("permissao"));

        if (new loginService().cadastrarUsuario(nome, email, senha, telefone, permissao)){
            RequestDispatcher rd;
            if (permissao == 0){
                rd = req.getRequestDispatcher("/index.jsp");
            }
            else{
                rd = req.getRequestDispatcher("WEB-INF/pages/cadastroAdm.jsp");
                req.setAttribute("sucessoCadastroADM", "Administrador cadastrado com sucesso!");
            }

            rd.forward(req, resp);
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("/cadastroUsuario.jsp");
            req.setAttribute("erroCadastro", "Cadastro de usuário falhou. Tente novamente.");
            rd.forward(req, resp);
        }
    }
}
