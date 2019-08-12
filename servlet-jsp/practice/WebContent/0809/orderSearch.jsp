<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문번호 검색</title>
</head>
<body>
	<form action="../searchOrder" method="get">
		주문번호 : <input type="text" name="searchId" size="10"><br>
		<br>
		<input type="submit" value="조회">
		<input type="hidden" name="command" value="searchOrder">
	</form>

</body>
</html>