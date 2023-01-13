<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>나도요리사 글목록</title>
<style type="text/css">
   main { width:fit-content; margin:0.5em auto;}
   h3 { width:fit-content; margin:0.5em auto; }
   table { border:2px solid black; width:fit-content; 
      border-spacing: 0; border-collapse: collapse;
   }
   th,td {padding:0.5em 1em; border-right:1px dashed black;}
   th { background-color:rgb(220,220,255);}
   th,td { border-bottom:1px solid black;}
   a { text-decoration: none; color:blue; }
</style>
</head>
<body>
<main>
<h3>나도요리사 글목록</h3>

<body bgcolor="#66cc99">
<table>
<tr><th>글번호</th><th>제목</th><th>작성자</th><th>첨부파일</th></tr>
<c:forEach var='b' items='${pageinfo.list}'>
<tr>
<td>${b.num}</td>
<td><a href='/board/detail/${b.num}'>${b.title}</a></td>
<td>${b.author}</td>
<td>${b.cnt}개</td>
</tr>
</c:forEach>
</table>
<c:forEach var='i' begin='${pages.begin}' end='${pages.end}'>
<span><a href='/board/list/${i}/1'>${i}</a></span>
</c:forEach>

<div><a href='/board/add'>추가</a></div>
</main>

</body>
</html>