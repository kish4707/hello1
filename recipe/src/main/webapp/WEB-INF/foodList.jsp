<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>나도요리사 게시글목록</title>
<style type="text/css">
   main { width:fit-content; margin:0.5em auto;}
   h3 { width:fit-content; margin:0.5em auto; }
   table { border:1px solid black; width:fit-content; 
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
<h3>나도요리사 게시글목록</h3>

<table>
<tr><th>글번호</th><th>제목</th><th>작성자</th><th>첨부파일</th></tr>
<c:forEach var='b' items='${pageinfo.list}'>
<tr>
<td>${b.num}</td>
<td>${b.title}</td>
<td>${b.author}</td>
<td>${b.cnt}개</td>
</tr>
</c:forEach>

</table>

<div><a href='/foodWrite'>추가</a></div>
</main>

</body>
</html>