<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	System.out.print(request.getSession().getAttribute("uid"));
	System.out.print(request.getSession().getAttribute("uname"));
	if (session != null) {
		session.invalidate();
		System.out.print(request.getSession().getAttribute("uid"));
		System.out.print(request.getSession().getAttribute("uname"));
		response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("index.jsp");
	}
%>
