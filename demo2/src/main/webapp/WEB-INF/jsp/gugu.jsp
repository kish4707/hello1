<%@  page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>현타오는 구구단</title>
</head>  
<body>
<h1>구구단연습</h1>
<c:forEach var='gugu' items='${list}'>
${gugu}
</c:forEach>

</body>
</html>