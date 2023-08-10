<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>histTestAct</title>
</head>
<body>

<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;한국사 시험 실시<h3>

<table border="2" width="1200" height="400">
<tr>
	<td><img src="/histTestPics/${randomNum}.png" width="${500}" height="${400}"/></td>
	<td style="vertical-align:top" width="700" height="400"><br>
		&nbsp;(${count})&nbsp;${histTestQuestion1}</td>
</tr>
</table><br>

<form action="/hist/testAct">
&nbsp;&nbsp;&nbsp;
<input type="text" name="text1">
<input type="submit" value="확인">
</form>

</body>
</html>