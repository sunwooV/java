<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
	import="practice0809.*"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 조회</title>
</head>
<body>
<h1 align=center>주문 조회</h1>
<%
	request.setCharacterEncoding("utf-8");

	List list = (List)request.getAttribute("list");
%>
	<table border=1 width=800 align=center>
		<tr align=center bgcolor="lightpink">
		<td>주문 번호</td>
		<td>주문 항목 번호</td>
		<td>제품ID</td>
		<td>제품명</td>
		<td>항목수량</td>
		<td>항목가격</td>
		<td>수정</td>
		<td>삭제</td>
		</tr>
		
<%
	for(int i=0;i<list.size();i++){
		OrderVO vo = (OrderVO)list.get(i);
		String num = vo.getNum();
		String item = vo.getItem();
		String prodId = vo.getProdId();
		String name = vo.getName();
		String quantity = vo.getQuantity();
		String price = vo.getPrice();
		String searchId = vo.getSearchId();
		String editId = num;
		String editProdId = prodId;

%>
	<tr align=center>
		<td><%=num %></td>
		<td><%=item %></td>
		<td><%=prodId %></td>
		<td><%=name %></td>
		<td><%=quantity %></td>
		<td><%=price %></td>
		<td><a href='searchOrder?command=editOrder&num=<%=num %>&item=<%=item %>&name=<%=name %>&quantity=<%=quantity %>&price=<%=price %>&searchId=<%=searchId%>&prodId=<%=prodId%>%editId=<%=editId%>&editProdId=<%=editProdId%>'>수정</a></td>
		<td><a href='searchOrder?command=delOrder&num=<%=num %>&searchId=<%=searchId%>&prodId=<%=prodId%>'>삭제</a></td>
	</tr>
	
<%
	}
%>

	</table>
</body>
</html>