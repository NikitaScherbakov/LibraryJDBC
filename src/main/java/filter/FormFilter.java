package filter;


import javax.servlet.*;
import java.io.IOException;

public class FormFilter implements javax.servlet.Filter {
    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    public void destroy(){
    }

    /** Causes the next filter in the chain to be invoked, or if the calling filter is the last filter in the chain, causes the resource at the end of the chain to be invoked
     *
     * @param req - the request to pass along the chain
     * @param resp - the response to pass along the chain
     * @param chain - object that giving a view into the invocation chain of a filtered request for a resource
     * @throws ServletException - if the request for the GET could not be handled
     * @throws IOException - if an input or output error is detected when the servlet handles the GET request
     */
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException{
        String contentType = req.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    /** Initialize filter to servlet
     *
     * @param config - the FilterConfig object
     * @throws ServletException - if the request for the GET could not be handled
     */
    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}
