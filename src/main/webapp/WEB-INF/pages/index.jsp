<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Пошук ТЗ</title>

    <meta name="viewport" content="width=10, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" media="screen" href="style.css"/>




</head>
<body>
<div align="center">

    <h3>Пошук ТЗ</h3>
    <form action="/getcar" method="POST">

        <h4>ДНЗ</h4>
        <input type="text" name="dnz" class="form-control" style="width: 300px"><br/>
        <h4>VIN</h4>
        <input type="text" name="vin" class="form-control" style="width: 300px"><br/>
        <p>
            <button type="submit" class="btn btn-outline-primary">Знайти ТЗ</button>
        </p>

    </form>

    <table class="table table-striped">
        <thead>
        <tr align="center" >
            <td><b>Марка</b></td>
            <td><b>Модель</b></td>
            <td><b>ДНЗ</b></td>
            <td><b>Колір</b></td>
            <td><b>Рік випуску</b></td>
            <td><b>VIN</b></td>
            <td><b>Район</b></td>
            <td><b>Дата додавання в группу</b></td>
            <td><b>Примiтка</b></td>
            <td><b></b></td>
        </tr>
        </thead>

        <c:forEach items="${cars}" var="cars">

            <tr align="center" >
                <td>${cars.brand}</td>
                <td>${cars.model}</td>
                <td>${cars.dnz}</td>
                <td>${cars.color}</td>
                <td>${cars.year}</td>
                <td>${cars.vin}</td>
                <td>${cars.district}</td>
                <td>${cars.inGroupDate}</td>
                <td>${cars.note}</td>

                <td>
                    <c:if test="${role ne null}">
                        <form action="/changecar" method="post">
                            <input type="hidden" name="cId" value="${cars.id}"><br/>
                            <button type="submit" class="btn btn-outline-primary">Змiнити</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${role ne null}">
            <a href="/adduser">Додати користувача</a></p>
        <a href="/addcar">Додати ТЗ</a></p>
    </c:if>

    <c:url value="/logout" var="logoutUrl"/>
    <p><a href="${logoutUrl}">Вихiд</a></p>


</div>


</body>
</html>
