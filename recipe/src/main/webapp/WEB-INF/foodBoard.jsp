<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>나도요리사 글작성</title>
<link rel="stylesheet" th:href="@{/css/style.css}"/>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script>
$(function(){
$('#btnUpload').on('click', function(event) {
    event.preventDefault();
    
    var form = $('#uploadForm')[0]
    var data = new FormData(form);
    
    $('#btnUpload').prop('disabled', true);
   
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/food/board",
        data: data,
        dataType: 'text',     /* text, json, script, html, xml 등 */
        processData: false,   /* 디폴트인 Query String 변환 하지 않도록 설정 */
        contentType: false,   /* 디폴트 "application/x-www-form-urlencoded; charset=UTF-8" */
        cache: false,
        timeout: 600000,      /* 시간 제한 없음 */
        success: function (data) {
           $('#btnUpload').prop('disabled', false);
           alert('success')
           location.href = "/food/list/1/50";
        },
        error: function (e) {
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });
})
})
</script>
</head>

<body bgcolor="#66cc99">

<table border="1" width="250px">
<form id='uploadForm'>

	<tr>
		<th colspan="2">나도요리사 글작성</th>
	<tr>
	
	<tr>
		<th>제목</th>
		<th><input type='text' name = 'title' 
			style="width:140px; height:20px; background-color:transparent"></th>
	<tr>
	
	<tr>
		<th>작성자</th>
		<th><input type="text" name="author" value="" 
			style="width:140px; height:20px; background-color:transparent"></th>
	<tr>
	
	<tr>
		<th>내용</th>
		<th><textarea name='contents' 
			style="width:140px; height:80px; background-color:transparent"></textarea></th>
	<tr>
	
	<tr>
		<th>파일</th>		
		<th><input type="file" name="files" multiple="multiple" 
			style="width:140px; height:25px; background-color:transparent"></th>
		
</form>
</table>
	
<button id='btnUpload' type="button">업로드</button>
<a href='/food/list'><button>목록</button></a>

</body>
</html>
