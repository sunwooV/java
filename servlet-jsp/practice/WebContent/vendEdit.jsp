<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공급업체 수정</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String zip = request.getParameter("zip");
	String country = request.getParameter("country");
	String searchId = request.getParameter("searchId");
	String editId = id;
%>
	<form name="frmsearch" method="get" action="vend" encType="UTF-8">
		공급업체 번호 : <input type="text" name="vend_id" value="<%=id %>"><br>
		공급업체 이름 : <input type="text" name="vend_name" value="<%=name %>"><br>
		공급업체 주소 : <input type="text" name="vend_address" value="<%=address %>"><br>
		공급업체 시 : <input type="text" name="vend_city" value="<%=city %>"><br>
		공급업체 주 : <input type="text" name="vend_state" value="<%=state %>"><br>
		공급업체 우편번호 : <input type="text" name="vend_zip" value="<%=zip %>"><br>
		공급업체 국가 : <input type="text" name="vend_country" value="<%=country %>"><br>
		<input type="submit" value="조회">
		<input type="reset" value="다시입력">
		<input type="hidden" name="command" value="editVend">
		<input type="hidden" name="editId" value="<%=editId %>">
		<input type="hidden" name="searchId" value="<%=searchId %>">
	</form>
</body>
</html>