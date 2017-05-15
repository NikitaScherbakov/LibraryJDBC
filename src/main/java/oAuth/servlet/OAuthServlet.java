package oAuth.servlet;

import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.http.HttpServletRequest;

public interface OAuthServlet {

    /**
     * Get token from post response
     *
     * @param post of response
     * @return token
     */
    String getToken(PostMethod post);

    /**
     * Check on error message
     *
     * @param request
     * @return true if get error message
     */
    boolean getError(HttpServletRequest request);

    /**
     * Check on code for authorization
     *
     * @param request
     * @return true if get code
     */
    boolean notHaveCode(HttpServletRequest request);

    String getClientId();

    String getClientSecret();

    String getRedirectUri();

    String getAuthUrl();

    String getTokenUrl();

    String getUserName(PostMethod post);
}