package oAuth.utils;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtils {

    private HttpServletRequest request;

    public AuthorizationUtils(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Check authorization
     *
     * @return true if session have token
     */
    public boolean isAuthorization(){
        return request.getSession().getAttribute(OAuthUtils.ACCESS_TOKEN) != null;
    }

    public void logout() {
        request.getSession().setAttribute(OAuthUtils.ACCESS_TOKEN, null);
    }

    /**
     * Check authorization
     *
     * @return true if session not have token
     */
    public boolean isNotAuthorization(){
        return request.getSession().getAttribute(OAuthUtils.ACCESS_TOKEN) == null;
    }

    /**
     * Check does it URL for logIn
     *
     * @return true if URL contains part of logIn URL
     */
    public boolean isLoginRequest(){
        return request.getRequestURI().startsWith("/login") ||
                request.getRequestURI().startsWith("/vk/oauth") ||
                request.getRequestURI().startsWith("/fb/oauth");
    }

    public boolean isResourcesRequest(){
        return request.getRequestURI().startsWith("/fonts") || request.getRequestURI().startsWith("/styles");
    }
}