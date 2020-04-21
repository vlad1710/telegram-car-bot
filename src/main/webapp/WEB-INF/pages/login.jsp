<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login page</title>

    <meta name="viewport" content="width=10, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>body {
        background: #fefcea; /* Для старых браузров */
        background: linear-gradient(to top, #fefcea, #aaadf1);
        padding: 10px;
        border: 1px solid #333;
        font-family: "Myriad Pro";
        font-style: oblique;
        vertical-align: middle;
        display: none;
    }


    </style>
</head>
<body>
    <div align="center">
        <c:url value="/j_spring_security_check" var="loginUrl" />

        <form action="${loginUrl}" method="POST">
            Login:<br/><input type="text" name="j_login" class="form-control" style="width:400px"><br/>
            Password:<br/><input type="password" name="j_password" class="form-control" style="width:400px"><br/>
            <input type="submit" class="btn btn-outline-primary" value="Вхiд" style="width:150px"/>


            <c:if test="${param.error ne null}">
                <p>Невiрний логiн або пароль!</p>
            </c:if>

            <c:if test="${param.logout ne null}">
                <p>Ви вийшли.</p>
            </c:if>
        </form>
    </div>
</body>
</html>
