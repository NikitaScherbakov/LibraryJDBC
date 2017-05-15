package oAuth.servlet;

import oAuth.utils.OAuthUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
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

@WebServlet(name="VKServlet", urlPatterns = {"/vk/oauth"})
public class VKServlet extends HttpServlet implements OAuthServlet {

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
        String environment = this.getInitParameter("environment");
        try {
            authUrl = environment
                    + "/authorize?" +
                    "client_id=" + clientId + "&" +
                    "redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                    "&response_type=code" +
                    "&scope=email";
        } catch (UnsupportedEncodingException e) {
            throw new ServletException(e);
        }
        tokenUrl = environment + "/access_token";
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OAuthUtils oAuthUtils = new OAuthUtils(this, request, response);
        oAuthUtils.authorization();
    }

    /**
     * Get token from response
     * @param post the pos data response from vk server
     * @return token from response.
     * */
    @Override
    public String getToken(PostMethod post){
        String accessToken = "";
        try {
            JSONObject authResponse = new JSONObject(new JSONTokener(new InputStreamReader(post.getResponseBodyAsStream())));
            accessToken = authResponse.getString("access_token");
            userName = authResponse.getString("email");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public String getUserName(PostMethod post){
        return userName;
    }

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
    public String getAuthUrl() {
        return authUrl;
    }

    @Override
    public String getTokenUrl() {
        return tokenUrl;
    }
}