<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

<script>
function addEmp()
{
	var v = $('#Emp').serialize();
	$.ajax
	({
		url : '/emp/add',
		method : 'post',
		data : v,
		dataType : 'json',
		cache : false,
		
		success : function(res)
		{
			alert(res.added?'성공':'실패');
			if(res.added)
				{
				location.href='/emp/list';
				}
			else
			{
			alert(res.msg);
			location.href = res.url;
			}
		},
		error(xhr, status, err)
		{
			alert(err);
		}
	});
	return false;
}
</script>

</head>
<body>

<form id='Emp' onsubmit='return addEmp()'>
<h3>등록</h3>
<div>사번<input type='text' name='empno' ></div>
<div>이름<input type='text' name='ename'></div>
<div>부서<input type='text' name='deptno' placeholder='10이상'></div>
<div>급여<input type='text' name='sal' placeholder='100이상'></div>
<div>입사일<input type='date' name='hiredate'></div>

<button type='submit'>추가</button>
<button type='reset'>취소</button>
<a href='/emp/list'><button type='button'>목록</button></a>
</form>

</body>
</html>