<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Додати ТЗ</title>

    <meta name="viewport" content="width=10, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" media="screen" href="style.css"/>

</head>
<body>
<div align="center">

    <c:if test="${param.success ne null}">
        <p>Збережено.</p>
    </c:if>

    <form action="/newcar" method="POST">
        <h4>Марка</h4>
        <input type="text" name="brand" class="form-control" style="width: 300px">
        <h4>Модель</h4>
        <input type="text" name="model" class="form-control" style="width: 300px">
        <h4>ДНЗ</h4>
        <input type="text" name="dnz" class="form-control" style="width: 300px">
        <h4>Колiр</h4>
        <input type="text" name="color" class="form-control" style="width: 300px">
        <h4>Рiк випуску</h4>
        <input type="text" name="year" class="form-control" style="width: 300px">
        <h4>VIN</h4>
        <input type="text" name="vin" class="form-control" style="width: 300px">
        <h4>Район</h4>
        <td><select name="district" class="custom-select mb-3" style="width: 300px">
            <option value="Голосiївський">Голосiївський</option>
            <option value="Дарницький">Дарницький</option>
            <option value="Деснянський">Деснянський</option>
            <option value="Днiпровський">Днiпровський</option>
            <option value="Оболонський">Оболонський</option>
            <option value="Печерський">Печерський</option>
            <option value="Подiльський">Подiльський</option>
            <option value="Святошинський">Святошинський</option>
            <option value="Солом'янський">Солом'янський</option>
            <option value="Шевченківський">Шевченківський</option>
        </select></td>
        <h4>Дата, коли додано в группу</h4>
        <input type="date" name="inGroupDate" class="form-control" style="width: 300px">
        <h4>Примiтка</h4>
        <input type="text" name="note" class="form-control" style="width: 300px"><br/>
        <p>
            <button type="submit" class="btn btn-outline-primary">Додати ТЗ</button>
        </p>

    </form>
    <a href="/">На головну</a></p>
    <p><a href="/logout">Вихiд</a></p>
</div>
</body>
</html>
