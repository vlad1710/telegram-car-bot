<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Додати ТЗ</title>
</head>
<body>
<div align="center">

    <c:if test="${param.success ne null}">
        <p>Збережено.</p>
    </c:if>

    <form action="/newcar" method="POST">
        <h4>Марка</h4>
        <input type="text" name="brand" class="form-control" style="width: 300px"><br/>
        <h4>Модель</h4>
        <input type="text" name="model" class="form-control" style="width: 300px"><br/>
        <h4>ДНЗ</h4>
        <input type="text" name="dnz" class="form-control" style="width: 300px"><br/>
        <h4>Колiр</h4>
        <input type="text" name="color" class="form-control" style="width: 300px"><br/>
        <h4>Рiк випуску</h4>
        <input type="text" name="year" class="form-control" style="width: 300px"><br/>
        <h4>VIN</h4>
        <input type="text" name="vin" class="form-control" style="width: 300px"><br/>
        <h4>Район</h4>
        <input type="text" name="district" class="form-control" style="width: 300px"><br/>
        <h4>Дата, коли додано в группу</h4>
        <input type="date" name="inGroupDate" class="form-control" style="width: 300px"><br/>
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
