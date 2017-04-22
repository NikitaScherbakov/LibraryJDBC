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
          <a href="${pageContext.request.contextPath}/new" itemprop = "url">Add New Book</a>
      </div>
      &nbsp;&nbsp;&nbsp;
      <div itemscope itemtype="http://schema.org/ViewAction">
          <a href="${pageContext.request.contextPath}/list" itemprop="url">List All Books</a>
      </div>
  </h2>
</center>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of Books</h2></caption>
      <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Author</th>
          <th>Genre</th>
          <th>Actions</th>
      </tr>
    <c:forEach var="book" items="${listBook}">
      <tr itemscope itemtype="http://schema.org/Book">
        <td itemprop = "identifier"><c:out value="${book.id}" /></td>
        <td itemprop = "name"><c:out value="${book.title}" /></td>
        <td itemprop = "author"><c:out value="${book.author}" /></td>
        <td itemprop = "genre"><c:out value="${book.genre}" /></td>
        <td>
          <div itemscope itemtype="http://schema.org/UpdateAction" >
              <a href="${pageContext.request.contextPath}/update?id=<c:out value='${book.id}' />" itemprop = "url">Edit</a>
          </div>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <div itemscope itemtype="http://schema.org/DeleteAction">
              <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${book.id}' />" itemprop = "url">Delete</a>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>

