<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function login()
{
	var v = $('#user').serialize();
	
	$.ajax
	({
		url : '/user/login',
		method : 'post',
		data : v,
		dataType : 'json',
		cache : false,
		success : function(res)
		{
			alert(res.login ? "로그인 성공" : "로그인 실패");
			if(res.login) location.href='/emp/list';
		},
		error(xhr,status,err)
		{
			alert(res);
			alert(err);
		}
	});
	return false;
}
</script>
<title></title>
</head>
<body>
<form id='user' onsubmit='return login();'>
<div>아이디<input type='text' name='user_id' value='smith'></div>
<div>비밀번호<input type='text' name='user_pwd' value='1111'></div>

<button type='submit'>로그인</button>
<button type='reset'>리셋</button>
<a href='/emp/list'><button type='button'>목록</button></a>
</form>
</body>
</html>