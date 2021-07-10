<%@page import="java.util.List"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%@page import="com.javaex.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
// 파라미터 값 가져오기

int no = Integer.parseInt(request.getParameter("no"));
String pass = request.getParameter("pass");
String pw = request.getParameter("pw");

System.out.println("no: " + no + ", pass: " + pass + ", pw: " + pw);

//사전 준비
GuestbookDao guestbookDao = new GuestbookDao();

guestbookDao.delete(no, pass);

// 리다이렉트
response.sendRedirect("./addList.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>