<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Application</title>
</head>
<body>
<%--TODO: fix canter tag--%>
<center>
    <h1>Library</h1>
    <h2>
        <div itemscope itemtype="http://schema.org/AddAction">
            <a href="/new" itemprop = "url">Add New Book</a>
        </div>
        &nbsp;&nbsp;&nbsp;
        <div itemscope itemtype="http://schema.org/Action">
            <a href="/list" itemprop="url">List All Books</a>
        </div>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr itemscope itemtype=" http://schema.org/Book">
            <th itemprop = "isbn">ID</th>
            <th itemprop = "bookEdition">Title</th>
            <th itemprop = "illustrator">Author</th>
            <th itemprop = "bookFormat">Genre</th>
        </tr>
        <tr itemscope itemtype="http://schema.org/UpdateAction">
            <th itemprop="instrument">Actions</th>
        </tr>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.title}" /></td>
                <td><c:out value="${book.author}" /></td>
                <td><c:out value="${book.genre}" /></td>
                <td>
                    <div itemscope itemtype="http://schema.org/UpdateAction" >
                        <a href="/update?id=<c:out value='${book.id}' />" itemprop = "url">Edit</a>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div itemscope itemtype="http://schema.org/DeleteAction">
                        <a href="/delete?id=<c:out value='${book.id}' />" itemprop = "url">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

