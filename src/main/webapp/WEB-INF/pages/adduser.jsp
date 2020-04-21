<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Реєстрація</title>
    <meta name="viewport" content="width=10, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" media="screen" href="style.css"/>

</head>
<body>
<div align="center">
    <c:url value="/newuser" var="regUrl"/>

    <c:if test="${param.success ne null}">
        <p>Збережено.</p>
    </c:if>

    <form action="${regUrl}" method="POST">

        <h4>Реєстрація</h4><br/>

        Логiн:<br/><input type="text" name="login" class="form-control" style="width: 30%"><br/>
        Пароль:<br/><input type="password" name="password" class="form-control" style="width: 30%"><br/>

        <input type="submit" value="Зареєструвати" class="btn btn-primary"/><br/>

        <c:if test="${exists ne null}">
            <p>Користувач з таким логіном вже зареєстрований.</p>
        </c:if>
    </form>

    <a href="/">На головну</a></p>
    <a href="/addcar">Додати ТЗ</a></p>
    <p><a href="/logout">Вихiд</a></p>

</div>
</body>
</html>
