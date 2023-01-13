<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
function deleteEmp()
{
	if(confirm('삭제?'))
		{
		$.ajax
		({
			url : '/emp/delete',
			method : 'post',
			data : {'empno':${emp.empno}},
			dataType : 'json',
			cache : false,
			
			success : function(res)
			{
				alert(res.deleted?'삭제 성공':'삭제 실패');
				if(res.deleted) 
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
		}
}
</script>
<title></title>
</head>
<body>
<div><label>사번</label>${emp.empno}</div>
<div><label>이름</label>${emp.ename}</div>
<div><label>부서</label>${emp.deptno}</div>
<div><label>급여</label>${emp.sal}</div>
<div><label>입사일</label>${emp.hiredate}</div>

<a href='/emp/edit/${emp.empno}'><button>수정</button></a>
<button type='button' onclick='deleteEmp();'>삭제</button>
<a href='/emp/list'><button>목록</button></a>
</body>
</html>