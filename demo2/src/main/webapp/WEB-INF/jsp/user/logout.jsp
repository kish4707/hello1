<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function logout()
{
	$.ajax
    ({
        url : '/user/logout',
        method : 'post',
        
        dataType : 'json',
        cache : false,
        success : function(res)
        {
			alert(res.logout ? "로그아웃 성공" : "실패");
			if(res.logout) location.href='/user/login';
        },
        error(xhr,status,err)
        {
            alert(err);
        }
    });
}
</script>
</head>
<body>
<form>
유저 : ${sessionScope.user_id}
<button type = 'button' onclick='logout()'>로그아웃</button>
</form>
</body>
</html>