<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>histTestSet</title>
</head>
<body>

<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;한국사 시험 셋팅<h3>

<table border="2" width="1200" height="400">
<tr>
	<td><img src="/histStudyPics/${studyNum}.png" width="${500}" height="${400}"/></td>
	<td style="vertical-align:top" width="700" height="400"><br>
		&nbsp;&nbsp; # 한국사<br><br>
		&nbsp;&nbsp; 1. 소개<br><br>
		&nbsp;&nbsp; 2. 고대, 중세 역사<br><br>
		&nbsp;&nbsp; 3. 근대사</td>
</tr>
</table><br>

<form action="/hist/testSet">
&nbsp;원하는 출제 문항 수를 입력하세요. (1~63) : <input type="text" name="setNum">
<input type="submit">
</form>

</body>
</html>