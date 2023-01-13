<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<%--
<script>
function login()
{
	var id = "${sessionScope.user_id}";
	if(id=="")
		{
		alert('로그인해야 합니다');
		location.href='/user/login';
		return false;
		}
	return true;
}
</script>
--%>
<title></title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/user/logout.jsp"/>

<table>
<tr><td>사번</td><td>이름</td><td>부서</td><td>급여</td><td>입사일</td></tr>
<c:forEach var='list' items='${list}'>
<tr>
<td>${list.empno}</td> 
<td><a href='/emp/detail/${list.empno}'onclick='return login();'> ${list.ename}</a></td>
<td><a href='/emp/deptnolist/${list.deptno}'>${list.deptno}</a></td>
<td>${list.sal}</td>
<td>${list.hiredate}</td>
</tr>
</c:forEach>
</table>
<a href='/emp/add'onclick='return login();'> <button>추가</button></a>
<a href='/emp/list'><button type='button'>목록</button></a>
<a href='/emp/find'><button type='button'>검색</button></a>
<!---->
</body>
</html>