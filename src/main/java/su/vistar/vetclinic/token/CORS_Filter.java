package su.vistar.vetclinic.token;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by Владислав on 21.02.2017.
 */
@Component
public class CORS_Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        if(method.equals("OPTIONS") || method.equals("options"))
        {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");// разрешенные загаловки
            response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Headers");//открыть доступ к загаловкам
            response.setStatus(200);
        }
        else {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");// разрешенные загаловки
            response.setHeader("Access-Control-Expose-Headers", "Authorization, URL, Access-Control-Allow-Headers");//открыть доступ к загаловкам
            chain.doFilter(req, response);
        }
    }

    public void destroy() {}
}
