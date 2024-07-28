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
import service.loginService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        System.out.println(email);
        System.out.println(senha);

        Usuario usuario = new loginService().logar(email, senha);
        ArrayList<Livro> carrinho = new ArrayList<Livro>();

        HttpSession session = req.getSession(true);

        session.setAttribute("usuario", usuario);
        session.setAttribute("carrinho", carrinho);

        if(usuario != null){
            if (usuario.getPermissao() == 0){
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboardCliente.jsp");
                rd.forward(req, resp);
            }
            if (usuario.getPermissao() == 1){
                System.out.println("Usuario permissao: " + usuario.getPermissao());
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
                rd.forward(req, resp);
            }

        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            req.setAttribute("erroLogin", "Usuário não encontrado. Tente novamente ou cadastre-se.");
            rd.forward(req, resp);
        }
    }
}
