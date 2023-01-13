<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function editEmp()
{
	var d = $('input[name=deptno]').val();
	var s = $('input[name=sal]').val();
	
	var v = $('#editForm').serialize();
	
	$.ajax
	({
	url : '/emp/update',
	method : 'post',
	data : v,
	dataType : 'json',
	cache : false,
	
	success : function(res)
	{
		alert(res.updated?'수정성공':'수정실패');
		if(res.updated)
			{
			location.href='/emp/detail/'+res.empno;
			}
		else
			{
			alert(res.msg);
			location.href = res.url;
			}
	},
	error(xhr,status,err)
	{
		alert(err);
	}
	});
	return false;
}
</script>
<title></title>
</head>

<body>

<form id='editForm' onsubmit='return editEmp();'>

<input type='hidden' name='empno' value='${emp.empno}'>
<div><label>사번</label>${emp.empno}</div>
<div><label>이름</label>${emp.ename}</div>
<div><label>부서</label><input type='number' min='10' max='40' step='10' id='deptno' name='deptno' value='${emp.deptno}'></div>
<div><label>급여</label><input type='number' min='100' id='sal' name='sal' value='${emp.sal}'></div>
<div><label>입사일</label>${emp.hiredate}</div>

<button type='submit'>수정</button>
<a href='/emp/list'><button type='button'>목록</button></a>

</form>
</body>
</html>