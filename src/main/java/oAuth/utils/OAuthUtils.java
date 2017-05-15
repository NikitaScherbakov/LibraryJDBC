package oAuth.utils;

import oAuth.servlet.OAuthServlet;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OAuthUtils {
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private OAuthServlet oAuthServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public OAuthUtils(OAuthServlet oAuthServlet, HttpServletRequest request, HttpServletResponse response) {
        this.oAuthServlet = oAuthServlet;
        this.request = request;
        this.response = response;
    }

    /**
     * Method implements OAuth protocol for authorization session
     * and redirect on students page if authorization pass successful
     *
     * @throws IOException
     */
    public void authorization() throws IOException {
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        if (authorizationUtils.isNotAuthorization()) {
            String accessToken;
            String userName;
            if (oAuthServlet.getError(request)) {
                PrintWriter out = response.getWriter();
                out.print("<h1>Error. Not get code!</h1>");
                return;
            } else if (oAuthServlet.notHaveCode(request)) {
                response.sendRedirect(oAuthServlet.getAuthUrl());
                return;
            } else {
                HttpClient httpclient = new HttpClient();
                PostMethod post = new PostMethod(oAuthServlet.getTokenUrl());
                post.addParameter("code", request.getParameter("code"));
                post.addParameter("client_id", oAuthServlet.getClientId());
                post.addParameter("client_secret", oAuthServlet.getClientSecret());
                post.addParameter("redirect_uri", oAuthServlet.getRedirectUri());
                httpclient.executeMethod(post);
                accessToken = oAuthServlet.getToken(post);
                userName = oAuthServlet.getUserName(post);
            }
            request.getSession().setAttribute(OAuthUtils.ACCESS_TOKEN, accessToken);
            request.getSession().setAttribute("userName", userName);
        }
        response.sendRedirect("/list");
    }
}