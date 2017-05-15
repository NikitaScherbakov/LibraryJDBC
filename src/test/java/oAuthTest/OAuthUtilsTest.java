package oAuthTest;

import oAuth.servlet.OAuthServlet;
import oAuth.utils.OAuthUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({OAuthUtils.class, PostMethod.class, HttpClient.class})

public class OAuthUtilsTest {

    private OAuthServlet oAuthServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;

    @Before
    public void init(){
        request = PowerMockito.mock(HttpServletRequest.class);
        response = PowerMockito.mock(HttpServletResponse.class);
        oAuthServlet = mock(OAuthServlet.class);
        httpSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void authorizationHaveToken() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn("token");
        OAuthUtils oAuthUtils = new OAuthUtils(oAuthServlet, request, response);
        oAuthUtils.authorization();
        verify(oAuthServlet, times(0)).getError(request);
    }

    @Test
    public void authorizationGetError() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        when(oAuthServlet.getError(request)).thenReturn(true);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        OAuthUtils oAuthUtils = new OAuthUtils(oAuthServlet, request, response);
        oAuthUtils.authorization();
        verify(printWriter).print("<h1>Error. Not get code!</h1>");
    }

    @Test
    public void authorizationNotHaveTokenAndCode() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        when(oAuthServlet.notHaveCode(request)).thenReturn(true);
        OAuthUtils oAuthUtils = new OAuthUtils(oAuthServlet, request, response);
        oAuthUtils.authorization();
        verify(oAuthServlet).getAuthUrl();
    }

    @Test
    public void authorizationNotHaveTokenAndHaveCode() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        when(oAuthServlet.notHaveCode(request)).thenReturn(false);
        when(oAuthServlet.getClientId()).thenReturn("clientId");
        when(oAuthServlet.getClientSecret()).thenReturn("clientSecret");
        when(oAuthServlet.getRedirectUri()).thenReturn("clientRedirectUri");
        PowerMockito.mockStatic(HttpClient.class);
        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        PowerMockito.whenNew(HttpClient.class).withNoArguments()
                .thenReturn(httpClient);
        PowerMockito.mockStatic(PostMethod.class);
        PostMethod post = PowerMockito.mock(PostMethod.class);
        PowerMockito.whenNew(PostMethod.class).withAnyArguments()
                .thenReturn(post);
        when(httpClient.executeMethod(post)).thenReturn(0);
        when(request.getParameter("code")).thenReturn("code");
        OAuthUtils oAuthUtils = new OAuthUtils(oAuthServlet, request, response);
        oAuthUtils.authorization();
        verify(oAuthServlet).getToken(post);
    }
}