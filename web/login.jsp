<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library</title>
    <link rel="stylesheet" href="//http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen, print" href="styles/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" media="screen, print" href="styles/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" media="screen, print" href="styles/common-styles.css"/>
    <link rel="stylesheet" type="text/css" media="screen, print" href="styles/login.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
    <div class="container login-container">
        <div class="row">
            <div    style=" margin-left: 46%"  class="login-form">
                <h2 class="text-center">Log in</h2>
                <a style="text-align: center" href="vk/oauth"> VK
                    <i class="fa fa-vk fa-4" aria-hidden="true"></i>
                </a>
                <a style="text-align: center" class="btn" href="vk/oauth"> FB
                    <i class="fa fa-facebook fa-4" aria-hidden="true"></i>
                </a>
            </div>
        </div>
    </div>
</body>
</html>