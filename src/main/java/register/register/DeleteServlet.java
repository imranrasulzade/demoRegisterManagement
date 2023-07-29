package register.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        boolean login = (session != null && session.getAttribute("login") != null);

        if (login && request.getParameter("id") != null) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);


            for (int i = 0; i < AddServlet.memberArrayList.size(); i++) {
                Member member = AddServlet.memberArrayList.get(i);
                if (member.getId() == id) {
                    AddServlet.memberArrayList.remove(i);
                    break;
                }
            }

            request.setAttribute("memberArrayList", AddServlet.memberArrayList);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
