package register.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password) || "imran".equals(username) && "imran".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login", true);

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(3600);
            response.addCookie(usernameCookie);

            doGet(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        boolean login = (session != null && session.getAttribute("login") != null);

        if (login) {
            AddServlet addServlet = new AddServlet();
            addServlet.doPost(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
