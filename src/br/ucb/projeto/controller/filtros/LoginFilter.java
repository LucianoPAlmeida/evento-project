package br.ucb.projeto.controller.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.projeto.model.beans.Usuario;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Usuario user = (Usuario)req.getSession().getAttribute("usuario");
		String servletPath = req.getServletPath();
		//System.out.println(servletPath);
		if(servletPath.contains(".js") || servletPath.contains(".css")|| servletPath.contains("/images")|| user != null || req.getPathInfo().equals("/telaLogin.xhtml") || servletPath.equals("/rest")){
			chain.doFilter(request, response);
		}else{
			((HttpServletResponse)response).sendRedirect("telaLogin.xhtml");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
