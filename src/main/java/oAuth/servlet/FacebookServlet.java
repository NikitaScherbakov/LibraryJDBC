package oAuth.servlet;

import oAuth.utils.OAuthUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet(name = "FacebookServlet", urlPatterns = {"/fb/oauth"})
public class FacebookServlet extends HttpServlet implements OAuthServlet {

    private String clientId = null;
    private String clientSecret = null;
    private String redirectUri = null;
    private String authUrl = null;
    private String tokenUrl = null;
    private String userName = null;

    /**
     * Initialize all properties by getting values from web.xml file
     * */
    public void init() throws ServletException {
        clientId = this.getInitParameter("clientId");
        clientSecret = this.getInitParameter("clientSecret");
        redirectUri = this.getInitParameter("redirectUri");
        try {
            authUrl = this.getInitParameter("authUrl")
                    + "/v2.8/dialog/oauth?" +
                    "client_id=" + clientId +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                    "&response_type=code" +
                    "&scope=name";
        } catch (UnsupportedEncodingException e) {
            throw new ServletException(e);
        }
        tokenUrl = this.getInitParameter("tokenUrl") + "/oauth/access_token";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OAuthUtils oAuthUtils = new OAuthUtils(this, request, response);
        oAuthUtils.authorization();
    }

    /**
     * Get token from response
     * @param post the pos data response from fb server
     * @return token from response.
     * */
    @Override
    public String getToken(PostMethod post){
        String accessToken = null;
        try {
            JSONObject authResponse = new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
            accessToken = authResponse.getString("access_token");
            //userName = authResponse.getString("email");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public String getUserName(PostMethod post){
        return userName;
    }

    /**
     * Get token from response
     * @param request request from fb server
     * @return boolean value was error or not
     * */
    @Override
    public boolean getError(HttpServletRequest request) {
        return request.getParameter("error") != null && request.getParameter("error").equals("invalid_request");
    }

    @Override
    public boolean notHaveCode(HttpServletRequest request) {
        return request.getParameter("code") == null;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    @Override
    public String getAuthUrl() { return authUrl; }
    @Override
    public String getTokenUrl() {
        return tokenUrl;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
}