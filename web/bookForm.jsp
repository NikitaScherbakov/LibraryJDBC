<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <c:if test="${book != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="new" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <div itemscope itemtype="http://schema.org/UpdateAction">
                            <c:if test="${book != null}">
                                Edit Book
                            </c:if>
                        </div>
                        <div itemscope itemtype="http://schema.org/AddAction">
                            <c:if test="${book == null}">
                                Add New Book
                            </c:if>
                        </div>
                    </h2>
                </caption>
                <c:if test="${book != null}">
                    <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                </c:if>
                <tr>
                    <div itemscope itemtype="http://schema.org/Book">
                        <th itemprop="bookEdition">Title: </th>
                    </div>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${book.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <div itemscope itemtype="http://schema.org/Book">
                        <th itemprop="illustrator">Author: </th>
                    </div>
                    <td>
                        <input type="text" name="author" size="45"
                               value="<c:out value='${book.author}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <div itemscope itemtype="http://schema.org/Book">
                        <th itemprop="bookFormat">Genre: </th>
                    </div>
                    <td>
                        <input type="text" name="genre" size="45"
                               value="<c:out value='${book.genre}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
    </form>
</div>
</body>
</html>
