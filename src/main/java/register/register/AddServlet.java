package register.register;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    public static Member memberr = new Member(1, "Imran", "Rasulzade", 21, "+994107133033", true);
    public static Member memberr1 = new Member(2, "Ali", "Alizade", 19, "+994101001010", false);
    public static Member memberr2 = new Member(3, "Vali", "Valiyev", 20, "+994505005050", true);
    public static Member memberr3 = new Member(4, "Baba", "Babayev", 22, "+994707007070", false);
    public static ArrayList<Member> memberArrayList = new ArrayList<>(Arrays.asList(memberr, memberr1, memberr2, memberr3));

    public static int staticId = 5;
    public boolean shert = true;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");

        if (login != null && login) {
        String action = request.getServletPath();

        if(request.getParameter("id") == null && request.getParameter("name") == null && request.getParameter("surname") == null && request.getParameter("age") == null && request.getParameter("phone") == null && request.getParameter("status") == null){
            request.setAttribute("memberArrayList", memberArrayList);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }
        if(request.getParameter("id") != ""){
                doPut(request, response);
        }else{
            insertMember(request);
            shert = true;

            request.setAttribute("memberArrayList", memberArrayList);
            request.getRequestDispatcher("list.jsp").forward(request, response);

            }
        }else {
            response.sendRedirect("index.jsp");
        }

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));

        Member newMember = new Member();
        newMember.setId(id);
        newMember.setName(request.getParameter("name"));
        newMember.setSurname(request.getParameter("surname"));
        newMember.setAge(Integer.parseInt(request.getParameter("age")));
        newMember.setPhone(request.getParameter("phone"));
        newMember.setStatus(Boolean.parseBoolean(request.getParameter("status")));

        for (int i = 0; i < memberArrayList.size(); i++) {
            Member existingMember = memberArrayList.get(i);
            if (existingMember.getId() == id) {
                memberArrayList.set(i, newMember);
                break;
            }
        }
        request.setAttribute("memberArrayList", memberArrayList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void insertMember(HttpServletRequest request) {
        Member newMember = new Member();
        newMember.setId(++staticId);
        newMember.setName(request.getParameter("name"));
        newMember.setSurname(request.getParameter("surname"));
        if (request.getParameter("age") != null) {
            newMember.setAge(Integer.parseInt(request.getParameter("age")));
        }
        newMember.setPhone(request.getParameter("phone"));
        if (request.getParameter("status") != null) {
            newMember.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        }

        memberArrayList.add(newMember);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean login = (Boolean) session.getAttribute("login");

        if (login != null && login) {
            String action = request.getServletPath();
            String queryString = request.getQueryString();
            String idString = request.getParameter("id");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String ageString = request.getParameter("age");
            String phone = request.getParameter("phone");
            String statusString = request.getParameter("status");

            Member m = new Member();

            int id = Integer.parseInt(idString);
            int age = Integer.parseInt(ageString);

            boolean status = Boolean.parseBoolean(statusString);

            m.setId(id);
            m.setName(name);
            m.setSurname(surname);
            m.setAge(age);
            m.setPhone(phone);
            m.setStatus(status);

            request.setAttribute("m", m);
            RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");

            dispatcher.forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
        }
        }




    public void destroy() {
    }
}