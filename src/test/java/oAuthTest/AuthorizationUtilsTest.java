package oAuthTest;

import oAuth.utils.AuthorizationUtils;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class AuthorizationUtilsTest {

    private HttpServletRequest request;
    private HttpSession httpSession;

    @Before
    public void init(){
        request = PowerMockito.mock(HttpServletRequest.class);
        httpSession = PowerMockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void isAuthorizationTrue() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn("token");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isAuthorization();
    }

    @Test
    public void isAuthorizationFalse() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert !authorizationUtils.isAuthorization();
    }

    @Test
    public void isNotAuthorizationTrue() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isNotAuthorization();
    }

    @Test
    public void isNotAuthorizationFalse() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn("token");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert !authorizationUtils.isNotAuthorization();
    }


    @Test
    public void isLoginRequestLogin() throws Exception {
        when(request.getRequestURI()).thenReturn("/login");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isLoginRequest();
    }

    @Test
    public void isLoginRequestOAuthVK() throws Exception {
        when(request.getRequestURI()).thenReturn("/vk/oauth");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isLoginRequest();
    }

    @Test
    public void isLoginRequestOAuthFB() throws Exception {
        when(request.getRequestURI()).thenReturn("/fb/oauth");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isLoginRequest();
    }

    @Test
    public void isLoginRequestFalse() throws Exception {
        when(request.getRequestURI()).thenReturn("/pages");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert !authorizationUtils.isLoginRequest();
    }

    @Test
    public void isResourcesRequestCSS() throws Exception {
        when(request.getRequestURI()).thenReturn("/styles/test.css");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isResourcesRequest();
    }

    @Test
    public void isResourcesRequestFont() throws Exception {
        when(request.getRequestURI()).thenReturn("/fonts/test.ttf");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert authorizationUtils.isResourcesRequest();
    }

    @Test
    public void isResourcesRequest() throws Exception {
        when(request.getRequestURI()).thenReturn("/pages");
        AuthorizationUtils authorizationUtils = new AuthorizationUtils(request);
        assert !authorizationUtils.isResourcesRequest();
    }
}