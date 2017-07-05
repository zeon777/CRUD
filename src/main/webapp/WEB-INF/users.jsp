<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        table{margin: auto;text-align: center;width: 50%;border: solid;border-color: aqua;}
        td{border-bottom: 1px solid #ccc;color: #669;padding: 9px 8px;transition: .3s linear;}
        tr.top{background-color: azure}
        body{text-align:center;font-size: 16px}
      div{display: inline-block}
        div.col{padding:10px;}

    </style>
   <title>Пользователи</title>
</head>
<body>
<h3>Пользователи</h3>
<a href="<c:url value="/addUser.html"/>">Добавить пользователя</a>
<form action="/users.html">
    <div class="row">
        <div class="col">Найти пользователя по имени</div>
        <div class="col"><input type="text" name="searchName" id="searchName" value="${searchName}"></div>
        <div class="col"><input class="btn btn-xs" type='submit' value='search'/></div>
    </div>
</form>

 <table>
 <thead>
 <tr class='top'>

     <td>Имя</td>
     <td>Возрост</td>
     <td>Дата создания</td>
     <td>Админ</td>
     <td>Действия</td>
   </tr>
 </thead>
   <c:forEach items="${users}" var="user">
   <tr>

     <td><c:out value="${user.name}" escapeXml="true"/></td>
     <td><c:out value="${user.age}" escapeXml="true"/></td>
     <td><fmt:formatDate value="${user.createdDate}" pattern="dd-MM-yyyy"/></td>
     <td>${user.isAdmin==true ? "Да" : "Нет"}</td>
     <td><a href="<c:url value="/editUser.html?id=${user.id}"/>">Редактировать</a> <a href="<c:url value="/deleteUser.html?id=${user.id}"/>">Удалить</a></td>
   </tr>
   </c:forEach>
 </table>

<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
    <c:choose>
        <c:when test="${page == i.index}">
            <span>${i.index}</span>
        </c:when>
        <c:otherwise>
            <c:url value="/users.html" var="url">
                <c:param name="page" value="${i.index}"/>
                <c:param name="searchName" value="${searchName}"/>
            </c:url>
            <a href='<c:out value="${url}" />'>${i.index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>