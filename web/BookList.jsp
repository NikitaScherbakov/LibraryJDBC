<%--
  Created by IntelliJ IDEA.
  User: GE60
  Date: 30.03.2017
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library app</title>
</head>
<body>
    <%--TODO: fix center tag--%>
    <center>
        <h1>Library management</h1>
        <h2>
            <a href = "/new">Add new book</a>
            &nbsp; &nbsp; &nbsp;
            <a href="/list">List all books</a>
        </h2>
    </center>
    <div align = "center">
        <table border="1" cellpadding="5">
            <caption><h2>List of available books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>In store</th>
                <th>Actions</th>
            </tr>
            <c:forEach var = "book" items = "${listBook}">
                <tr>
                    <td><c:out value ="${library.id}" /></td>
                    <td><c:out value ="${library.title}" /></td>
                    <td><c:out value ="${library.author}" /></td>
                    <td><c:out value ="${library.inStore}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${book.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
