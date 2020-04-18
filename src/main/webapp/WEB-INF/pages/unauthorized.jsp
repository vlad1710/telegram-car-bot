<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Доступ закритий</title>
</head>
<body>
<div align="center">
    <h1>Доступ закритий для ${login}!</h1>

    <c:url value="/logout" var="logoutUrl" />
    <p><a href="${logoutUrl}">Вихiд</a></p>
</div>
</body>
</html>