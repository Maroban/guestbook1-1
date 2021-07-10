<%@page import="com.javaex.dao.GuestbookDao"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//post 방식 사용 시 한글 깨짐 방지
request.setCharacterEncoding("UTF-8");

//파라미터 값 가져오기
String nameString = request.getParameter("name");
String pwString = request.getParameter("pw");
String contentString = request.getParameter("content");

// Vo로 묶기
GuestbookVo guestbookVo = new GuestbookVo(nameString, pwString, contentString);

// 값 넣기
GuestbookDao guestbookDao = new GuestbookDao();
guestbookDao.insert(guestbookVo);

//리다이렉트
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