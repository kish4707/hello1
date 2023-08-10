<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>histStudy</title>
</head>
<body>

<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;한국사 학습<h3>

<table border="2" width="1200" height="400">
<tr>
	<td><img src="/histStudyPics/${studyNum}.png" width="${500}" height="${400}"/></td>
	<td style="vertical-align:top" width="700" height="400"><br>
		&nbsp;(${studyNum})&nbsp;${histStudyText1}</td>
</tr>
</table><br>

<form action="/hist/study">
&nbsp;&nbsp;&nbsp;<input type="submit" value="시작" name="begin">
&nbsp;&nbsp;&nbsp;<input type="submit" value="뒤로" name="back">
&nbsp;&nbsp;&nbsp;<input type="submit" value="앞으로" name="go">
&nbsp;&nbsp;&nbsp;<input type="submit" value="끝" name="end">
&nbsp;&nbsp;&nbsp;<input type="submit" value="메인" name="main">
</form>

</body>
</html>