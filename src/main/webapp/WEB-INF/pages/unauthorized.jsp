<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Доступ закритий</title>

    <meta name="viewport" content="width=10, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" media="screen" href="style.css"/>

</head>
<body>
<div align="center">
    <h1>Доступ закритий для ${login}!</h1>
    <a href="/">На головну</a></p>

    <c:url value="/logout" var="logoutUrl" />
    <p><a href="${logoutUrl}">Вихiд</a></p>
</div>
</body>
</html>