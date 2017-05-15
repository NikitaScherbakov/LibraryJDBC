package oAuth.filter;

import oAuth.utils.AuthorizationUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class RequestFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        AuthorizationUtils authorizationUtils = new AuthorizationUtils(req);

        HttpSession session = req.getSession(false);
        session.setAttribute("time", 10);
        session.setMaxInactiveInterval(10);
        if(session == null) {
            this.context.log("Unauthorized access request");
            res.sendRedirect("/login");
        }
        if(!(uri.endsWith("/login")) && !authorizationUtils.isAuthorization() && !(uri.endsWith("/vk/oauth")) && !(uri.endsWith("/logout")) && !(uri.endsWith("/list"))){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }else{
            chain.doFilter(request, response);
        }


    }
    public void destroy() {
    }

}
