package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
		if (usuario == null) {
			PrintWriter writer = resp.getWriter();
			writer.write("<html><body>Não foi Possivel logar o usuário!</body></html>");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.write("<html><body>Usuario: " + usuario.getEmail() + " logado com Sucesso!</body></html>");
			HttpSession session = req.getSession();
			session.setAttribute("usuario.logado", usuario);
		}
	}
}
