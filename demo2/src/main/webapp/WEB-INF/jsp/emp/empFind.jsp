<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function search()
{
var v = $('#find').serialize();
$.ajax
({
	
    url : '/emp/search',
    method : 'post',
    data : v,
    dataType : 'json',
    cache : false,
    success : function(res)
    {
		
    },
    error(xhr,status,err)
    {
        alert(err);
    }
});
}
</script>
</head>
<form id='find' action='/emp/search' method='post'>
<body>
<div>이름 : <input type='text' name='ename'></div>
<div>부서 : <input type='text' name='deptno'></div>

<button type='submit'>검색</button>
</form>
</body>
</html>