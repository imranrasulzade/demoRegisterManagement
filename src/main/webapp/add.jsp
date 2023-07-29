<%@ page import="register.register.Member" %><%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 14/07/2023
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Member</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        form {
            width: 300px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            margin-top: 10px;
            padding: 8px 16px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
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

<h1>Add Member</h1>

<form action="AddServlet" method="POST">
    <%
        Member m = (Member) request.getAttribute("m");

    %>
    <label for="id">ID: <%= m == null ? "" : m.getId() %></label>
    <input type="hidden" id="id" name="id" value="<%= m == null ? "" : m.getId() %>">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= m == null ? "" : m.getName() %>">

    <label for="surname">Surname:</label>
    <input type="text" id="surname" name="surname" value="<%= m == null ? "" : m.getSurname() %>">

    <label for="age">Age:</label>
    <input type="text" id="age" name="age" value="<%= m == null ? "" : m.getAge() %>">

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="<%= m == null ? "" : m.getPhone() %>">

    <label for="status">Status:</label>
    <input type="text" id="status" name="status" value="<%= m == null ? "" : m.isStatus() %>">

    <button type="submit">Save</button>

</form>

</body>
</html>

