<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Змiна даних</title>
</head>
<body>
<div>

<form action="/change" method="post">
    <table class="table table-striped">
        <thead>
        <tr align="center">
            <td><b>ID</b></td>
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

        <c:set value="${car}" var="car"/>
        <input type="hidden" name="carId" value="${car.id}"/>

        <tr align="center">
            <td>${car.id}</td>
            <td><input type="text" name="brand" value="${car.brand}" style="width: 40%"
                       class="form-control"></td>

            <td><input type="text" name="model" value="${car.model}" class="form-control"></td>
            <td><input type="text" name="dnz" value="${car.dnz}" class="form-control"></td>
            <td><input type="text" name="color" value="${car.color}" class="form-control"></td>
            <td><input type="text" name="year" value="${car.year}" class="form-control"></td>
            <td><input type="text" name="vin" value="${car.vin}" class="form-control"></td>

            <td><select name="district" class="custom-select mb-3">
                <option value="${car.district}">${car.district}</option>
                <option value="${car.district}"></option>
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

            <td><input type="date" name="inGroupDate" value="${car.inGroupDate}" class="form-control"></td>
            <td><input type="text" name="note" value="${car.note}" class="form-control"></td>
            <input type="hidden" name="cId" value="${car.id}"><br/>


            <td><button type="submit" class="btn btn-primary">Змінити</button></td>
        </tr>
    </table>
</form>

    <a href="/">На головну</a></p>
    <a href="/addcar">Додати ТЗ</a></p>
    <p><a href="/logout">Вихiд</a></p>


</div>

</body>
</html>
