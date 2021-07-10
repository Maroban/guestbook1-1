
<%@page import="java.util.List"%>
<%@page import="com.javaex.dao.GuestbookDao"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
GuestbookDao guestbookDao = new GuestbookDao();

int no = Integer.parseInt(request.getParameter("no"));

//리스트 가져오기
GuestbookVo guestbookVo = guestbookDao.guestNoPw(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="delete.jsp" method="post">
		<label>비밀번호</label>
		<input type="password" name="pass" value="">
		<button type="submit">확인</button>
		<input type="hidden" name="pw" value="<%=guestbookVo.getPassword()%>">
		<input type="hidden" name="no" value="<%=guestbookVo.getNo()%>">
	</form>

	<a href="./addList.jsp">메인으로 돌아가기</a>


</body>
</html>