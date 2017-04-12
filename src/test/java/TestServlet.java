/*import com.sun.corba.se.spi.activation.Repository;
import controller.ControllerServlet;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertThat;

public class TestServlet{
    @Test
    public void shouldDecodeSearchParameters() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        Repository repository = mock(Repository.class);

        ControllerServlet personController =
                new ControllerServlet(req, resp, repository);

        given(req.getParameter("firstName")).willReturn("johannes");
        given(req.getParameter("lastName")).willReturn("brodwall");
        given(req.getParameter("includePaidMembers"))
                .willReturn("true");
        given(req.getParameter("includeUnpaidMembers"))
                .willReturn("false");
        when(resp.getWriter())
                .thenReturn(new PrintWriter(new StringWriter()));

        personController.find();

        ArgumentCaptor< PersonSpecification> specificationCapture =
                ArgumentCaptor.forClass(PersonSpecification.class);
        verify(repository).find(specificationCapture.capture());

        PersonSpecification personSpec =
                specificationCapture.getValue();
        assertThat(personSpec.getFirstName()).isEqualTo("johannes");
        assertThat(personSpec.getLastName()).isEqualTo("brodwall");
        assertThat(personSpec.isIncludingPaidMembers())
                .isEqualTo(true);
        assertThat(personSpec.isIncludingUnpaidMembers())
                .isEqualTo(false);
    }
}*/
