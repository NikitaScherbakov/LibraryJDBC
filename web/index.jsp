<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Library Application</title>
</head>
<body>

            <c:if test="${isAuthorized}">
                <div class="nav-link">Email: <c:out value="${userName}"/></div>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </c:if>
            <c:if test="${!isAuthorized}">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
<%--TODO: fix canter tag--%>
<center>
  <h1>Library</h1>
  <h2>
      <div itemscope itemtype="http://schema.org/AddAction">
          <c:if test="${isAuthorized}"><a itemtype="http://schema.org/AddAction" href="${pageContext.request.contextPath}/new">Add new Book</a></c:if>
      </div>
      &nbsp;&nbsp;&nbsp;
      <div itemscope itemtype="http://schema.org/ViewAction">
            <c:if test="${isAuthorized}"><a href="${pageContext.request.contextPath}/list" itemprop="url">List All Books</a></c:if>
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
              <c:if test="${isAuthorized}"><a href="${pageContext.request.contextPath}/update?id=<c:out value='${book.id}' />" itemprop = "url">Edit</a></c:if>
          </div>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <div itemscope itemtype="http://schema.org/DeleteAction">
              <c:if test="${isAuthorized}"><a class="close" itemscope itemtype="http://schema.org/DeleteAction" href="${pageContext.request.contextPath}/delete?id=<c:out value='${book.id}'/>" role="button">Delete</a></c:if>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>