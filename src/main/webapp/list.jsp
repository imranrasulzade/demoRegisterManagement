<%@ page import="register.register.Member" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 14/07/2023
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Table Example</title>

    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        button {
            margin-top: 10px;
        }

    </style>

</head>
<body>
<%
    String username = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies) {
        if(cookie.getName().equals("username"))
            username = cookie.getValue();
    }
%>
<span style="border: solid"><%= username %></span>
<a style="margin-left: 85%" href="LogoutServlet"><button>Log out</button></a>

<br>
<br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody id="table-body">
    <%
        ArrayList<Member> memberArrayList = (ArrayList<Member>) request.getAttribute("memberArrayList");
        for (Member member : memberArrayList) {
    %>
    <tr>
        <td><%= member.getId() %></td>
        <td><%= member.getName() %></td>
        <td><%= member.getSurname() %></td>
        <td><%= member.getAge() %></td>
        <td><%= member.getPhone() %></td>
        <td><%= member.isStatus() ? "Active" : "Inactive" %></td>
        <td><a href="AddServlet?id=<%= member.getId() %>&name=<%= member.getName() %>&surname=<%= member.getSurname() %>&age=<%= member.getAge() %>&phone=<%= member.getPhone() %>&status=<%= member.isStatus() %>">EDIT</a></td>
        <td><a href="DeleteServlet?id=<%= member.getId() %>">Delete</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<a href="add.jsp"><button >Add Member</button></a>
</body>
</html>

