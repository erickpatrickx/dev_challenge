package br.com.devchallenge.reclamacao.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.com.devchallenge.reclamacao.client.AuthClient;

/**
 * 
 * Filtro que controla a autenticação dos usuarios
 * 
 * @author erick.oliveira
 *
 */
public class AuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	AuthClient authClient;
	
	public AuthenticationFilter(TokenAuthenticationService authenticationService) {
		this.tokenAuthenticationService = authenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getHeader(TokenAuthenticationService.HEADER_STRING) == null) {
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			SecurityContextHolder.getContext().setAuthentication(null);
			return;
		} else {
			Authentication authentication = tokenAuthenticationService.getAuthentication(httpRequest);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
			} else {
				httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			}

		}

		SecurityContextHolder.getContext().setAuthentication(null);

	}
}