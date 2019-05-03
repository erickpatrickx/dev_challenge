package br.com.devchallenge.gateway;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Filtro do Zuul para redirecionamento para rotas
 * @author erick.oliveira
 *
 */
public class SimpleFilter extends ZuulFilter {

	private static Logger log = Logger.getLogger(ZuulFilter.class.getName());


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
