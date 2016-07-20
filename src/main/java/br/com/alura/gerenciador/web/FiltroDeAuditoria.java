package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest requiscao = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Cookie cookie = new Cookies(requiscao.getCookies()).getUsuarioLogado();
		String usuario = "<deslogado>";
		String uri = requiscao.getRequestURI();
		System.out.println("Usuario " + usuario + " acessou a URI: " + uri);
		if (cookie != null) {
			cookie.setMaxAge(10 * 60);
			usuario = cookie.getValue();
			response.addCookie(cookie);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
