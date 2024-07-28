package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("navServlet")
public class navServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");

        System.out.println(opcao);

        RequestDispatcher rd = null;
        if (opcao.equals("home")){
            rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
        }
        if (opcao.equals("homeCliente")){
            rd = req.getRequestDispatcher("WEB-INF/pages/dashboardCliente.jsp");
        }
        if (opcao.equals("generos")){
            rd = req.getRequestDispatcher("WEB-INF/pages/generos.jsp");
        }
        if (opcao.equals("carrinho")){
            rd = req.getRequestDispatcher("WEB-INF/pages/carrinho.jsp");
        }
        if (opcao.equals("historico")){
            rd = req.getRequestDispatcher("WEB-INF/pages/historicoCliente.jsp");
        }
        if (opcao.equals("cadastroAdm")){
            rd = req.getRequestDispatcher("WEB-INF/pages/cadastroAdm.jsp");
        }
        if (opcao.equals("sair")){
            HttpSession session = req.getSession(true);
            session.removeAttribute("usuario");
            session.removeAttribute("carrinho");

            session.invalidate();
            rd = req.getRequestDispatcher("index.jsp");
        }

        rd.forward(req, resp);
    }
}
