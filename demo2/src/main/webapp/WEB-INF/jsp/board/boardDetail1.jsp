<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<c:forEach var='b' items='${board}'>
<div>이름 : ${b.board.title}</div>
<div>작성자 : ${b.board.author}</div>
<div>내용 : ${b.board.contents}</div>
<div>파일이름 : ${b.att.fname}</div>
<div><a href='/board/download/${b.att.fname}'>${b.att.fname}</a></div>
<div>${b.att.fsize	}</div>
<br>
</c:forEach>

</body>
</html>