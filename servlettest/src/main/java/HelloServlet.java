/**
 * @author 万松(Aaron)
 * @since 5.7
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    Random r=new Random();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        resp.getWriter().println("hello");
        resp.getWriter().close();
    }
}
