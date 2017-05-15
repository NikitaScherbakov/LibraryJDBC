package oAuth.filter;

import oAuth.utils.AuthorizationUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    /**
     * Filter for redirect on login page if user request page not not accessible to authorized users
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        String loginURI = request.getContextPath() + "/login";
        servletRequest.setAttribute("isAuthorized", authorizationUtils.isAuthorization());
        /*if (authorizationUtils.isAuthorization() || authorizationUtils.isLoginRequest() || authorizationUtils.isResourcesRequest()) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }*/
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}