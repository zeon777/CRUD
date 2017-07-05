<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style>
 body{text-align:center;font-size: 16px;background-color:azure }
 div{width:50%;margin: auto; font-size: large;color: darkblue;background-color: aliceblue}
 h2{color: darkcyan}
 b{color: crimson;font-size: 18px}
</style>
 <title>Добро пожаловать</title>
</head>
<body>

<div>
 <h2> Добро пожаловать в CRUD приложение! </h2>
 Вы <b>${visitorCount}</b> посетитель<br/>

 <p><a href="/users.html">Перейти на страницу с пользователями</a> <br/>
</div>




</body>
</html>
