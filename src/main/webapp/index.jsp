<%@ page language="java" contentType="text/html; 
    charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.getSession().setAttribute("locale", "en");
		response.sendRedirect("Controller?command=gotoindexpage");
	%>

	<%--<jsp:forward page="jsp/main_page.jsp"></jsp:forward>--%>
</body>
</html>