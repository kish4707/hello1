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
<div>게시글번호 ${board.num}</div>
<div>제목 ${board.title}</div>
<div>내용 ${board.contents}</div>
<div>글쓴이 ${board.author}</div>

<br>

<c:forEach var='a' items='${board.attList}'>
<div>파일번호 ${a.num}</div>
<div>파일크기 ${a.fsize}</div>
<div>파일이름<a href='/board/download/${a.fname}'>${a.fname}</a></div>
<br>
</c:forEach>

<div><a href='/board/add'>추가</a></div>
<a href='/board/list/1/1'><button>목록</button></a>

</body>
</html>